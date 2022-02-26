package io.yggdrasil.rl.player.model.training;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.event.EventBus;
import com.fastcat.labyrintale.event.events.actions.EntityActionEvent;
import com.fastcat.labyrintale.event.events.entity.EntityDeathEvent;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import io.yggdrasil.rl.player.model.GameListener;
import io.yggdrasil.rl.player.model.agent.GameAgent;
import io.yggdrasil.rl.player.model.state.GameObservationSpace;
import io.yggdrasil.rl.player.model.state.GameState;
import org.deeplearning4j.rl4j.learning.IEpochTrainer;
import org.deeplearning4j.rl4j.learning.ILearning;
import org.deeplearning4j.rl4j.learning.configuration.QLearningConfiguration;
import org.deeplearning4j.rl4j.learning.listener.TrainingListener;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.deeplearning4j.rl4j.network.configuration.DQNDenseNetworkConfiguration;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.deeplearning4j.rl4j.policy.DQNPolicy;
import org.deeplearning4j.rl4j.util.IDataManager;
import org.nd4j.linalg.learning.config.Adam;

import java.io.IOException;

public class Trainer extends Thread {
    public static final int MAX_STEP_BY_EPOCH = 100;
    public static final int MAX_STEP = 1000;
    private GameAgent player;

    public Trainer(){
        super("Player Trainer");
    }

    @Override
    public void run() {
        GameListener listener = new GameListener();
        EventBus.getInstance().registerListener(EntityActionEvent.class, listener);
        EventBus.getInstance().registerListener(EntityDeathEvent.class, listener);
        startDQNTraining();
    }

    private void startDQNTraining() {
        player = new GameAgent();
        train(player);
    }

    public GameAgent getPlayer() {
        return player;
    }

    private void train(GameAgent agent) {
        QLearningConfiguration qlConfig =
                QLearningConfiguration.builder()
                        .seed(1234L)
                        .maxEpochStep(MAX_STEP_BY_EPOCH)
                        .maxStep(MAX_STEP)
                        .expRepMaxSize(5000)
                        .batchSize(32)
                        .targetDqnUpdateFreq(100)
                        .updateStart(0)
                        .rewardFactor(1)
                        .gamma(0.95)
                        .errorClamp(3.0)
                        .minEpsilon(0.01f)
                        .epsilonNbStep(10000)
                        .doubleDQN(false)
                        .build();

        DQNFactoryStdDense qlNet =
                new DQNFactoryStdDense(
                        DQNDenseNetworkConfiguration.builder()
                                .numLayers(3)
                                .numHiddenNodes(16)
                                .l2(0.0001)
                                .updater(new Adam())
                                .build()
                );


        QLearningDiscreteDense<GameState> dqn = new QLearningDiscreteDense<GameState>(agent, qlNet, qlConfig);

        dqn.addListener(new TrainingListener() {
            @Override
            public ListenerResponse onTrainingStart() {
                return ListenerResponse.CONTINUE;
            }

            @Override
            public void onTrainingEnd() {

            }

            @Override
            public ListenerResponse onNewEpoch(IEpochTrainer trainer) {
                return ListenerResponse.CONTINUE;
            }

            @Override
            public ListenerResponse onEpochTrainingResult(IEpochTrainer trainer, IDataManager.StatEntry statEntry) {
                GameAgent.getLogger().info(String.format("Epoch %d  completed and episode count is %d.",trainer.getEpochCount(),trainer.getEpisodeCount()));
                return ListenerResponse.CONTINUE;
            }

            @Override
            public ListenerResponse onTrainingProgress(ILearning learning) {

                return ListenerResponse.CONTINUE;
            }
        });
        //Wait for battle screen
        while (!(Labyrintale.getCurScreen() instanceof BattleScreen)) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        dqn.train();
        try {
            dqn.getQNetwork().save("playerModel.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        agent.close();
    }
}
