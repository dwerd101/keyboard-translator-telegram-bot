package ru.dwerd.telegram.keyboard.layout.translator.bot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Data
public class BotContext {
    private final Map<Long, BotState> botContextMap;
    public void put(Long telegramId, BotState services) {
        botContextMap.put(telegramId,services);
    }
}
