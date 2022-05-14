package ru.dwerd.telegram.keyboard.layout.translator.bot.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static ru.dwerd.telegram.keyboard.layout.translator.bot.buttons.TelegramButtonKeyboard.Buttons.MENU_KEYBOARD;

@Component
@RequiredArgsConstructor
public class TelegramButtonKeyboard {

    public enum Buttons {
        MENU_KEYBOARD
    }

    public SendMessage createMessageWithKeyboard(final long chatId,
                                                 String textMessage, Buttons buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = null;
        if (buttons.equals(MENU_KEYBOARD)) {
            replyKeyboardMarkup = getChooseTranslateKeyboard();
        }
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }


    private ReplyKeyboardMarkup getChooseTranslateKeyboard() {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();

        row1.add(new KeyboardButton("С английского на русский"));
        row1.add(new KeyboardButton("С русского на английский"));

        keyboard.add(row1);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
