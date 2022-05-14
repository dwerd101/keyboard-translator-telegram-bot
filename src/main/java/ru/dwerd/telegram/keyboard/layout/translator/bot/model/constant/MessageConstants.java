package ru.dwerd.telegram.keyboard.layout.translator.bot.model.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:message.properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageConstants {
    @Value("${message.englishToRussian}")
    private  String englishToRussianMessage;
    @Value("${message.russianToEnglish}")
    private String russianToEnglishMessage;
    @Value("${message.start}")
    private String helloMessage;
}
