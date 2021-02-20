package com.oracle;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.oracle.dao.MotifDao;
import com.oracle.dao.ProprieteDao;
import com.oracle.dao.UserDao;
import com.oracle.models.Role;
import com.oracle.models.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement
public class TapisIrisiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapisIrisiApplication.class, args);
	}
   @Bean
   public CommandLineRunner demo(UserDao userDao,MotifDao motifDao,ProprieteDao proprieteDao) {
	   return (args) -> {
		   User user1 = new User();
		   user1.setLogin("madara123");
		   user1.setNom("madara");
		   user1.setPrenom("prenom");
		   user1.setPassword("12345");
		   user1.setRole(Role.USER);
		   userDao.save(user1);
		   
	   };
   }

}
