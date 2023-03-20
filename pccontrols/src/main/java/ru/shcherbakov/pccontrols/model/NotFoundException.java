package ru.shcherbakov.pccontrols.model;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super();
    }
}
