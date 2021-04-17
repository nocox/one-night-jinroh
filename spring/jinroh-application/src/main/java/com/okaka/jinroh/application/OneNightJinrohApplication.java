package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class OneNightJinrohApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneNightJinrohApplication.class, args);
	}

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	TEventDao tEventDao;

	@Autowired
	TUserDao tUserDao;

	// Insert data at initailizing phase using ReservationDao#insert
	@Bean
	CommandLineRunner runner() {
		Arrays.asList("spring", "spring boot", "spring cloud", "doma").forEach(s -> {
			Reservation r = new Reservation();
			r.name = s;
			reservationDao.insert(r);
		});

		Arrays.asList("ライブ", "握手会").forEach(s -> {
			TEvent tEvent = new TEvent();
			tEvent.t_event_title = s;
			tEvent.t_event_detail = "未定";
			tEventDao.insert(tEvent);
		});

		Arrays.asList("太郎", "二郎", "たかし").forEach(name -> {
			TUser tUser = new TUser();
			tUser.t_user_name = name;
			tUserDao.insert(tUser);
		});

		return null;
	}

	@RequestMapping(path = "/")
	@CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"})
	List<Reservation> all() {
		return reservationDao.selectAll();
	}

	@RequestMapping(path = "/", params = "name")
	@CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"})
	List<Reservation> name(@RequestParam String name) {
		return reservationDao.selectByName(name);
	}

	@RequestMapping(path = "/insert", params = "name")
	@CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"})
	List<Reservation> insert(@RequestParam String name) {
		Reservation reservation = new Reservation();
		reservation.name = name;
		reservationDao.insert(reservation);
		return reservationDao.selectAll();
	}

	@RequestMapping(path = "/all_user")
	@CrossOrigin(origins = {"http://ec2-52-198-98-214.ap-northeast-1.compute.amazonaws.com"})
	List<TUser> allUser() {
		return tUserDao.selectTUserAll();
	}
}
