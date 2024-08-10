package com.subham.lld;

import com.subham.lld.librarymanagement.service.AccountService;
import com.subham.lld.librarymanagement.service.impl.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LldApplication implements ApplicationRunner {

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;

	@Autowired
	private LibrarianService librarianService;


	public static void main(String[] args) {
		SpringApplication.run(LldApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(accountService);
		System.out.println(librarianService);
	}
}
