package com.rarlab.configlib;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created on January, 28, 2020
 *
 * @author RarLab
 */
public class ConfigLib {
    @Nullable public static Path PLUGIN_PATH;
    @NotNull private final Map<String, IConfig> configs;

    /**
     * Constructor.
     * Initialize all fields.
     *
     * @param plugin Plugin instance.
     */
    public ConfigLib(@NotNull JavaPlugin plugin) {
        PLUGIN_PATH = plugin.getDataFolder().toPath();
        this.configs = new IdentityHashMap<>();
    }

    /**
     * Add a Config object to the map.
     *
     * @param key Name of the file.
     * @param config Config object.
     */
    public void addConfig(@Nullable String key, @NotNull IConfig config) {
        // Checking.
        Objects.requireNonNull(key, "Config name cannot be null!");

        // Handling.
        configs.putIfAbsent(key, config);
    }

    /**
     * Save a config.
     *
     * @param config Config to save.
     */
    public void saveConfig(@Nullable IConfig config) {
        // Checking.
        Objects.requireNonNull(config, "Config cannot be null!");
        Objects.requireNonNull(config.getFile(), "File cannot be null!");
        Objects.requireNonNull(config.get(), "Configuration cannot be null!");

        // Handling.
        final Optional<IConfig> configOptional = configs
                .values()
                .stream()
                .filter(config::equals)
                .findAny();

        configOptional.ifPresent(conf -> {
            try {
                conf.get().save(conf.getFile());
                conf.updateConfig(YamlConfiguration.loadConfiguration(conf.getFile()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Get a Config from the map.
     *
     * @param key Config key.
     * @return Config object.
     */
    @Nullable @Getter
    public IConfig getConfig(@Nullable String key) {
        return configs.get(key);
    }

    /**
     * Get the configurations map.
     *
     * @return Map of configurations.
     */
    @NotNull @Getter
    public Map<String, IConfig> getConfigs() {
        return this.configs;
    }
}