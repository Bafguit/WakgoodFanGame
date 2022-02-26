package io.yggdrasil.rl.enemy.model.environment;

public class ActionResult {

    private double reward;
    private int action;
    private int actionIndex;

    public ActionResult(double reward) {
        this.reward = reward;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

}
