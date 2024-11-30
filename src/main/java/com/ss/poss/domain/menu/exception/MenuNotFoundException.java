package com.ss.poss.domain.menu.exception;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(String msg){
        super(msg);
    }
}
