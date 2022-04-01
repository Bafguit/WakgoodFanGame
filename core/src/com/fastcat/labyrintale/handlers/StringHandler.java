package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.strings.*;

public class StringHandler {

    public static SkillString skillString;
    public static StatusString statusString;
    public static CharString charString;
    public static ChoiceString choiceString;
    public static EventString eventString;

    public static void generate() {
        skillString = new SkillString();
        statusString = new StatusString();
        charString = new CharString();
        choiceString = new ChoiceString();
        eventString = new EventString();
    }
}
