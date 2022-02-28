package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.strings.ChoiceString;
import com.fastcat.labyrintale.strings.SkillString;
import com.fastcat.labyrintale.strings.CharString;

public class StringHandler {

    public static SkillString skillString;
    public static CharString charString;
    public static ChoiceString choiceString;

    public static void generate() {
        skillString = new SkillString();
        charString = new CharString();
        //choiceString = new ChoiceString();
    }
}
