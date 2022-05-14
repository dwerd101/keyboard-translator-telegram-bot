package ru.dwerd.telegram.keyboard.layout.translator.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.dwerd.telegram.keyboard.layout.translator.common.EnglishRussianKeyboard.*;

@Component
@Slf4j
public class Translator {

    public String getRussianTranslation(String text) {

        var stringMassive = text.split("");
        AtomicInteger count = new AtomicInteger(0);
        List<Character> russianList = new ArrayList<>();

        log.info("Create \"englishKeyboardList\"");
        var englishKeyboardList = Arrays.stream(stringMassive)
                .map(x -> EnglishRussianKeyboard.getEnglishKeyboard(Character.toLowerCase(x.charAt(0))))
                .toList();

        log.info("Created \"englishKeyboardList\"");
        log.info("Call method: \"changeTextWithUpperOrLowerCaseRussianTranslation\"");
        changeTextWithUpperOrLowerCaseRussianTranslation(russianList, englishKeyboardList, stringMassive, count);

        return listToString(russianList);
    }

    public String getEnglishTranslation(String text) {
        var stringMassive = text.split("");
        AtomicInteger count = new AtomicInteger(0);
        List<Character> englishList = new ArrayList<>();

        log.info("Create \"englishKeyboardList\"");
        var russianKeyboardList = Arrays.stream(stringMassive)
                .map(x -> EnglishRussianKeyboard.getRussianKeyboard(Character.toLowerCase(x.charAt(0))))
                .toList();

        log.info("Created \"englishKeyboardList\"");
        log.info("Call method: \"changeTextWithUpperOrLowerCaseEnglishTranslation\"");
        changeTextWithUpperOrLowerCaseEnglishTranslation(englishList, russianKeyboardList, stringMassive, count);
        return listToString(englishList);
    }

    private void changeTextWithUpperOrLowerCaseRussianTranslation(List<Character> russianList,
                                                                  List<EnglishRussianKeyboard> englishKeyboardList,
                                                                  String[] stringMassive, AtomicInteger count) {


        englishKeyboardList.forEach(
                x -> {
                    if (Character.isUpperCase(stringMassive[count.get()].charAt(0))) {
                        russianList.add(Character.toUpperCase(x.getSymbol()));
                        count.incrementAndGet();
                    } else {
                        russianList.add(x.getSymbol());
                        count.incrementAndGet();
                    }
                }
        );
        count.set(0);
    }

    private void changeTextWithUpperOrLowerCaseEnglishTranslation(List<Character> englishList,
                                                                  List<EnglishRussianKeyboard> russianKeyboardList,
                                                                  String[] stringMassive,
                                                                  AtomicInteger count) {
        russianKeyboardList.forEach(
                x -> {
                    if (x.equals(SPACE) || x.equals(EXCLAMATION_POINT) || x.equals(COMMA) || x.equals(POINT)) {
                        englishList.add(x.getSymbol());
                        count.incrementAndGet();
                    } else if (Character.isUpperCase(stringMassive[count.get()].charAt(0))) {
                        englishList.add(Character.toUpperCase(x.name().charAt(0)));
                        count.incrementAndGet();
                    } else {
                        englishList.add(Character.toLowerCase(x.name().charAt(0)));
                        count.incrementAndGet();
                    }
                }
        );
        count.set(0);
    }

    private <T> String listToString(List<T> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
