package com.example.utils;

import java.util.UUID;
import com.example.utils.RandomUtils;

public class RandomUtils {

    public static String randomText(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    public static String randomEmail() {
        return "user_" + randomText() + "@test.com";
    }

    public static String randomName() {
        return "name_" + randomText();
    }

    public static String randomPassword() {
        return "pass"+randomText();
    }

    public static String randomFalseEmail() {
        return "user_" + randomText() + "@falsee.com";
    }

    public static String randomRussianEmail() {
        String[] names = {"иван", "пётр", "светлана", "анна", "алексей", "юлия", "дмитрий"};
        String[] domains = {"mail.ru", "yandex.ru", "почта.рф"};

        String name = names[(int) (Math.random() * names.length)];
        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        String domain = domains[(int) (Math.random() * domains.length)];

        return name + suffix + "@" + domain;
    }

    public static String randomEmailSpaceBefore() {
        return " user_" + randomText() + "@test.com";
    }

    public static String randomEmailSpaceAfter() {
        return " user_" + randomText() + "@test.com ";
    }

    public static String randomRussianName() {
        String[] names = {"иван", "пётр", "светлана", "анна", "алексей", "юлия", "дмитрий"};

        String name = names[(int) (Math.random() * names.length)];
        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 4);

        return name + suffix;
    }

    public static String randomNameWithSpetialSymbols(){
        return "name_" + randomText() + "@)(*&^%$#@!#@?>?)";
    }

    public static String random51Symbol(){
        return "name_" + randomText() + randomText() + randomText() + randomText() + "name_e";
    }
}