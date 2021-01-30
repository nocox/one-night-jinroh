package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.Reservation;
import com.okaka.jinroh.persistence.ReservationAdapter;
import com.okaka.jinroh.persistence.ReservationDao;
import com.okaka.jinroh.persistence.TEvent;
import com.okaka.jinroh.persistence.TEventDao;
import com.okaka.jinroh.persistence.TUser;
import com.okaka.jinroh.persistence.TUserDao;
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

@SpringBootApplication(scanBasePackages = "com.okaka")
@RestController
public class OneNightJinrohApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneNightJinrohApplication.class, args);
	}

	@Autowired
	ReservationAdapter reservationAdapter;

	@Autowired
	TEventDao tEventDao;

	@Autowired
	TUserDao tUserDao;

	// Insert data at initailizing phase using ReservationDao#insert
	@Bean
	CommandLineRunner runner() {
		Arrays.asList("spring", "spring boot", "spring cloud", "doma").forEach(s -> {
			reservationAdapter.insert(s);
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
	@CrossOrigin(origins = {"http://localhost:8081"})
	List<Reservation> all() {
		return reservationAdapter.selectAll();
	}

	@RequestMapping(path = "/", params = "name")
	@CrossOrigin(origins = {"http://localhost:8081"})
	List<Reservation> name(@RequestParam String name) {
		return reservationAdapter.selectByName(name);
	}

	@RequestMapping(path = "/insert", params = "name")
	@CrossOrigin(origins = {"http://localhost:8081"})
	List<Reservation> insert(@RequestParam String name) {
		reservationAdapter.insert(name);
		return reservationAdapter.selectAll();
	}

	@RequestMapping(path = "/all_user")
	@CrossOrigin(origins = {"http://localhost:8081"})
	List<TUser> allUser() {
		return tUserDao.selectTUserAll();
	}
}
