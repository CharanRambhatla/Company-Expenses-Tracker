package companyexpensestracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import companyexpensestracker.repository.CategoryRepo;

@SpringBootApplication
public class CompanyexpensestrackerApplication implements CommandLineRunner {

	@Autowired
	CategoryRepo categoryrepo;

	public void run(String... args) throws Exception {

	}

	public static void main(String[] args) {
		SpringApplication.run(CompanyexpensestrackerApplication.class, args);
	}

}
