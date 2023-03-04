package com.okaka.onenightjinroh.application.service.room;

public class NotEnoughParticipantsException extends Exception {
  NotEnoughParticipantsException(String msg){
    super(msg);
  }
}
