package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.Reservation;
import com.okaka.jinroh.persistence.ReservationAdapter;
import com.okaka.jinroh.persistence.RoleDao;
import com.okaka.jinroh.persistence.RoleEntity;
import com.okaka.jinroh.persistence.RoleSelectDao;
import com.okaka.jinroh.persistence.RoleSelectEntity;
import com.okaka.jinroh.persistence.RuleDao;
import com.okaka.jinroh.persistence.RuleEntity;
import com.okaka.jinroh.persistence.TEvent;
import com.okaka.jinroh.persistence.TEventDao;
import com.okaka.jinroh.persistence.TUser;
import com.okaka.jinroh.persistence.TUserDao;
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

	@Autowired
	RoleDao roleDao;

	@Autowired
	RuleDao ruleDao;

	@Autowired
	RoleSelectDao roleSelectDao;

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

		if (roleDao.selectAll().size() != 6) {
			Arrays.asList("村人","人狼","占い師","怪盗","狂人","吊り人").forEach(name -> {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.role_name = name;
				roleDao.insert(roleEntity);
			});
		}

		insertRole("デフォルト3人", Arrays.asList(1L, 2L, 3L, 4L, 5L));
		insertRole("デフォルト4人", Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
		insertRole("デフォルト5人", Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 1L));
		insertRole("デフォルト6人", Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 1L, 2L));
		insertRole("デフォルト7人", Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 1L, 2L, 3L));
		return null;
	}

	private void insertRole(String ruleName, List<Long> RoleIdList) {
		if (!ruleDao.existRule(ruleName)) {
			RuleEntity ruleEntity = new RuleEntity();
			ruleEntity.rule_name = ruleName;
			ruleDao.insert(ruleEntity);

			RoleIdList.forEach(id -> {
				RoleSelectEntity roleSelectEntity = new RoleSelectEntity();
				roleSelectEntity.rule_id = ruleEntity.rule_id;
				roleSelectEntity.role_id = id;
				roleSelectDao.insert(roleSelectEntity);
			});
		}
	}

	@RequestMapping(path = "/")
	List<Reservation> all() {
		return reservationAdapter.selectAll();
	}

	@RequestMapping(path = "/", params = "name")
	List<Reservation> name(@RequestParam String name) {
		return reservationAdapter.selectByName(name);
	}

	@RequestMapping(path = "/insert", params = "name")
	List<Reservation> insert(@RequestParam String name) {
		reservationAdapter.insert(name);
		return reservationAdapter.selectAll();
	}

	@RequestMapping(path = "/all_user")
	List<TUser> allUser() {
		return tUserDao.selectTUserAll();
	}
}
