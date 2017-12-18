import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class My_bot extends TelegramLongPollingBot{
int day = 0;
User user;
boolean codeMode;

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
            }
            else  if (text.equals("/newgame")){
                newGame(chatId);
            }else if (text.equals("/money")) {
                codeMode = true;
                sendText("Ваш код на сегодня ",chatId);
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
        sendText(user.getInfo(), chatId);
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
