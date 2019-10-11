package org.campusonline.hackathon;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

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
}
