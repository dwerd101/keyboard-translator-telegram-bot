package ru.dwerd.telegram.keyboard.layout.translator.bot.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dwerd.telegram.keyboard.layout.translator.bot.model.BotContext;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.impl.BotChooseServiceImpl;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramFacade {

    private final BotChooseServiceImpl botChooseService;
    private final BotContext botContext;

    public Optional<BotApiMethod<?>> handleUpdate(Update update) {
        if (update.getMessage() != null) {
            var message = update.getMessage();

            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());

            return handleInputMessage(update.getMessage());
        }
        return Optional.empty();
    }


    private Optional<BotApiMethod<?>> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        final Long userId = message.getFrom().getId();
        BotState services;

        switch (inputMsg) {
            case "/start" -> {
                botContext.getBotContextMap().put(userId, BotState.HELLO);
                services = BotState.HELLO;
            }
            case "С английского на русский" -> services = BotState.ENGLISH_TO_RUSSIAN;
            case "С русского на английский" -> services = BotState.RUSSIAN_TO_ENGLISH;
            default -> {
                services = botContext.getBotContextMap().get(userId);

                if (services == null) {
                    services = BotState.HELLO;
                    botContext.getBotContextMap().put(userId, BotState.HELLO);
                }

            }
        }
        return Optional.ofNullable(botChooseService.processInputMessage(services, message));
    }
}
