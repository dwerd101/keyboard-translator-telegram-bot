package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.buttons.TelegramButtonKeyboard;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.constant.MessageConstants;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;


@Service
@RequiredArgsConstructor
@PropertySource("classpath:message.properties")
public class StartServiceImpl implements HandleMessageService {
    private final TelegramButtonKeyboard telegramButtonKeyboard;
    private final MessageConstants messageConstants;
    @Override
    public BotApiMethod<?> handle(Message message) {
       return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(),messageConstants.getHelloMessage(),
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);

    }

    @Override
    public BotState getBotStateName() {
        return BotState.HELLO;
    }
}
