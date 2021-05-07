package com.telegram.project;
import com.database.server.DataBaseHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


@AllArgsConstructor
@NoArgsConstructor
public class Bot extends TelegramLongPollingBot{
    private static final Logger log = Logger.getLogger(Bot.class);
    final int RECONNECT_PAUSE = 10000;


    String moscow = Cities.MOSCOW.toString();
    String berlin = Cities.BERLIN.toString();
    String warsaw = Cities.WARSAW.toString();
    String london = Cities.LONDON.toString();


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

   private void addToBase(String s){
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        if(s.equals("/Moscow")){
            dataBaseHandler.sign(Cities.MOSCOW.toString(), Information.INFO_MOSCOW);
        }else if (s.equals("/Berlin")){
            dataBaseHandler.sign(Cities.BERLIN.toString(), Information.INFO_BERLIN);
        }else if (s.equals("/Warsaw")){
            dataBaseHandler.sign(Cities.WARSAW.toString(), Information.INFO_WARSAW);
        }else if (s.equals("/London")){
           dataBaseHandler.sign(Cities.LONDON.toString(), Information.INFO_LONDON);
        }
    }




    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Receive new Update. updateID: " + update.getUpdateId());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();


        if (inputText.startsWith("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Hello. This is start message");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (inputText.startsWith("/Moscow")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Moscow is the capital of Russia...");
            addToBase(moscow);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (inputText.startsWith("/Berlin")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Berlin is the capital of Germany...");
            addToBase(berlin);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (inputText.startsWith("/London")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("London is the capital of England...");
           // addToBase(london);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (inputText.startsWith("/Warsaw")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Warsaw is the capital of Poland...");
            addToBase(warsaw);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
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
