package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class PointBlank extends AbstractSkill {

    private static final String ID = "PointBlank";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 2;

    public PointBlank(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    protected void upgradeCard() {

    }

    @Override
    public int calculateAttack(int a) {
        if(AbstractLabyrinth.cPanel.type == ControlPanel.ControlType.BATTLE) {
            if(Labyrintale.battleScreen.looking.size == 1) {
                AbstractEntity e = Labyrintale.battleScreen.looking.get(0);
                if(!e.isPlayer) {
                    return a + owner.tempIndex + e.tempIndex;
                }
            }
        }
        return a;
    }
}
