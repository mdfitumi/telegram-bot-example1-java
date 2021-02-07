package com.example.tgbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;
import java.util.stream.Collectors;

sealed interface ProcessUpdateStatus permits Ok, Err {}
record Ok() implements ProcessUpdateStatus {}
record Err(String reason) implements ProcessUpdateStatus {}


public class Bot {
    private final TelegramBot telegram;

    public Bot(TelegramBot telegram) {
        this.telegram = telegram;
        this.telegram.setUpdatesListener(this::onTelegramUpdatesReceived);
    }

    private int onTelegramUpdatesReceived(List<Update> updates) {
        var errors = updates
                .stream()
                .map(this::processTelegramUpdate)
                .filter(result -> result instanceof Err);
        if (errors.count() > 0) {
            System.out.println(errors.collect(Collectors.toList()));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private ProcessUpdateStatus processTelegramUpdate(Update update) {
        try {
            System.out.printf("processed message %s%n", update.message().text());
            telegram.execute(new SendMessage(update.message().chat().id(), "got your message!"));
            return new Ok();
        }
        catch (Exception err) {
            return new Err(err.getMessage());
        }
    }
}
