package com.flipkart.jedi.restController;

import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.exceptions.NoClashingSlotException;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotAlreadyBookedException;
import com.flipkart.jedi.exceptions.SlotNotCancelledException;
import com.flipkart.jedi.service.BookingSlotGMSInterface;
import com.flipkart.jedi.service.BookingSlotGMSService;
import com.flipkart.jedi.service.CustomerGMSInterface;
import com.flipkart.jedi.service.CustomerGMSService;
import org.checkerframework.checker.units.qual.C;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @Path("cancel-slot/{booking_id}")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public static Response cancelSlot(@PathParam("booking_id") int booking_id){
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
            if(book==null){
                return Response.status(Response.Status.OK).entity("{\"clashing\":\"false\"}").build();
            }
            return Response.status(Response.Status.OK).entity("{\"clashing\":\"true\"}").build();
        } catch (NoClashingSlotException e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }

    @Path("get_all_bookings/{username}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public static Response getAllBookings(@PathParam("username") String username)
    {
        CustomerGMSInterface customerSer = new CustomerGMSService();
        List<Booking> bookings = customerSer.showAllBookings(username);
        return Response.ok(bookings).build();
    }
}
