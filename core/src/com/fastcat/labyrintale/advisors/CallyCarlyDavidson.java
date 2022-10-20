package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.CallyCarlySkill;

public class CallyCarlyDavidson extends AbstractAdvisor {

  public CallyCarlyDavidson() {
    super(AdvisorClass.CALLYCARLY, new CallyCarlySkill());
  }
}
