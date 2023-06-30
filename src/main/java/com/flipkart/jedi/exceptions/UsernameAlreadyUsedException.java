package com.flipkart.jedi.exceptions;

public class UsernameAlreadyUsedException extends Exception{
    private String username;
    public UsernameAlreadyUsedException(String username){
        this.username = username;
    }
    @Override
    public String getMessage(){
        return this.username = " - already exists";
    }
}
