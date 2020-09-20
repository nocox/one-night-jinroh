package com.okaka.onenightjinroh;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "T_EVENT_PARTICIPANT")
public class TEventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer t_event_participant_id;
    public Integer t_user_id;
    public Integer t_event_id;
}
