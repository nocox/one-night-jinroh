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
  `USER_ID` BIGINT(19) IDENTITY,
  `USER_NAME` VARCHAR(255),
  PRIMARY KEY (`USER_ID`)
);

CREATE TABLE IF NOT EXISTS `RULE` (
  `RULE_ID` BIGINT(19) IDENTITY,
  `RULE_NAME` VARCHAR(255),
  PRIMARY KEY (`RULE_ID`)
);

CREATE TABLE IF NOT EXISTS `TEMPLATE_RULE` (
  `TEMPLATE_RULE_ID` BIGINT(19) IDENTITY,
  `RULE_ID` BIGINT(19) NOT NULL,
  `TEMPLATE_RULE_NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`TEMPLATE_RULE_ID`),
  CONSTRAINT `fk_TEMPLATE_RULE_RULE1`
    FOREIGN KEY (`RULE_ID`)
    REFERENCES `RULE` (`RULE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `ROLE` (
  `ROLE_ID` BIGINT(19) IDENTITY ,
  `ROLE_NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
);

CREATE TABLE IF NOT EXISTS `ROLE_SELECT` (
  `ROLE_SELECT_ID` BIGINT(19) IDENTITY ,
  `ROLE_ID` BIGINT(19) NOT NULL,
  `RULE_ID` BIGINT(19) NOT NULL,
  PRIMARY KEY (`ROLE_SELECT_ID`),
  CONSTRAINT `fk_ROLE_SELECT_ROLE1`
    FOREIGN KEY (`ROLE_ID`)
    REFERENCES `ROLE` (`ROLE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROLE_SELECT_RULE1`
    FOREIGN KEY (`RULE_ID`)
    REFERENCES `RULE` (`RULE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `ROOM`
(
  `ROOM_ID` BIGINT(19) IDENTITY,
  `UUID` VARCHAR(255) NOT NULL,
  `RULE_ID` BIGINT(19),
  PRIMARY KEY (`ROOM_ID`),
  CONSTRAINT `fk_ROOM_RULE1`
    FOREIGN KEY (`RULE_ID`)
    REFERENCES `RULE` (`RULE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `ROOM_PARTICIPANT` (
  `ROOM_PARTICIPANT_ID` BIGINT(19) IDENTITY,
  `ROOM_ID` BIGINT(19) NOT NULL,
  `USER_ID` BIGINT(19) NOT NULL,
  `HOST_FLG` BOOLEAN NOT NULL,
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

CREATE TABLE IF NOT EXISTS `GAME` (
  `GAME_ID` BIGINT(19) IDENTITY,
  `ROOM_ID` BIGINT(19) NOT NULL,
  `RULE_ID` BIGINT(19) NOT NULL,
  PRIMARY KEY (`GAME_ID`),
  CONSTRAINT `fk_GAME_ROOM1`
    FOREIGN KEY (`ROOM_ID`)
    REFERENCES `ROOM` (`ROOM_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GAME_RULE1`
    FOREIGN KEY (`RULE_ID`)
    REFERENCES `RULE` (`RULE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `GAME_PARTICIPATION` (
  `GAME_PARTICIPATION_ID` BIGINT(19) IDENTITY,
  `GAME_ID` BIGINT(19) NOT NULL,
  `USER_ID` BIGINT(19) NOT NULL,
  `ROLE_ID` BIGINT(19) NOT NULL,
  `HOST_FLG` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`GAME_PARTICIPATION_ID`),
  CONSTRAINT `fk_GAME_PARTICIPATION_GAME1`
    FOREIGN KEY (`GAME_ID`)
    REFERENCES `GAME` (`GAME_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GAME_PARTICIPATION_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GAME_PARTICIPATION_ROLE1`
    FOREIGN KEY (`ROLE_ID`)
    REFERENCES `ROLE` (`ROLE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `NIGHT_ACT` (
  `NIGHT_ACT_ID` BIGINT(19) IDENTITY,
  `GAME_PARTICIPATION_ID` BIGINT(19) NOT NULL,
  PRIMARY KEY (`NIGHT_ACT_ID`),
  UNIQUE INDEX `GAME_PARTICIPATION_ID_UNIQUE` (`GAME_PARTICIPATION_ID` ASC),
  CONSTRAINT `fk_NIGHT_ACT_GAME_PARTICIPATION1`
    FOREIGN KEY (`GAME_PARTICIPATION_ID`)
    REFERENCES `GAME_PARTICIPATION` (`GAME_PARTICIPATION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `VOTE` (
  `VOTE_ID` BIGINT(19) IDENTITY,
  `GAME_PARTICIPATION_ID` BIGINT(19) NOT NULL UNIQUE,
  `TO_GAME_PARTICIPATION_ID` BIGINT(19) NULL,
  `PEACE_VILLAGE_FLG` BOOLEAN NOT NULL DEFAULT 'N',
  PRIMARY KEY (`VOTE_ID`),
  CONSTRAINT `fk_VOTE_GAME_PARTICIPATION1`
    FOREIGN KEY (`GAME_PARTICIPATION_ID`)
    REFERENCES `GAME_PARTICIPATION` (`GAME_PARTICIPATION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VOTE_GAME_PARTICIPATION2`
    FOREIGN KEY (`TO_GAME_PARTICIPATION_ID`)
    REFERENCES `GAME_PARTICIPATION` (`GAME_PARTICIPATION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `GAME_VOTE_TALLY` (
  `GAME_VOTE_TALLY_ID` BIGINT(19) IDENTITY,
  `GAME_ID` BIGINT(19) NOT NULL,
  `GAME_PARTICIPATION_ID` BIGINT(19) NOT NULL,
  `VOTE_COUNT` INT NOT NULL DEFAULT 0,
  `SELECTED` BOOLEAN NOT NULL DEFAULT 'N',
  PRIMARY KEY (`GAME_VOTE_TALLY_ID`),
  CONSTRAINT `fk_GAME_RESULT_GAME1`
    FOREIGN KEY (`GAME_ID`)
    REFERENCES `GAME` (`GAME_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GAME_VOTE_TALLY_GAME_PARTICIPATION1`
    FOREIGN KEY (`GAME_PARTICIPATION_ID`)
    REFERENCES `GAME_PARTICIPATION` (`GAME_PARTICIPATION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
