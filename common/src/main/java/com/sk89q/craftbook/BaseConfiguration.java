package com.sk89q.craftbook;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.*;

/**
 * @author Me4502
 */
public class BaseConfiguration {

    public final FileConfiguration cfg;
    public final File folder;

    public BaseConfiguration(FileConfiguration cfg, File dataFolder, boolean common) {

        this.cfg = cfg;
        folder = dataFolder;
        if(common)
            commonSettings = new CommonSettings();
    }

    public BaseConfiguration(FileConfiguration cfg, File dataFolder) {

        this.cfg = cfg;
        folder = dataFolder;
        commonSettings = new CommonSettings();
    }

    public CommonSettings commonSettings;

    //General settings
    public class CommonSettings {

        public final List<String> languages;
        public final String language;
        public final boolean opPerms;
        public final boolean useBlockDistance;
        public final boolean checkWGRegions;
        public final boolean experimentalRepeaters;
	    public final boolean indirectRedstone;

        private CommonSettings() {

            languages = getStringList("languages", new ArrayList<String>(Arrays.asList("en_US")));
            language = getString("language", "en_US");
            opPerms = getBoolean("op-perms", true);
            useBlockDistance = getBoolean("use-block-radius", false);
            checkWGRegions = getBoolean("check-worldguard-flags", true);
            experimentalRepeaters = getBoolean("experimental-repeater-support", false);
	        indirectRedstone = getBoolean("indirect-redstone-support", false);
        }
    }

    public int getInt(String name, int def) {

        int it = cfg.getInt(name, def);
        cfg.set(name, it);
        return it;
    }

    public double getDouble(String name, double def) {

        double it = cfg.getDouble(name, def);
        cfg.set(name, it);
        return it;
    }

    public boolean getBoolean(String name, boolean def) {

        boolean it = cfg.getBoolean(name, def);
        cfg.set(name, it);
        return it;
    }

    public String getString(String name, String def) {

        String it = cfg.getString(name, def);
        cfg.set(name, it);
        return it;
    }

    public List<String> getStringList(String name, List<String> def) {

        List<String> it = cfg.getStringList(name);
        if(it == null || it.size() == 0) {
            it = def;
        }
        cfg.set(name, it);
        return it;
    }

    public Set<Integer> getIntegerSet(String name, List<Integer> def) {

        List<Integer> tids = cfg.getIntegerList(name);
        if (tids == null || tids.isEmpty() || tids.size() < 1) tids = def;
        Set<Integer> allowedBlocks = new HashSet<Integer>();
        for (Integer tid : tids) allowedBlocks.add(tid);
        cfg.set(name, tids);
        return allowedBlocks;
    }

    public Set<Material> getMaterialSet(String name, List<Integer> def) {

        List<Integer> tids = cfg.getIntegerList(name);
        if (tids == null || tids.isEmpty() || tids.size() < 1) tids = def;
        Set<Material> allowedBlocks = new HashSet<Material>();
        for (Integer tid : tids) allowedBlocks.add(Material.getMaterial(tid));
        cfg.set(name, tids);
        return Collections.unmodifiableSet(allowedBlocks);
    }
}