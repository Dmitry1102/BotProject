package com.telegram.project;
import com.database.server.CityService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Bot extends TelegramLongPollingBot{
    private static final Logger log = Logger.getLogger(Bot.class);
    final int RECONNECT_PAUSE = 10000;
    InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
    @Autowired
    private CityService cityService;

    @Setter
    @Getter
    private String userName;
    @Setter
    @Getter
    private String token;

    public Bot(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public Bot(DefaultBotOptions options, String userName, String token) {
        super(options);
        this.userName = userName;
        this.token = token;
    }

    public SendMessage  sendInlineKeyBoardMessage(long chatId){
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1.setText("Moscow").setCallbackData("Moscow is the capital of Russia"));
//        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
//        keyboardButtonsRow2.add(inlineKeyboardButton2.setText("Warsaw").setCallbackData("Warsaw is the capital of Poland"));
//        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
//        keyboardButtonsRow3.add(inlineKeyboardButton3.setText("Berlin").setCallbackData("Berlin is the capital of Germany"));
//        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
//        keyboardButtonsRow4.add(inlineKeyboardButton4.setText("London").setCallbackData("London is the capital of England"));


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText("City").setReplyMarkup(inlineKeyboardMarkup);

    }


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        SendMessage mes = new SendMessage();

        if (updates != null && !updates.isEmpty()) {
            Update update = updates.get(0);
            String message = update.getMessage().getText();
            mes.setChatId(update.getMessage().getText());
            if (!message.startsWith("/start")) {
                String brand = cityService.getCityByName(message);
                mes.setText(brand);
                if (brand != null) {
                    try{
                        execute(mes);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    try{
                        mes.setText("Zero truth info");
                        execute(mes);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onClosing() {
        super.onClosing();
    }


    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }




}
