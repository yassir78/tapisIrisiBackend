package com.oracle;

import java.io.File;

import java.io.FileInputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.dao.MotifDao;
import com.oracle.dao.ProprieteDao;
import com.oracle.dao.UserDao;
import com.oracle.dao.UserMotifDao;
import com.oracle.models.Motif;
import com.oracle.models.Role;
import com.oracle.models.User;
import com.oracle.models.UserMotif;


@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement
public class TapisIrisiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapisIrisiApplication.class, args);
	}
   @Bean
   public CommandLineRunner demo(UserDao userDao,MotifDao motifDao,ProprieteDao proprieteDao,UserMotifDao userMotifDao) {
	   return (args) -> {
			// user
//			User user = new User();
//			user.setLogin("yassir123");
//			user.setNom("soulaimane");
//			user.setPrenom("abidar");
//			user.setPassword("hiba");
//			user.setRole(Role.ADMIN);
//			User returnedUser = userDao.save(user);
//			// Motif
//			Motif motif = new Motif();
//			motif.setLibelle("corne de bélier");
//			Motif returnedMotif = motifDao.save(motif);
//			// User motif
//			UserMotif userMotif1 = new UserMotif();
//			MultipartFile multipartFile = new MockMultipartFile("motif.png", new FileInputStream(new File(
//					"C:\\Users\\yassi\\Desktop\\tapsbackend2\\tapisIrisiBackend\\src\\assets\\images\\motif1.png")));
//			byte[] blob1 = multipartFile.getBytes();
//			userMotif1.setImage(blob1);
//			userMotif1.setUser(returnedUser);
//			userMotif1.setMotif(returnedMotif);
//			UserMotif returnedUserMotif = userMotifDao.save(userMotif1);
	   };
   }

}
