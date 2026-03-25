package com.juanCarlos.hardwareHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class HardwareHubApplication {

	public static void main(String[] args) {
		/*
		 * Fija el nombre del fichero de log UNA SOLA VEZ por arranque.
		 * Esto se hace asi para evitar problemas de archivos duplicados de log.
		 */
		if (System.getProperty("app.log.file") == null) {
			String timestamp = LocalDateTime.now()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
			System.setProperty("app.log.file",
					System.getProperty("user.dir") + "/log/log_" + timestamp + ".txt");
		}
		SpringApplication.run(HardwareHubApplication.class, args);
	}
}
