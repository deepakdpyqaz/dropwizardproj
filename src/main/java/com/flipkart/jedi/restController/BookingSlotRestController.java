package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.exceptions.NoClashingSlotException;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotAlreadyBookedException;
import com.flipkart.jedi.exceptions.SlotNotCancelledException;
import com.flipkart.jedi.service.BookingSlotGMSInterface;
import com.flipkart.jedi.service.BookingSlotGMSService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("booking_slot")
public class BookingSlotRestController {
    @Path("book-slot/{username}/{slot_id}")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public static Response bookSlot(@PathParam("username") String username, @PathParam("slot_id") int slot_id){
        BookingSlotGMSInterface bookingSlotSer = new BookingSlotGMSService();
        try{
            return Response.ok(bookingSlotSer.bookSlot(username, slot_id)).build();
        } catch (SlotAlreadyBookedException | NoClashingSlotException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
        } catch (NoSlotsFoundException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
    @Path("cancel-slot")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public static Response cancelSlot(int booking_id){
        BookingSlotGMSInterface bookingSlotSer = new BookingSlotGMSService();
        try{
            return Response.ok(bookingSlotSer.cancelSlot(booking_id)).build();
        } catch (SlotNotCancelledException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @Path("clashing-slot/{username}/{slot_id}")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public static Response getClashingBooking(@PathParam("username") String username, @PathParam("slot_id") int slot_id){
        BookingSlotGMSInterface bookingSlotSer = new BookingSlotGMSService();
        try{
            Booking book =  bookingSlotSer.getClashingBooking(username, slot_id);
            System.out.println(book);
            return Response.ok(bookingSlotSer.getClashingBooking(username, slot_id)).build();
        } catch (NoClashingSlotException e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }
}
