package ru.dwerd.telegram.keyboard.layout.translator.services;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;

public interface HandleMessageService {
    BotApiMethod<?> handle(Message message);
    BotState getBotStateName();
}
