package com.flipkart.jedi.restController;

import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;
import com.flipkart.jedi.service.CustomerGMSInterface;
import com.flipkart.jedi.service.CustomerGMSService;

import javax.ws.rs.*;
import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.exceptions.UsernameAlreadyUsedException;
import com.flipkart.jedi.service.CustomerGMSInterface;
import com.flipkart.jedi.service.CustomerGMSService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.jedi.bean.Customer;

@Path("customer")
public class CustomerRestController {
    @Path("get_all_details")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public static Response getAllDetails(String user_id) {
        CustomerGMSInterface customerGMSInterface = new CustomerGMSService();
        try {
            return Response.ok(customerGMSInterface.getAllDetails(user_id)).build();
        } catch (InvalidLoginCredentialsException ilce) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(ilce.getMessage()).build();
        }
    }
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public static Response customerRegistration(Customer customer){
        CustomerGMSInterface customerSer = new CustomerGMSService();
        try {
            boolean isRegistered = customerSer.customerRegistration(customer);
            System.out.println(isRegistered);
            if(isRegistered){
                return Response.status(Response.Status.CREATED).entity("{\"message\":\"Create\"}").build();
            }
            else{
                throw new UsernameAlreadyUsedException(customer.getCustomerId());
            }
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Internal server error\"}").build();
        }
    }
}
