package com.flipkart.jedi.exceptions;

public class NoClashingSlotException extends Exception {
    public String getMessage(){ return "No clashing slots found";}
}
