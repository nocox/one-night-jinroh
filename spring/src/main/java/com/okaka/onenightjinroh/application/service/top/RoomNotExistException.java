package com.okaka.onenightjinroh.application.service.top;

public class RoomNotExistException extends Exception {
  RoomNotExistException(String msg){
    super(msg);
  }
}
