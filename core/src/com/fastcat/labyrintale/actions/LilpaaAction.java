package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.ALL;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.*;

public class LilpaaAction extends AbstractAction {

    public boolean ps = false;
    public AbstractEffect effect;
    public AbstractEntity.DamageInfo info;
    public int burn;

    public LilpaaAction(AbstractSkill s) {
        super(s.owner, ALL, 2.0f);
        info = new AbstractEntity.DamageInfo(actor, s.attack);
        burn = s.value;
    }

    @Override
    protected void applySetting() {}

    @Override
    protected void updateAction() {
        if (duration < 1.1f && !ps) {
            if (actor != null) {
                AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                actor.state.addAnimation(0, "idle", true, 0.0F);
                e.setTimeScale(1.0f);
            }
            ps = true;
            if (SettingHandler.setting.shake)
                Labyrintale.getScreenShake().shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.LONG, false);
            for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                if (e.isAlive())
                    EffectHandler.add(new HitEffect(e, FileHandler.getVfx().get("LIGHTNING")));
            }
            for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
                if (e.isAlive()) e.takeDamage(info);
            }
        }
    }
}
