package com.example.tgbot;

import com.pengrad.telegrambot.TelegramBot;


class Main {
    public static void main(String[] args) {
        var botToken = System.getenv().get("BOT_TOKEN");
        var bot = new Bot(new TelegramBot(botToken));
        System.out.println("Bot has started");
    }
}
