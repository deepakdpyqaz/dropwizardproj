package com.flipkart.jedi.exceptions;

public class SlotAlreadyBookedException extends Throwable {
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "Slot already booked. Kindly book another slot!";
    }
}
