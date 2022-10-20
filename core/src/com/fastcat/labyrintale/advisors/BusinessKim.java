package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.BusinessSkill;

public class BusinessKim extends AbstractAdvisor {

  public BusinessKim() {
    super(AdvisorClass.BUSINESS, new BusinessSkill());
  }
}
