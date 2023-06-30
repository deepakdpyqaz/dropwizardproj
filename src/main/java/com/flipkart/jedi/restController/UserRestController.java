package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.User;
import com.flipkart.jedi.exceptions.AccountNotApprovedException;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;
import com.flipkart.jedi.service.UserGMSInterface;
import com.flipkart.jedi.service.UserGMSService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserRestController {

    @Path("login")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public static Response login(User user){
        UserGMSInterface userSer = new UserGMSService();
        try{
            System.out.println(user);
            return Response.ok(userSer.login(user.getUsername(),user.getPassword())).build();
        }
        catch(InvalidLoginCredentialsException ilce){
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\""+ilce.getMessage()+"\"}").build();
        }
        catch(AccountNotApprovedException anae){
            return Response.status(Response.Status.UNAUTHORIZED).entity(anae.getMessage()).build();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
