package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.rewards.ItemReward;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.strings.KeyString;

public class RewardItemButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final Sprite image;
    public AbstractReward reward;
    public SkillReward sReward;
    public ItemReward iReward;
    private RewardItemCharIcon icon;

    public RewardItemButton(AbstractReward re) {
        super(FileHandler.getUi().get("BORDER"));
        image = re.img;
        this.reward = re;
        if (reward.type == AbstractReward.RewardType.SKILL) {
            sReward = (SkillReward) reward;
            icon = new RewardItemCharIcon(sReward);
        } else if (reward.type == AbstractReward.RewardType.ITEM) {
            iReward = (ItemReward) reward;
        }

        fontData = FontHandler.SUB_NAME;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (reward.isDone) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(image, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            if (reward.type == AbstractReward.RewardType.SKILL) {
                sb.draw(icon.item.skill.owner.imgTiny, x + sWidth - icon.sWidth, y, icon.sWidth, icon.sHeight);
                sb.draw(icon.img, x + sWidth - icon.sWidth, y, icon.sWidth, icon.sHeight);

                sb.setColor(Color.WHITE);
                if(!icon.item.skill.passive) {
                    sb.draw(cost, x - sWidth * 0.2f, y + sWidth * 0.7f, sWidth * 0.5f, sWidth * 0.5f);
                    FontHandler.renderCenter(sb, fontData, Integer.toString(icon.item.skill.cost), x - sWidth * 0.05f, y + sWidth * 0.95f, sWidth * 0.2f, sWidth * 0.2f);
                }
            }
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if (over) {
            if (reward.type == AbstractReward.RewardType.SKILL)
                AbstractLabyrinth.cPanel.infoPanel.setInfo(sReward.skill);
            else AbstractLabyrinth.cPanel.infoPanel.setInfo(reward.name, reward.desc);
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected Array<SubText> getSubText() {
        if(reward.type == AbstractReward.RewardType.SKILL) {
            return sReward.skill.key;
        } else if(reward.type == AbstractReward.RewardType.ITEM) {
            return iReward.item.key;
        } else if(reward.type == AbstractReward.RewardType.EXP) {
            return subs;
        }

        return null;
    }

    @Override
    protected void onClick() {
        if (!reward.isDone) {
            reward.takeReward();
            reward.isDone = true;
        }
    }
}
