package ru.dwerd.telegram.keyboard.layout.translator.bot.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dwerd.telegram.keyboard.layout.translator.bot.facade.TelegramFacade;

@Component
@RequiredArgsConstructor
public class TelegramBot {
    private final TelegramFacade telegramFacade;

    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update).orElse(new SendMessage());

    }
}
