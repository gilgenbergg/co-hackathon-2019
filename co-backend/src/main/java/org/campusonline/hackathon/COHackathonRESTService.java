package org.campusonline.hackathon;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class COHackathonRESTService {

    @GET
    @Path("time")
    @Produces("application/json")
    public Response getService() {

        Date date = new Date();
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("text", date.toString()).build();

        return Response.status(Response.Status.OK)
                .entity(jsonObject)
                .build();

    }

    @POST
    @Path("login")
    @Produces("application/json")
    public Response recieveLogin(String login) {
        Map<String, String> fakeBase = new HashMap<>();
        fakeBase.put("admin", "12345");
        for (int i=0; i<=10; i++) {
            System.out.println();
            i++;
        }
        System.out.println(login);

        if (fakeBase.containsKey(login)
                //&& (fakeBase.get(login).equals(password))
        ) {
                final JsonObject jsonObject = Json.createObjectBuilder()
                        .add("Success", login).build();
                return Response.status(Response.Status.OK)
                        .entity(jsonObject)
                        .build();
            }
            else {
                final JsonObject jsonObject = Json.createObjectBuilder()
                        .add("Failed authorization", login).build();
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(jsonObject)
                        .build();
            }
        }
    }