package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.strings.ChoiceString;
import com.fastcat.labyrintale.strings.SkillString;
import com.fastcat.labyrintale.strings.CharString;
import com.fastcat.labyrintale.strings.StatusString;

public class StringHandler {

    public static SkillString skillString;
    public static StatusString statusString;
    public static CharString charString;
    public static ChoiceString choiceString;

    public static void generate() {
        skillString = new SkillString();
        statusString = new StatusString();
        charString = new CharString();
        choiceString = new ChoiceString();
    }
}
