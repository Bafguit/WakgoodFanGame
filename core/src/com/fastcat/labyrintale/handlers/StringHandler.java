package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.strings.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringHandler {

  public static SkillString skillString;
  public static StatusString statusString;
  public static CharString charString;
  public static EnemyString enemyString;
  public static ChoiceString choiceString;
  public static EventString eventString;
  public static ItemString itemString;
  public static KeyString keyString;
  public static AchieveString achvString;

  public static void generate() {
    skillString = new SkillString();
    statusString = new StatusString();
    charString = new CharString();
    enemyString = new EnemyString();
    choiceString = new ChoiceString();
    eventString = new EventString();
    itemString = new ItemString();
    keyString = new KeyString();
    achvString = new AchieveString();
  }
}
