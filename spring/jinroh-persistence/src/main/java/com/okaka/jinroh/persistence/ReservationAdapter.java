package com.okaka.jinroh.persistence;

import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationAdapter {
    @Autowired
    ReservationDao reservationDao;

    public void insert(String name){
        Reservation r = new Reservation();
        r.name = name;
        reservationDao.insert(r);
    }

    public List<Reservation> selectAll() {
        return reservationDao.selectAll();
    }

    public List<Reservation> selectByName(String name) {
        return reservationDao.selectByName(name);
    }

}
