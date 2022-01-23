package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.StatusSelfDamageAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;

public class ObjectiveEyeStatus extends AbstractStatus {

    private static final String ID = "ObjectiveEye";

    public ObjectiveEyeStatus(int amount) {
        super(ID, new Sprite(FileHandler.SKILL_SPIKE), null, NONE);
        name = "객관안";
        desc = "턴 시작 시 피해를 받습니다.";
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return "턴 시작 시 피해를 &y<" + amount + "> 받습니다.";
    }

    @Override
    public void startOfTurn() {
        ActionHandler.bot(new StatusSelfDamageAction(this, true));
    }
}
