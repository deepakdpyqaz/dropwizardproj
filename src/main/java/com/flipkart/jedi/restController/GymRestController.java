package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.service.CustomerGMSInterface;
import com.flipkart.jedi.service.CustomerGMSService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gym")
public class GymRestController {
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_available_gyms(){
        CustomerGMSInterface customerSer = new CustomerGMSService();
        List<Gym> gyms = customerSer.showAllAvailableGym();
        return Response.ok(gyms).build();
    }
}
