package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.DopaSkill;

public class DrDopamine extends AbstractAdvisor {

  public DrDopamine() {
    super(AdvisorClass.DOPA, new DopaSkill());
  }
}
