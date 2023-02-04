package com.okaka.onenightjinroh;

import com.okaka.jinroh.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

	@Autowired
	ClsRoomStatusDao clsRoomStatusDao;

	// Insert data at initailizing phase using ReservationDao#insert
	@Bean
	CommandLineRunner runner() {
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

		insertRoomStatus("Ready");
		insertRoomStatus("InGame");
		insertRoomStatus("Finished");

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

	private void insertRoomStatus(String roomStatus) {
		if (!clsRoomStatusDao.exist(roomStatus)){
			clsRoomStatusDao.insert(new ClsRoomStatusEntity(roomStatus));
		}
	}
}
