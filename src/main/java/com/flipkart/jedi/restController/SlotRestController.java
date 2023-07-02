package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Slot;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotNotCreatedException;
import com.flipkart.jedi.service.SlotGMSInterface;
import com.flipkart.jedi.service.SlotGMSService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("slot")
public class SlotRestController {
    @Path("get-slots-of-gym/{gymId}")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public static Response getSlotsOfGym(@PathParam("gymId") int gymId){
        SlotGMSInterface slotSer = new SlotGMSService();
        try{
            return Response.ok(slotSer.getSlotsOfGym(gymId)).build();
        } catch(NoSlotsFoundException ex){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(ex.getMessage()).build();
        }
    }

    @Path("get-slot-availability")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public static Response isSlotAvailable(int gymId){
        SlotGMSInterface slotSer = new SlotGMSService();
        try{
            return Response.ok(slotSer.isSlotAvailable(gymId)).build();
        } catch(NoSlotsFoundException ex){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(ex.getMessage()).build();
        }
    }
    @Path("create-slot")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public static Response createSlots(List<Slot> slots){
        SlotGMSInterface slotSer = new SlotGMSService();
        try{
            return Response.ok(slotSer.createSlots(Collections.singletonList((Slot) slots))).build();
        } catch(NoSlotsFoundException ex){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(ex.getMessage()).build();
        } catch (SlotNotCreatedException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }

}
