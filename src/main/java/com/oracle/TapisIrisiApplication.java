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
import com.oracle.models.Propriete;
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
	public CommandLineRunner demo(UserDao userDao, MotifDao motifDao, ProprieteDao proprieteDao,
			UserMotifDao userMotifDao) {
		return (args) -> {
//			// user
//			User user = new User();
//			user.setLogin("admin");
//			user.setNom("yassir");
//			user.setPrenom("acaf");
//			user.setPassword("admin");
//			user.setRole(Role.ADMIN);
//			User returnedUser = userDao.save(user);
//			// Motif 1
//			Motif motif1 = new Motif();
//			motif1.setLibelle("corne de bélier");
//			motif1.setDescription("Ce motif symbolise la force, la virilité, la puissance et la fertilité masculine. "
//					+ "Tous les anciens tapis de Perepedil sont ornés de motifs ”corne de bélier”.");
//			Motif returnedMotif1 = motifDao.save(motif1);
//			// propriete Motif 1
//			Propriete prop1 = new Propriete();
//			prop1.setLibelle("origine");
//			prop1.setDescription("Iran");
//			prop1.setMotif(motif1);
//			proprieteDao.save(prop1);
//			// User motif 1
//			UserMotif userMotif1 = new UserMotif();
//			MultipartFile multipartFile1 = new MockMultipartFile("motif1.png", new FileInputStream(new File(
//					"C:\\Users\\yassi\\Desktop\\tapsbackend2\\tapisIrisiBackend\\src\\assets\\images\\motif1.png")));
//			byte[] blob1 = multipartFile1.getBytes();
//			userMotif1.setImage(blob1);
//			userMotif1.setUser(returnedUser);
//			userMotif1.setMotif(returnedMotif1);
//			UserMotif returnedUserMotif1 = userMotifDao.save(userMotif1);
//			// Motif 2
//			Motif motif2 = new Motif();
//			motif2.setLibelle("bande de nuages");
//			motif2.setDescription(" Il apparaît dans le champ, répété un certain nombre de fois et diversement orienté,"
//					+ " mais toujours associé à d’autres éléments décoratifs floraux, tels que palmettes et arabesques,");
//			Motif returnedMotif2 = motifDao.save(motif2);
//			// User motif 2
//			UserMotif userMotif2 = new UserMotif();
//			MultipartFile multipartFile2 = new MockMultipartFile("motif2.png", new FileInputStream(new File(
//					"C:\\Users\\yassi\\Desktop\\tapsbackend2\\tapisIrisiBackend\\src\\assets\\images\\motif2.png")));
//			byte[] blob2 = multipartFile2.getBytes();
//			userMotif2.setImage(blob2);
//			userMotif2.setUser(returnedUser);
//			userMotif2.setMotif(returnedMotif2);
//			UserMotif returnedUserMotif2 = userMotifDao.save(userMotif2);

		};
	}

}
