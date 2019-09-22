package org.campusonline.hackathon;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("example")
public class COHackathonExampleRESTService {

    @GET
    @Path("welcome")
    @Produces("application/json")
    public Response getService() {

        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("text", "Welcome to the CAMPUSonline Hackathon 2019. The hacking starts now!").build();

        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET").header("Access-Control-Max-Age", "10").entity(jsonObject)
                .build();

    }
}