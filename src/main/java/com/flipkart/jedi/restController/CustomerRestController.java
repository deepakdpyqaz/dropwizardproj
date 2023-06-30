package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.service.CustomerGMSInterface;
import com.flipkart.jedi.service.CustomerGMSService;

import javax.ws.rs.core.Response;

public class CustomerRestController {
    public static Response customerRegistration(Customer customer){
        CustomerGMSInterface customerSer = new CustomerGMSService();
        try {
            boolean isRegistered = customerSer.customerRegistration(customer);
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"Create\"}").build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
