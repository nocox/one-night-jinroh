CREATE TABLE IF NOT EXISTS reservation
(
    id         IDENTITY,
    name       VARCHAR(50) NOT NULL,
    delete_flg BOOLEAN     NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS T_USER
(
    t_user_id   IDENTITY,
    t_user_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS T_PHONE
(
    t_phone_id     IDENTITY,
    t_phone_number varchar(255),
    t_user_id      BIGINT,
    FOREIGN KEY (t_user_id) REFERENCES T_USER (t_user_id)
);

CREATE TABLE IF NOT EXISTS T_EVENT
(
    t_event_id     IDENTITY,
    t_event_title  varchar(255),
    t_event_detail TEXT
);

CREATE TABLE IF NOT EXISTS T_EVENT_PARTICIPANT
(
    t_event_participant_id IDENTITY,
    t_user_id              BIGINT,
    t_event_id             BIGINT,
    FOREIGN KEY (t_user_id) REFERENCES T_USER (t_user_id),
    FOREIGN KEY (t_event_id) REFERENCES T_EVENT (t_event_id)
);

CREATE TABLE IF NOT EXISTS `USER`
(
  `USER_ID` BIGINT IDENTITY,
  PRIMARY KEY (`USER_ID`)
);

CREATE TABLE IF NOT EXISTS `ROOM`
(
  `ROOM_ID` BIGINT IDENTITY,
  `UUID` VARCHAR(255) NOT NULL,
  `RULE_ID` BIGINT(19),
  PRIMARY KEY (`ROOM_ID`)
);

CREATE TABLE IF NOT EXISTS `ROOM_PARTICIPANT` (
  `ROOM_PARTICIPANT_ID` BIGINT(19) NOT NULL,
  `ROOM_ID` BIGINT(19) NOT NULL,
  `USER_ID` BIGINT(19) NOT NULL,
  `HOST_FLG` CHAR(1) NOT NULL,
  PRIMARY KEY (`ROOM_PARTICIPANT_ID`),
  CONSTRAINT `fk_ROOM_PARTICIPANT_ROOM1`
    FOREIGN KEY (`ROOM_ID`)
    REFERENCES `ROOM` (`ROOM_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROOM_PARTICIPANT_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
