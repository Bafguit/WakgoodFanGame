package com.fastcat.labyrintale.prototype;

import com.opencsv.CSVReader;

/**
 * @author Singlerr
 * @param <T> Type that configuration need to apply.
 */
public interface ConfigurationProvider<T> {
    /***
     * name of file that this is supposed to read
     * @return name of file
     */
    String getConfigurationFileName();

    /***
     * Called by csv manager when read csv files.
     * {@link CSVReader} passed to parameter to read records from csv.
     * @param reader {@link CSVReader} to read records from csv.
     */
    void loadConfiguration(CSVReader reader);

    /***
     * Apply configuration to actual object.
     * @param t An object to be applied.
     */
    void apply(T t);
}
