package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.buttons.TelegramButtonKeyboard;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.BotContext;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;
import ru.dwerd.telegram.keyboard.layout.translator.common.Translator;

@Service
@RequiredArgsConstructor
@Slf4j
public class RussianToEnglishServiceImpl implements HandleMessageService {
    private final BotContext botContext;
    private final Translator translator;
    private final TelegramButtonKeyboard telegramButtonKeyboard;
    @Override
    public BotApiMethod<?> handle(Message message) {
        log.info("Check this message:{} is command", message.getText());

        if("С русского на английский".equals(message.getText())) {
            botContext.put(message.getFrom().getId(), BotState.RUSSIAN_TO_ENGLISH);
            return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(), "Готово!",
                    TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);
        }
        log.info("This message:{} is not command", message.getText());

        log.info("Add user: {} in botContext with status: {}",message.getFrom().getId(), BotState.RUSSIAN_TO_ENGLISH);

        botContext.put(message.getFrom().getId(), BotState.RUSSIAN_TO_ENGLISH);
        var messageString = translator.getEnglishTranslation(message.getText());

        log.info("Create message for telegram id: {} with keyboard :{}",message.getFrom().getId(),
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);

        return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(), messageString,
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);
    }

    @Override
    public BotState getNameServices() {
        return BotState.RUSSIAN_TO_ENGLISH;
    }
}
