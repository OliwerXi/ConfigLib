package com.rarlab.configlib;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created on January, 28, 2020
 *
 * @author RarLab
 */
public interface IConfig {
    /**
     * Generate the file.
     */
    void generate();

    /**
     * Get the configuration.
     *
     * @return FileConfiguration object.
     */
    FileConfiguration get();

    /**
     * Update the configuration.
     *
     * @param configuration Config to be updated to.
     */
    void updateConfig(@NotNull FileConfiguration configuration);

    /**
     * Get the file.
     *
     * @return File object.
     */
    File getFile();
}