package io.yggdrasil.rl.player.model.state;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import io.yggdrasil.rl.player.model.environment.GameEnvironment;
import org.deeplearning4j.rl4j.space.Encodable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;

public class GameState implements Encodable {

    private final INDArray state;

    public GameState(List<AbstractEntity> allies, List<AbstractEntity> enemies) {
        state = Nd4j.zeros(20);
        for (int i = 0; i < allies.size(); i++) {
            AbstractEntity ally = allies.get(i);
            state.putScalar(i, ally.health);
            for (int j = 0; j < ally.hand.length; j++)
                state.putScalar(j + i + 1, GameEnvironment.encodeSkill(ally.hand[j]));
        }
        for (int i = 8; i < enemies.size(); i++)
            state.putScalar(i, enemies.get(i).health);
    }


    /**
     * @deprecated
     */
    @Override
    public double[] toArray() {
        return state.toDoubleVector();
    }

    @Override
    public boolean isSkipped() {
        return false;
    }

    @Override
    public INDArray getData() {
        return state;
    }

    @Override
    public Encodable dup() {
        return null;
    }

}
