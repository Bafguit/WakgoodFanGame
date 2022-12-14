//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import lombok.Getter;

public final class RandomXC {
    private RandomXS128 random;
    private long seed;

    @Getter
    private int counter;

    public RandomXC() {
        this(MathUtils.random(9999L), MathUtils.random(99));
    }

    public RandomXC(Long seed) {
        this.counter = 0;
        this.seed = seed;
        this.random = new RandomXS128(seed);
    }

    public RandomXC(Long seed, int counter) {
        this.counter = 0;
        this.seed = seed;
        this.random = new RandomXS128(seed);

        for (int i = 0; i < counter; ++i) {
            this.random(999);
        }
    }

    public RandomXC copy() {
        RandomXC copied = new RandomXC();
        copied.random = new RandomXS128(this.random.getState(0), this.random.getState(1));
        copied.seed = seed;
        copied.counter = counter;
        return copied;
    }

    public void setCounter(int targetCounter) {
        if (this.counter < targetCounter) {
            int count = targetCounter - this.counter;

            for (int i = 0; i < count; ++i) {
                this.randomBoolean();
            }
        } else {
            System.err.println("ERROR: Counter is already higher than target counter!");
        }
    }

    public int random(int range) {
        ++this.counter;
        return this.random.nextInt(range + 1);
    }

    public int random(int start, int end) {
        ++this.counter;
        return start + this.random.nextInt(end - start + 1);
    }

    public long random(long range) {
        ++this.counter;
        return (long) (this.random.nextDouble() * (double) range);
    }

    public long random(long start, long end) {
        ++this.counter;
        return start + (long) (this.random.nextDouble() * (double) (end - start));
    }

    public long randomLong() {
        ++this.counter;
        return this.random.nextLong();
    }

    public boolean randomBoolean() {
        ++this.counter;
        return this.random.nextBoolean();
    }

    public boolean randomBoolean(float chance) {
        ++this.counter;
        return this.random.nextFloat() < chance;
    }

    public float random() {
        ++this.counter;
        return this.random.nextFloat();
    }

    public float random(float range) {
        ++this.counter;
        return this.random.nextFloat() * range;
    }

    public float random(float start, float end) {
        ++this.counter;
        return start + this.random.nextFloat() * (end - start);
    }
}
