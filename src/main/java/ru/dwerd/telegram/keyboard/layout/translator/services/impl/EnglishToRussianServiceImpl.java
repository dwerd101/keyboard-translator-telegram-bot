package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import lombok.RequiredArgsConstructor;
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
public class EnglishToRussianServiceImpl implements HandleMessageService {
    private final BotContext botContext;
    private final Translator translator;
    private final TelegramButtonKeyboard telegramButtonKeyboard;

    @Override
    public BotApiMethod<?> handle(Message message) {

        if("С английского на русский".equals(message.getText())) {
            botContext.put(message.getFrom().getId(), BotState.ENGLISH_TO_RUSSIAN);
            return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(), "Готово!",
                    TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);
        }
        botContext.put(message.getFrom().getId(), BotState.ENGLISH_TO_RUSSIAN);
        var messageString = translator.getRussianTranslation(message.getText());

        return telegramButtonKeyboard.createMessageWithKeyboard(message.getFrom().getId(), messageString,
                TelegramButtonKeyboard.Buttons.MENU_KEYBOARD);
    }

    @Override
    public BotState getNameServices() {
        return BotState.ENGLISH_TO_RUSSIAN;
    }
}
