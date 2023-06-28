package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.User;
import com.flipkart.jedi.exceptions.AccountNotApprovedException;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;
import com.flipkart.jedi.service.UserGMSInterface;
import com.flipkart.jedi.service.UserGMSService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
            return Response.ok(userSer.login(user.getUsername(),user.getPassword())).build();
        }
        catch(InvalidLoginCredentialsException ilce){
            return Response.status(Response.Status.UNAUTHORIZED).entity(ilce.getMessage()).build();
        }
        catch(AccountNotApprovedException anae){
            return Response.status(Response.Status.UNAUTHORIZED).entity(anae.getMessage()).build();
        }
    }
}
