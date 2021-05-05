package com.telegram.project;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {

        ApiContextInitializer.init();
        Bot testBot = new Bot("CityTMS_bot", "1788643120:AAFtrOOYo0ldcKI4c_gsYAUZLv_QFyHfrpg");
        testBot.botConnect();
    }
}

