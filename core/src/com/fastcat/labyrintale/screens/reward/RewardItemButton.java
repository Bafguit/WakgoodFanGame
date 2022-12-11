package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.rewards.ItemReward;
import com.fastcat.labyrintale.rewards.SkillReward;

public class RewardItemButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final Sprite image;
    public AbstractReward reward;
    public SkillReward sReward;
    public ItemReward iReward;

    public RewardItemButton(AbstractReward re) {
        super(FileHandler.getUi().get("BORDER"));
        image = re.img;
        this.reward = re;
        if (reward.type == AbstractReward.RewardType.SKILL) {
            sReward = (SkillReward) reward;
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
            if (reward.type == AbstractReward.RewardType.SKILL) {
                if(sReward.skill.owner != null) sb.setColor(sReward.skill.owner.pColor);
                else sb.setColor(Color.WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
                sb.setColor(Color.WHITE);

                if (!sReward.skill.passive) {
                    sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                    FontHandler.renderCenter(
                            sb,
                            fontData,
                            Integer.toString(sReward.skill.cost),
                            x + sWidth * 0.05f,
                            y + sWidth * 0.85f,
                            sWidth * 0.2f,
                            sWidth * 0.2f);
                }
            } else if (reward.type == AbstractReward.RewardType.ITEM) {
                sb.setColor(
                        iReward.type == ItemReward.ItemRewardType.BOSS
                                ? AbstractItem.getRarityColor(AbstractItem.ItemRarity.BOSS)
                                : iReward.item.getRarityColor());
                sb.draw(img, x, y, sWidth, sHeight);
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
    protected void onOver() {}

    @Override
    protected Array<SubText> getSubText() {
        if (reward.type == AbstractReward.RewardType.SKILL) {
            return sReward.skill.key;
        } else if (reward.type == AbstractReward.RewardType.ITEM && iReward.type != ItemReward.ItemRewardType.BOSS) {
            return iReward.item.key;
        } else if (reward.type == AbstractReward.RewardType.EXP) {
            return subs;
        }

        return subTexts;
    }

    @Override
    protected void onClick() {
        if (!reward.isDone) {
            reward.takeReward();
        }
    }
}
