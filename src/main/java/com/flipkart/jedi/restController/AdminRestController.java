package com.flipkart.jedi.restController;

import com.flipkart.jedi.service.AdminGMSInterface;
import com.flipkart.jedi.service.AdminGMSService;
import com.flipkart.jedi.exceptions.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminRestController {
    @Path("all-gym-centres")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public static Response showAllGyms(){
        AdminGMSInterface adminSer = new AdminGMSService();
        try{
            return Response.ok(adminSer.showAllGymCentres()).build();
        }
        // unauthorized admin should not be able to hit the API - edit the code here.
        catch(InvalidLoginCredentialsException exception){
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }catch(Exception ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build();
        }
    }

    @Path("approve-gym")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public static Response approveGyms(int gymId){
        AdminGMSInterface adminSer = new AdminGMSService();
        try{
            return Response.ok(adminSer.approveGym(gymId)).build();
        }catch (GymAlreadyApprovedException ex){
            return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
        }
    }

    @Path("approve-gym-OWNER")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public static Response approveGymOwner(String gymOwnerId){
        AdminGMSInterface adminSer = new AdminGMSService();
        try{
            return Response.ok(adminSer.approveGymOwner(gymOwnerId)).build();
        }catch (GymOwnerAlreadyApprovedException ex){
            return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
        }
    }

}
