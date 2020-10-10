package com.okaka.onenightjinroh;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "T_EVENT")
public class TEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer t_event_id;
    public String t_event_title;
    public String t_event_detail;
}
