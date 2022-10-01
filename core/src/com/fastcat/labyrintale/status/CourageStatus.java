package com.fastcat.labyrintale.status;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.ReduceStatusAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;

public class CourageStatus extends AbstractStatus {

    private static final String ID = "Courage";
    private boolean used = false;

    public CourageStatus(int amount) {
        super(ID, AbstractSkill.SkillTarget.NONE, StatusType.BUFF);
        setAmount(amount);
    }

    @Override
    public void onInitial() {
        used = false;
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void onAttack(AbstractEntity e, int dmg, AbstractEntity.DamageType type) {
        if(!used) {
            used = true;
            flash();
            bot(new ReduceStatusAction(this, amount, StatusType.STATIC, true));
        }
    }

    @Override
    public int showAttack(int base) {
        return base + amount;
    }
}
