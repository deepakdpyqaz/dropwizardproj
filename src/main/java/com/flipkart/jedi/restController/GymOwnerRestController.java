package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
import com.flipkart.jedi.exceptions.GymAlreadyExistsException;
import com.flipkart.jedi.exceptions.GymAlreadyRegisteredException;
import com.flipkart.jedi.exceptions.GymNotRemovedException;
import com.flipkart.jedi.exceptions.GymOwnerAlreadyRegisteredException;
import com.flipkart.jedi.service.GymOwnerGMSInterface;
import com.flipkart.jedi.service.GymOwnerGMSService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("gym-owner")
public class GymOwnerRestController {
    @Path("register")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public static Response gymOwnerRegister(GymOwner gymOwner){
        GymOwnerGMSInterface gymOwnerSer = new GymOwnerGMSService();
        try{
            return Response.ok(gymOwnerSer.gymOwnerRegister(gymOwner)).build();
        } catch (GymOwnerAlreadyRegisteredException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (GymAlreadyRegisteredException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @Path("add-gym")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public static Response addGym(Gym gym){
        GymOwnerGMSInterface gymOwnerSer = new GymOwnerGMSService();
        try{
            return Response.ok(gymOwnerSer.addGym(gym)).build();
        } catch (GymAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @Path("remove-gym")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public static Response removeGym(Gym gym){
        GymOwnerGMSInterface gymOwnerSer = new GymOwnerGMSService();
        try{
            return Response.ok(gymOwnerSer.removeGym(gymOwnerSer.getGymId(gym))).build();
        } catch (GymNotRemovedException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }


}
