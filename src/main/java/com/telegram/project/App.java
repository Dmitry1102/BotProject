package com.telegram.project;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {

        ApiContextInitializer.init();
        Bot testBot = new Bot("CityCheckFin_bot", "1734832073:AAGSLCDuvW5Q4mKtXUdW5Puut6dP4aBvBvs");
        testBot.botConnect();
    }
}

