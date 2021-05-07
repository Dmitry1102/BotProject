package com.telegram.project;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot testBot = new Bot("TestTMS_bot", "1773180686:AAF890sGnfjEil_6ualF8dslmSywbeu7HtI");
        testBot.botConnect();
        SpringApplication.run(App.class, args);
    }
}

