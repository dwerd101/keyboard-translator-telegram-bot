package ru.dwerd.telegram.keyboard.layout.translator.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;
import java.util.List;
import java.util.Map;

@Service
public class BotChooseServiceImpl {
    private final Map<BotState, HandleMessageService> messageHandlersMap;

    public BotChooseServiceImpl(List<HandleMessageService> messageHandlersList,
                                Map<BotState, HandleMessageService> messageHandlersMap) {
        this.messageHandlersMap = messageHandlersMap;
        messageHandlersList.forEach(handler -> this.messageHandlersMap.put(handler.getNameServices(), handler));
    }

    public BotApiMethod<?> processInputMessage(BotState services, Message message) {
        HandleMessageService currentMessageHandler = findMessageHandler(services);
        return currentMessageHandler.handle(message);
    }

    private HandleMessageService findMessageHandler(BotState currentState) {
        return messageHandlersMap.get(currentState);
    }

}
