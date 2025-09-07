package com.quiz.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User with this UserName not found in databse !! ");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
