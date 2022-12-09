package com.fastcat.labyrintale.prototype;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
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
        if (instance == null) instance = new GameConfiguration();
        return instance;
    }

    /***
     * Set provider classes.
     * Note that provider classes registered before are removed.
     * @param providerClasses class provider array to register.
     */
    @SafeVarargs
    public final void setProviderClasses(Class<? extends ConfigurationProvider<?>>... providerClasses) {
        this.providerClasses = Arrays.asList(providerClasses);
    }

    public Collection<ConfigurationProvider<?>> getConfigurationProviders() {
        return loadedProviders.values();
    }

    /***
     * Get registered provider classes
     * @return registered provider classes
     */
    public List<Class<? extends ConfigurationProvider<?>>> getProviderClasses() {
        return providerClasses;
    }

    /***
     * DO NOT CALL FROM OTHER CLASS EXCEPT DesktopLauncher.java class
     * @param parentFile Directory that contains configuration files.
     * @throws NoSuchMethodException
     * @throws FileNotFoundException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void loadAllProviders(File parentFile)
            throws NoSuchMethodException, IOException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        if (providerClasses == null) return;

        for (Class<? extends ConfigurationProvider<?>> providerClass : providerClasses) {

            ConfigurationProvider<?> provider = providerClass.getConstructor().newInstance();

            FileReader reader =
                    new FileReader(new File(parentFile, provider.getConfigurationFileName()), Charset.forName("MS949"));
            provider.loadConfiguration(new CSVReader(reader));

            loadedProviders.put(providerClass, provider);
        }
    }

    public void updateAllProviders(File parentFile) throws IOException {
        for (ConfigurationProvider<?> provider : loadedProviders.values()) {
            FileReader reader =
                    new FileReader(new File(parentFile, provider.getConfigurationFileName()), Charset.forName("MS949"));
            provider.loadConfiguration(new CSVReader(reader));
        }
    }

    /***
     * Check if provider with provider class exists.
     * @param providerClass Provider class to check
     * @return true if provider with provider class exists else false
     */
    public boolean hasProvider(Class<? extends ConfigurationProvider<?>> providerClass) {
        return loadedProviders.containsKey(providerClass);
    }

    /***
     * Get provider by provider class if registered.
     * @param providerClass Provider class expected to be registered.
     * @return Instance of provider class.
     * @param <T> Provider type that implements {@link ConfigurationProvider}
     */
    public <T extends ConfigurationProvider<?>> T getProvider(Class<T> providerClass) {
        return (T) loadedProviders.get(providerClass);
    }
}
