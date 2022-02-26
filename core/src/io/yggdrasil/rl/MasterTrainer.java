package io.yggdrasil.rl;

import io.yggdrasil.rl.enemy.model.environment.ActionResult;
import io.yggdrasil.rl.enemy.model.environment.GameEnvironment;
import io.yggdrasil.rl.player.model.training.Trainer;

public class MasterTrainer extends Thread {

    private static MasterTrainer instance;

    private final Trainer firstPlayerTrainer;
    private final io.yggdrasil.rl.enemy.model.training.Trainer secondPlayerTrainer;

    private MasterTrainer() {
        super("MasterTrainer");
        firstPlayerTrainer = new Trainer();
        secondPlayerTrainer = new io.yggdrasil.rl.enemy.model.training.Trainer();
    }

    public static MasterTrainer getInstance() {
        if (instance == null)
            return (instance = new MasterTrainer());
        return instance;
    }

    public Trainer getFirstPlayerTrainer() {
        return firstPlayerTrainer;
    }

    public io.yggdrasil.rl.enemy.model.training.Trainer getSecondPlayerTrainer() {
        return secondPlayerTrainer;
    }

    @Override
    public void run() {

        TurnState.getInstance().start();
        GameEnvironment.getCurrentAction().setActionResult(new ActionResult(0));
        io.yggdrasil.rl.player.model.environment.GameEnvironment.getCurrentAction().setActionResult(new io.yggdrasil.rl.player.model.environment.ActionResult(0));
        firstPlayerTrainer.start();
        secondPlayerTrainer.start();
        interrupt();
    }
}
