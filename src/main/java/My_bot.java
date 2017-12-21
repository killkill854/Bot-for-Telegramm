import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class My_bot extends TelegramLongPollingBot{
int day = 0;
User user;
boolean codeMode;
boolean eat;
int pizza = 2;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            sendText(text, chatId);
            if (codeMode == true){
                user.dollars += text.length();
                nextDay(chatId);
                codeMode = false;
            }else if (eat == true){
               int count = Integer.parseInt(text);
                user.dollars -= count * pizza;
                nextDay(chatId);
            }
            else  if (text.equals("/newgame")){
                newGame(chatId);
            }else if (text.equals("/cod")) {
                codeMode = true;
                sendText("Ваш код на сегодня ",chatId);
            }else if (text.equals("/eda")){
                eat = true;
                sendText("Сколько кусков пиццы?", chatId);

            }
        }else if (update.getMessage().hasPhoto()){
            String photo = update.getMessage().getPhoto().get(0).getFileId();
            long chatId = update.getMessage().getChatId();
            sendPhoto(photo, chatId);
        }
    }

    private void newGame(long chatId) {
        day = 0;
        user = new User();
        nextDay(chatId);
    }

    private void nextDay(long chatId){
        day++;
        sendText("Day number "+ day, chatId);
        sendTextKeyboard(user.getInfo(), chatId, "/code", "/eda");
    }

    @Override
    public String getBotUsername() {
        return "HaHaBoBobot";
    }

    @Override
    public String getBotToken() {
        return "509031536:AAEi9LUBlvsCN9U-oqhtA9y9U-4HpU4QT_c";
    }


    private void sendText(String text, long chatId){
        SendMessage request = new SendMessage();
        request.setText(text);
        request.setChatId(chatId);

        try {
            execute(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    } private void sendTextKeyboard(String text, long chatId, String... buttonNames){

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        for (int i   = 0 ; i<buttonNames.length; i++){
            firstRow.add(new KeyboardButton(buttonNames[i]));
        }
        firstRow.add(new KeyboardButton("ывывпа"));
        firstRow.add(new KeyboardButton("Кукусики"));
        keyboard.add(firstRow);
        markup.setKeyboard(keyboard);



        SendMessage request = new SendMessage();
        request.setText(text);
        request.setChatId(chatId);
        request.setReplyMarkup(markup);

        try {
            execute(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(String photo, long chatId){
        SendPhoto request = new SendPhoto();
        request.setPhoto(photo);
        request.setChatId(chatId);

        try {
            sendPhoto(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
