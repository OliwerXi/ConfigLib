package com.rarlab.configlib;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.rarlab.configlib.ConfigLib.PLUGIN_PATH;

/**
 * Created on January, 28, 2020
 *
 * @author RarLab
 */
public class Config implements IConfig {
    // Utility fields.
    @NotNull private static final String DEFAULT_NAME = "config";

    // Config related fields.
    @Nullable private final File file;
    @Nullable private FileConfiguration config;

    /**
     * Constructor.
     * Initialize all fields.
     *
     * @param name File name without the extension.
     */
    public Config(@Nullable String name) {
        // Null checks.
        Objects.requireNonNull(PLUGIN_PATH, "Plugin path cannot be null!");

        // Initializations
        this.file = new File(PLUGIN_PATH.toFile(), (name != null ? name : DEFAULT_NAME) + ".yml");
        if (file.exists()) this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Create the file.
     */
    @Override
    public void generate() {
        // Checking.
        Objects.requireNonNull(file, "File cannot be null!");
        if (file.exists()) return;

        // Handling.
        try {
            // Fields to work from.
            final boolean success = file.createNewFile();
            final String name = file.getName().split(".")[0];

            // Checks.
            if (success) System.out.println("Created file " + name);
            else System.out.println("Failed to create file " + name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the configuration.
     *
     * @return FileConfiguration object.
     */
    @Override @Nullable @Getter
    public FileConfiguration get() {
        return this.config;
    }

    /**
     * Update the configuration.
     *
     * @param configuration Config to be updated to.
     */
    @Override
    public void updateConfig(@NotNull FileConfiguration configuration) {
        this.config = configuration;
    }

    /**
     * Get the file.
     *
     * @return File object.
     */
    @Override @Nullable @Getter
    public File getFile() {
        return this.file;
    }
}