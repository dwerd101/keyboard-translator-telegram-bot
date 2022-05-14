package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.buttons.TelegramButtonKeyboard;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:message.properties")
public class StartServiceImpl implements HandleMessageService {
    private final TelegramButtonKeyboard telegramButtonKeyboard;
    @Value("${message.start}")
    private String helloMessage;
    @Override
    public BotApiMethod<?> handle(Message message) throws NoSuchElementException {
       return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(),helloMessage,
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);

    }

    @Override
    public BotState getNameServices() {
        return BotState.HELLO;
    }
}
