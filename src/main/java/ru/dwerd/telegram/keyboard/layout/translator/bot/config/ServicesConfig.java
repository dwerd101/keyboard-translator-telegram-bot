package ru.dwerd.telegram.keyboard.layout.translator.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.dwerd.telegram.keyboard.layout.translator.bot.state.BotState;
import ru.dwerd.telegram.keyboard.layout.translator.services.HandleMessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan("ru.dwerd.telegram.keyboard.layout.translator.services")
public class ServicesConfig {

    @Bean
    public Map<BotState, HandleMessageService> messageHandlersMap() {
        return new HashMap<>();
    }

}
