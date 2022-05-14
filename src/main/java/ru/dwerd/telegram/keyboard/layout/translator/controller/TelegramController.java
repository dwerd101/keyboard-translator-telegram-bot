package ru.dwerd.telegram.keyboard.layout.translator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.TelegramBot;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dwerd/translator")
public class TelegramController {
    private final TelegramBot telegramBot;
    @PostMapping("/")
    public BotApiMethod<?> updateMessage(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
