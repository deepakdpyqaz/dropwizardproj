package com.flipkart.jedi.restController;

import com.flipkart.jedi.exceptions.RoleNotFoundException;
import com.flipkart.jedi.service.RoleGMSInterface;
import com.flipkart.jedi.service.RoleGMSService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("role")
public class RoleRestController {
    @Path("name-by-id")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public static Response getRoleNameById(int roleId){
        RoleGMSInterface roleSer = new RoleGMSService();
        try{
            return Response.ok(roleSer.getRoleNameById(roleId)).build();
        } catch (RoleNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("id-by-name")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public static Response getRoleNameById(String roleName){
        RoleGMSInterface roleSer = new RoleGMSService();
        try{
            return Response.ok(roleSer.getRoleIdByName(roleName)).build();
        } catch (RoleNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
