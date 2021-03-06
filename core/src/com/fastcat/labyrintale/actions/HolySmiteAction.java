package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.handlers.*;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;

public class HolySmiteAction extends AbstractAction {

    public AbstractEntity.DamageInfo info;
    public int heal;

    public HolySmiteAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, int heal) {
        super(actor, target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            SoundHandler.playSfx("ATTACK_TEST");
            if (target.size > 0) {
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
                if (SettingHandler.setting.shake)
                    Labyrintale.getScreenShake().shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, FileHandler.getVfx().get("LIGHTNING")));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) te.takeDamage(info);
                }
                Array<AbstractPlayer> temp = new Array<>();
                int low = 2147483647;
                for (int i = 0; i < 4; i++) {
                    AbstractPlayer p = AbstractLabyrinth.players[i];
                    if (p.isAlive() && p.health < low) low = p.health;
                }
                for (AbstractPlayer p : AbstractLabyrinth.players) {
                    if (p.health == low) temp.add(p);
                }
                for (int i = 0; i < temp.size; i++) {
                    AbstractEntity te = temp.get(i);
                    EffectHandler.add(new UpDamageEffect(te.ui.x + te.ui.sWidth / 2, te.ui.y + te.ui.sHeight * 0.35f, heal, CHARTREUSE, false));
                    te.heal(actor != null ? actor.calculateSpell(heal) : heal);
                }
            } else isDone = true;
        }
    }
}
