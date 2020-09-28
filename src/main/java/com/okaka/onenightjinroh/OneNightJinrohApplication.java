package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Autowired
	TPhoneDao tPhoneDao;

	@Autowired
	TEventParticipantDao tEventParticipantDao;

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

		TPhone tPhone = new TPhone();
		tPhone.t_phone_number = "09011112222";
		tPhone.t_user_id = 1;
		tPhoneDao.insert(tPhone);

		tPhone.t_phone_number = "09033334444";
		tPhone.t_user_id = 2;
		tPhoneDao.insert(tPhone);

		TEventParticipant tEventParticipant = new TEventParticipant();
		tEventParticipant.t_event_id = 1;
		tEventParticipant.t_user_id = 1;
		tEventParticipantDao.insert(tEventParticipant);

		tEventParticipant.t_user_id = 2;
		tEventParticipantDao.insert(tEventParticipant);

		tEventParticipant.t_user_id = 3;
		tEventParticipantDao.insert(tEventParticipant);

		tEventParticipant.t_event_id = 2;
		tEventParticipant.t_user_id = 1;
		tEventParticipantDao.insert(tEventParticipant);

		return null;
	}

	@RequestMapping(path = "/")
	List<Reservation> all() {
		return reservationDao.selectAll();
	}

	@RequestMapping(path = "/", params = "name")
	List<Reservation> name(@RequestParam String name) {
		return reservationDao.selectByName(name);
	}

	@RequestMapping(path = "/insert", params = "name")
	List<Reservation> insert(@RequestParam String name) {
		Reservation reservation = new Reservation();
		reservation.name = name;
		reservationDao.insert(reservation);
		return reservationDao.selectAll();
	}

	@RequestMapping(path = "/all_user")
	List<TUser> allUser() {
		return tUserDao.selectTUserAll();
	}

	@RequestMapping(path = "/all_user_profile")
	List<TUserProfile> allUserProfile() {
		return tUserDao.selectTUserProfileAll();
	}

	@RequestMapping(path = "/event_participant", params = "eventId")
	List<TUserProfile> eventParticipant(@RequestParam Integer eventId) {
		return tUserDao.selectEventParticipantAll(eventId);
	}

	@RequestMapping(path = "/phone_list", params = "userId")
	List<TPhone> phoneListByUserId(@RequestParam List<Integer> userId){
		return tPhoneDao.selectByTUserIds(userId);
	}

	@RequestMapping(path = "/event_list", params = "userId")
	List<TEvent> eventListByUserId(@RequestParam List<Integer> userId){
		return tEventDao.selectByUserIds(userId, s -> s.collect(Collectors.toList()));
	}

	@RequestMapping(path = "/user_participant_event_list", params = "userId")
	Map<Integer, List<TEventParticipant>> userParticipantEventListByUserId(@RequestParam List<Integer> userId){
		return tEventParticipantDao.selectByUserIds(userId, Collectors.groupingBy(s -> s.t_user_id));
	}
}
