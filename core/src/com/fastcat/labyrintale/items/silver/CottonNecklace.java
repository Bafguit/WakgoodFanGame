package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class CottonNecklace extends AbstractItem {

    private static final String ID = "CottonNecklace";
    private static final ItemRarity RARITY = ItemRarity.SILVER;

    public CottonNecklace(AbstractPlayer owner) {
        super(ID, owner, RARITY);
    }

    @Override
    public void onGain() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.neutRes += 10;
        }
    }

    @Override
    public void onRemove() {
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            p.stat.neutRes -= 10;
        }
    }

    @Override
    public void atBattleStart() {
        top(new ApplyStatusAction(new EnduranceStatus(4), null, AbstractSkill.SkillTarget.PLAYER_ALL, false));
        flash();
    }
}
