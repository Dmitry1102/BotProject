package com.telegram.project;

import com.database.server.AppController;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


@SpringBootApplication
public class App {
    private static final String BOT_NAME = "TestTMS_bot";
    private static final String TOKEN = "1773180686:AAF890sGnfjEil_6ualF8dslmSywbeu7HtI";
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {
        AppController appController = new AppController();

        ApiContextInitializer.init();
        Bot testBot = new Bot(BOT_NAME, TOKEN);
        testBot.botConnect();
        // SpringApplication.run(AppController.class, appController.viewHomePage(testBot));

    }
}

