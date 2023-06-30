package com.flipkart.jedi.exceptions;

public class RoleNotFoundException extends Throwable {

    public String getMessage(){return "Corresponding role not found";}
}
