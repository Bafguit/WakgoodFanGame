package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class SkillButtonPanel extends AbstractUI {

    private static final ShapeRenderer shr = new ShapeRenderer();

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    public SkillButtonType type;
    public AbstractSkill skill;
    public boolean isUsed = false;

    public SkillButtonPanel(SkillButtonType type) {
        super(FileHandler.getUi().get("BORDER_M"));
        this.type = type;
        fontData = type == SkillButtonType.BASIC ? FontHandler.SUB_NAME : FontHandler.CARD_BIG_DESC;
    }

    @Override
    protected void updateButton() {
        if (skill != null) {
            clickable = !skill.passive
                    && skill.canUse()
                    && type != SkillButtonType.VIEW
                    && (!battleScreen.isSelecting ? !ActionHandler.isRunning() : cPanel.battlePanel.selected == skill)
                    && Labyrintale.getCurScreen() == battleScreen;
            if (type == SkillButtonType.PLAYER || type == SkillButtonType.BASIC) {
                isUsed = !skill.canUse();
            }
            if (over) {
                cPanel.infoPanel.setInfo(skill);
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && skill != null) {
            if (isUsed
                    || (type != SkillButtonType.VIEW && !skill.canUse())
                    || (battleScreen.isSelecting && cPanel.battlePanel.selected != skill)) {
                sb.setColor(Color.DARK_GRAY);
            } else if (over && clickable) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            if (!skill.passive) {
                sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                String t = !skill.canUse() ? "&r<" + skill.cost + ">" : Integer.toString(skill.cost);
                FontHandler.renderColorCenter(sb, fontData, t, x + sWidth * 0.05f, y + sWidth * 0.85f, sWidth * 0.2f);
            }
            if (skill.upgradeCount > 0) {
                FontHandler.renderLineRight(
                        sb,
                        fontData,
                        "+" + skill.upgradeCount,
                        x + sWidth * 0.75f,
                        y + sWidth * 0.2f,
                        sWidth * 0.2f,
                        sWidth * 0.2f);
            }
        }
    }

    @Override
    protected void onClick() {
        if (cPanel.battlePanel.selected == skill && battleScreen.gets != null) {
            battleScreen.gets.onTargetSelected(null);
        } else if (!isUsed && skill != null && skill.canUse()) {
            skill.useCard();
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    public enum SkillButtonType {
        PLAYER,
        BASIC,
        VIEW
    }
}
