package ru.dwerd.telegram.keyboard.layout.translator.bot.model;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dwerd.telegram.keyboard.layout.translator.bot.facade.TelegramFacade;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramWebhookBot {

    private   String username;
    private  String token;
    private  String webHookPath;
    private final TelegramFacade telegramFacade;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update).orElse(new SendMessage());

    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
