package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.buttons.TelegramButtonKeyboard;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StartServiceImpl implements HandleMessageService {
    private final TelegramButtonKeyboard telegramButtonKeyboard;
    @Override
    public BotApiMethod<?> handle(Message message) throws NoSuchElementException {
        var messageString = """
                Привет, дорогой пользователь! Я помогу тебе перевести правильно на русский или
                на английский с правильной раскладкой. 
                Нажми на кнопку, чтобы выбрать перевод.
                """;
       return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(),messageString,
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);

    }

    @Override
    public BotState getNameServices() {
        return BotState.HELLO;
    }
}
