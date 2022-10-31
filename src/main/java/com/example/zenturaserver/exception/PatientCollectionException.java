package com.example.zenturaserver.exception;

public class PatientCollectionException extends Exception{

    public PatientCollectionException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "Patient not found!";
    }
}
