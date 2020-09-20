package com.okaka.onenightjinroh;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "T_PHONE")
public class TPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer t_phone_id;
    public String t_phone_number;
    public Integer t_user_id;
}
