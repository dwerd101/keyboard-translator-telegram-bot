package ru.dwerd.telegram.keyboard.layout.translator.bot.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.BotContext;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.constant.MessageConstants;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.impl.BotChooseServiceImpl;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("classpath:message.properties")
public class TelegramFacade {
    private final BotChooseServiceImpl botChooseService;
    private final BotContext botContext;
    private final MessageConstants messageConstants;


    public Optional<BotApiMethod<?>> handleUpdate(final Update update) {
        if (update.getMessage() != null) {
            var message = update.getMessage();

            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());

            return handleInputMessage(update.getMessage());
        }
        return Optional.empty();
    }


    private Optional<BotApiMethod<?>> handleInputMessage(final Message message) {
        final Long userId = message.getFrom().getId();
        BotState services;

        if (message.equals("/start")) {
            botContext.getBotContextMap().put(userId, BotState.HELLO);
            services = BotState.HELLO;
        } else if (message.getText().equals(messageConstants.getEnglishToRussianMessage())) {
            services = BotState.ENGLISH_TO_RUSSIAN;
        } else if (message.getText().equals(messageConstants.getRussianToEnglishMessage())) {
            services = BotState.RUSSIAN_TO_ENGLISH;
        } else {
            services = botContext.getBotContextMap().get(userId);

            if (services == null) {
                services = BotState.HELLO;
                botContext.getBotContextMap().put(userId, BotState.HELLO);
            }
        }
        return Optional.ofNullable(botChooseService.processInputMessage(services, message));
    }
}
