package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.RuseokSkill;

public class Haeruseok extends AbstractAdvisor {

  public Haeruseok() {
    super(AdvisorClass.RUSEOK, new RuseokSkill());
  }
}
