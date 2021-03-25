import org.telegram.api.engine.RpcException;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.engine.TimeoutException;
import org.telegram.api.functions.messages.TLRequestMessagesGetAllChats;
import org.telegram.bot.TelegramFunctionCallback;
import org.telegram.bot.kernel.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.tl.TLObject;


public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "YOUR BOT NAME";
    }

    public String getBotToken() {
        return "YOUR BOT TOKEN";
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch (message.getText()){
                // YOU CAN SEND MESSAGES AND BOT SEND TO YOU INFORMATION
                case "/help":
                    sendMsg(message, "HOW CAN I DO FOR YOU?");
                    break;
                case "/setting":
                    sendMsg(message, "WHAT SETTINGS YOU WANT TO CONFIGURE");
                    break;
                default:
            }
        }
    }
    TLRequestMessagesGetAllChats tlRequestMessagesGetAllChats = new TLRequestMessagesGetAllChats();

}
