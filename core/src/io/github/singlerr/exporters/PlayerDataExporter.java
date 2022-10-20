package io.github.singlerr.exporters;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.providers.EntityStatProvider;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.github.singlerr.DataExporter;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class PlayerDataExporter implements DataExporter<AbstractEnemy> {
    /***
     * Write your data of {@link T} to {@link CSVWriter}
     * Any mechanisms writing data to csv is okay. Writing with bean or directly.
     * @param writer writer of csv. Passed from {@link GameConfiguration}
     */
    @Override
    public void export(CSVWriter writer) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {


        Set<Class<?>> classes = findAllClassesUsingReflectionsLibrary("com.fastcat.labyrintale");

        /*
        ColumnPositionMappingStrategy<EntityStatProvider.DummyEntityStat> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(EntityStatProvider.DummyEntityStat.class);
        strategy.setColumnMapping("키 값","이름","설명","행동력","이름","체력","기본 공격력","기본 주문력","치명타 확률","치명타 배율","속도","이동 저항","디버프 저항","죽음 저항");
        */
        StatefulBeanToCsv<EntityStatProvider.DummyEntityStat> builder = new StatefulBeanToCsvBuilder<EntityStatProvider.DummyEntityStat>(writer)
                .withThrowExceptions(true)
                .build();

        List<EntityStatProvider.DummyEntityStat> exportable = new ArrayList<>();

        for (Class<?> cls : classes) {

            System.out.println("Exporting " + cls.getName());


            EntityStatProvider.DummyEntityStat data = new EntityStatProvider.DummyEntityStat();
            data.setDescription(cls.getName());
            data.setClassName(cls.getName());

            try {
                AbstractEntity entity = (AbstractEntity) cls.getDeclaredConstructor().newInstance();
                data.setHealth(entity.health);
                data.setAttack(entity.stat.attack);
                data.setCritical(entity.stat.critical);
                data.setMultiply(entity.stat.multiply);
                data.setSpeed(entity.stat.speed);
                data.setSpell(entity.stat.spell);
                data.setDebuRes(entity.stat.debuRes);
                data.setMoveRes(entity.stat.moveRes);
                data.setNeutRes(entity.stat.neutRes);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            exportable.add(data);
        }

        builder.write(exportable);
    }

    /***
     * Identifying what this class should export.
     * @return class that this will export.
     */
    @Override
    public Class<AbstractEnemy> getExportedClass() {
        return AbstractEnemy.class;
    }

    /***
     * Identifying destination file path
     * @return destination file path
     */
    @Override
    public String getTargetFileName() {
        return "Player.csv";
    }


    public Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return new HashSet<>(reflections.getSubTypesOf(AbstractPlayer.class));
    }
}