package com.okaka.jinroh.persistence;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "ROLE_SELECT")
public class RoleSelectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long role_select_id;
    public Long role_id;
    public Long rule_id;
}
