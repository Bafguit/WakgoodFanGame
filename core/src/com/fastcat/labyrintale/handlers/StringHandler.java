package com.fastcat.labyrintale.handlers;

import com.fastcat.labyrintale.strings.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringHandler {

  public static SkillString skillString;
  public static StatusString statusString;
  public static CharString charString;
  public static ChoiceString choiceString;
  public static EventString eventString;
  public static ItemString itemString;
  public static AdvisorString advisorString;
  public static KeyString keyString;
  public static RiskString riskString;

  public static void generate() {
    skillString = new SkillString();
    statusString = new StatusString();
    charString = new CharString();
    choiceString = new ChoiceString();
    eventString = new EventString();
    itemString = new ItemString();
    advisorString = new AdvisorString();
    keyString = new KeyString();
    riskString = new RiskString();
  }
}
