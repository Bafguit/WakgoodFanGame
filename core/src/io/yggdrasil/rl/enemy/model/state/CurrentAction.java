package io.yggdrasil.rl.enemy.model.state;

import io.yggdrasil.rl.enemy.model.environment.ActionResult;

public class CurrentAction {
    private ActionResult actionResult;

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }
}
