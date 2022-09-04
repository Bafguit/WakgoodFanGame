package com.fastcat.labyrintale.prototype;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public final class GameConfiguration {
    private HashMap<Class<? extends ConfigurationProvider<?>>, ConfigurationProvider<?>> loadedProviders;

    private List<Class<? extends ConfigurationProvider<?>>> providerClasses;

    private static GameConfiguration instance;

    private GameConfiguration() {
        loadedProviders = new HashMap<>();
    }

    public static GameConfiguration getInstance() {
        if (instance == null)
            instance = new GameConfiguration();
        return instance;
    }

    @SafeVarargs
    public final void setProviderClasses(Class<? extends ConfigurationProvider<?>>... providerClasses) {
        this.providerClasses = Arrays.asList(providerClasses);
    }
    public Collection<ConfigurationProvider<?>> getConfigurationProviders(){
        return loadedProviders.values();
    }
    public List<Class<? extends ConfigurationProvider<?>>> getProviderClasses(){
        return providerClasses;
    }
    public void loadAllProviders(File parentFile) throws NoSuchMethodException, FileNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (providerClasses == null)
            return;

        for (Class<? extends ConfigurationProvider<?>> providerClass : providerClasses) {

            ConfigurationProvider<?> provider = providerClass.getConstructor().newInstance();

            FileReader reader = new FileReader(new File(parentFile, provider.getConfigurationFileName()));
            provider.loadConfiguration(new CSVReader(reader));


            loadedProviders.put(providerClass,provider);
        }
    }
    public boolean hasProvider(Class<? extends ConfigurationProvider<?>> providerClass){
        return loadedProviders.containsKey(providerClass);
    }

    public <T> ConfigurationProvider<T> getProvider(Class<? extends ConfigurationProvider<T>> providerClass){
        return (ConfigurationProvider<T>) loadedProviders.get(providerClass);
    }
}
