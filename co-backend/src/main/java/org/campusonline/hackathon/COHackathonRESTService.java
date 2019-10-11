package org.campusonline.hackathon;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/")
public class COHackathonRESTService {
        
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    @Path("register")
    public Response registerUser(@FormParam("login") String login,
                                 @FormParam("password") String password,
                                @FormParam("email") String email) {

        User u = new User (login, password);
        String message = "";
        boolean error = false;

        User o = UserDao.instance.findUser(login);

        if (o != null) {
            error = true;
            message = "Username already taken";
        }


        if (login.equals("")) {
            error = true;
            message = "Empty username";
        }

        if (email.equals("")) {
            error = true;
            message = "Empty email";
        }

        String regex = "^(.+)@(.+)\\.(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        if (!matcher.matches()) {
            error = true;
            System.out.println(email);
            message = "Email not valid";
        }

        if (error) {

            final JsonObject jsonObject = Json.createObjectBuilder()
                    .add("success", false)
                    .add("message", message)
                    .build();
            return Response.status(Response.Status.OK) // BAD REQUEST, eigentlich
                    .entity(jsonObject)
                    .build();
        }

        UserDao.instance.registerUser(u);

        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("success", true)
                .build();
        return Response.status(Response.Status.OK)
                .entity(jsonObject)
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    @Path("login")

    public Response checkLogon(@FormParam("login") String login,
                        @FormParam("password") String password,
                               @FormParam("email") String email ) {

        User u = UserDao.instance.findUser(login);
        if (u != null) {
            System.out.println("user was found");
        }
        if (u != null && u.password.equals(password)) {
            // generate token
            String token = UUID.randomUUID().toString();
            UserDao.instance.rememberLoginToken(token, u);

            final JsonObject jsonObject = Json.createObjectBuilder()
                    .add("success", true)
                    .add("token", token)
                    .build();

            return Response.status(Response.Status.OK)
                    .entity(jsonObject)
                    .build();
        }
        else {
            final JsonObject jsonObject = Json.createObjectBuilder()
                    .add("success", false)
                    .add("token", "")
                    .build();
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(jsonObject)
                    .build();
        }
    }

    @GET
    @Path("time")
    @Produces("application/json")
    public Response getTimeService() {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("text", formatter.format(date)).build();

        return Response.status(Response.Status.OK)
                .entity(jsonObject)
                .build();

    }


    @POST
    @Path("secret")
    @Produces("application/json")
    public Response getSecretService(String message) {

        String secretMessage = "{\n\t\"secret\" : \"aligator3\"\n}";

        if (message.equals(secretMessage)) {
            final JsonObject jsonObject = Json.createObjectBuilder()
                    .add("text", "CORRECT MESSAGE").build();

            return Response.status(Response.Status.OK)
                    .entity(jsonObject)
                    .build();
        } else {
            final JsonObject jsonObject = Json.createObjectBuilder()
                    .add("text", "WRONG MESSAGE").build();
            return Response.status(418, "I'm a teapot.")
                    .entity(jsonObject)
                    .build();
        }


    }


    @GET
    @Path("links")
    @Produces("application/json")
    public Response getLinkService() {

        Map<String, String> links = new HashMap<>();
        links.put("home", "/");
        links.put("links", "links");
        links.put("login", "login");
        links.put("register", "register");

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        links.forEach( (key, value) -> jsonObjectBuilder.add(key, value));
        final JsonObject jsonObject = jsonObjectBuilder.build();

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(jsonObject)
                .build();

    }
}
