package com.ksulima;

import com.ksulima.dto.Uzyszkodnik;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);


		Uzyszkodnik user = new Uzyszkodnik("Jan", "Kowalski");

		user.toString();



	}
}
