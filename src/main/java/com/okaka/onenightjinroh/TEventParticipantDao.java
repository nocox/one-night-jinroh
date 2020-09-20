package com.okaka.onenightjinroh;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

@ConfigAutowireable
@Dao
public interface TEventParticipantDao {
    @Insert
    @Transactional
    int insert(TEventParticipant tEventParticipant);
}
