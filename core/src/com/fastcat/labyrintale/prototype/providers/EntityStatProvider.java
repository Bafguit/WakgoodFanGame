package com.fastcat.labyrintale.prototype.providers;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.prototype.ConfigurationProvider;
import com.fastcat.labyrintale.prototype.tracker.Tracker;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class EntityStatProvider implements ConfigurationProvider<AbstractEntity> {

    private final HashMap<Class<? extends AbstractEntity>, DummyEntityStat> loadedStats = new HashMap<>();

    private final ArrayList<Tracker<AbstractEntity>> trackers = new ArrayList<>();

    /***
     * name of file that this is supposed to read
     * @return name of file
     */
    @Override
    public String getConfigurationFileName() {
        return "Char.csv";
    }

    /***
     * Called by csv manager when read csv files.
     * {@link CSVReader} passed to parameter to read records from csv.
     * @param reader {@link CSVReader} to read records from csv.
     */
    @Override
    public void loadConfiguration(CSVReader reader) {
        CsvToBean<DummyEntityStat> bean = new CsvToBeanBuilder<DummyEntityStat>(reader)
                .withType(DummyEntityStat.class)
                .withThrowExceptions(true)
                .build();

        for (DummyEntityStat loadedStat : bean) {
            try {
                Class<? extends AbstractEntity> entityClass = parseEntityClass(loadedStat.getClassName());
                loadedStats.put(entityClass, loadedStat);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("해당 클래스를 찾을 수 없습니다 : " + loadedStat.getClassName());
            }
        }
    }

    /***
     * Apply configuration to actual object.
     * @param abstractEntity An object to be applied.
     */
    @Override
    public void apply(AbstractEntity abstractEntity) {
        if (loadedStats.containsKey(abstractEntity.getClass())) {
            DummyEntityStat entityStat = loadedStats.get(abstractEntity.getClass());
            abstractEntity.stat.attack = entityStat.getAttack();
            abstractEntity.stat.spell = entityStat.getSpell();
            abstractEntity.stat.critical = entityStat.getCritical();
            abstractEntity.stat.multiply = entityStat.getMultiply();
            abstractEntity.stat.moveRes = entityStat.getMoveRes();
            abstractEntity.stat.neutRes = entityStat.getNeutRes();
            abstractEntity.stat.speed = entityStat.getSpeed();
            abstractEntity.stat.debuRes = entityStat.getDebuRes();

            abstractEntity.desc = entityStat.getDescription();
            abstractEntity.maxHealth = entityStat.getHealth();
            abstractEntity.health = entityStat.getHealth();
        }
    }

    @Override
    public List<Tracker<AbstractEntity>> getTrackers() {
        return trackers;
    }

    @Override
    public void addTracker(Tracker<AbstractEntity> t) {
        trackers.add(t);
    }

    protected Class<? extends AbstractEntity> parseEntityClass(String className) throws ClassNotFoundException {
        return (Class<? extends AbstractEntity>) Class.forName(className);
    }

    @ToString
    @NoArgsConstructor
    @Data
    public static class DummyEntityStat {
        @CsvBindByName(column = "설명")
        private String description;

        @CsvBindByName(column = "이름")
        private String name;

        @CsvBindByName(column = "class name")
        private String className;

        @CsvBindByName(column = "체력")
        private int health;

        @CsvBindByName(column = "기본 공격력")
        private int attack = 0;

        @CsvBindByName(column = "기본 주문력")
        private int spell = 0;

        @CsvBindByName(column = "쉴드 무시")
        private int speed = 0;

        @CsvBindByName(column = "치명타 확률")
        private int critical = 5;

        @CsvBindByName(column = "치명타 배율")
        private int multiply = 50;

        @CsvBindByName(column = "이동 저항")
        private int moveRes = 5;

        @CsvBindByName(column = "디버프 저항")
        private int debuRes = 5;

        @CsvBindByName(column = "죽음 저항")
        private int neutRes = 5;
    }
}
