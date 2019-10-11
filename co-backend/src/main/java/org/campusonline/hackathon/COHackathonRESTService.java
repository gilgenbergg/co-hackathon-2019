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

    @Path("/")
public class COHackathonRESTService {
        
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    @Path("login")

    public Response checkLogon(@FormParam("login") String login,
                        @FormParam("password") String password,
                               @FormParam("email") String email ) {

        User u = UserDao.instance.findUser(login);
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
