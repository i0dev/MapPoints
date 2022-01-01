package com.i0dev.MapPoints;

import com.i0dev.MapPoints.commands.CmdMapPoints;
import com.i0dev.MapPoints.config.GeneralConfig;
import com.i0dev.MapPoints.config.MessageConfig;
import com.i0dev.MapPoints.config.StorageConfig;
import com.i0dev.MapPoints.handlers.PointsHandler;
import com.i0dev.MapPoints.managers.PointsManager;
import com.i0dev.MapPoints.templates.AbstractCommand;
import com.i0dev.MapPoints.templates.AbstractConfiguration;
import com.i0dev.MapPoints.templates.AbstractListener;
import com.i0dev.MapPoints.templates.AbstractManager;
import com.i0dev.MapPoints.utility.ConfigUtil;
import com.i0dev.MapPoints.utility.MsgUtil;
import com.i0dev.MapPoints.utility.SQLUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Heart extends JavaPlugin {

    List<AbstractManager> managers = new ArrayList<>();
    List<AbstractConfiguration> configs = new ArrayList<>();

    public static boolean usingPapi;
    public static boolean usingMCoreFactions;

    @Override
    public void onEnable() {

        usingPapi = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        Plugin factions = getServer().getPluginManager().getPlugin("Factions");
        usingMCoreFactions = factions != null && factions.getDescription().getVersion().startsWith("2.");

        managers.addAll(Arrays.asList(
                new CmdMapPoints(this, "MapPoints"),
                new PointsManager(this),
                new PointsHandler(this)
        ));

        configs.addAll(Arrays.asList(
                new GeneralConfig(this, getDataFolder() + "/General.json"),
                new MessageConfig(this, getDataFolder() + "/Messages.json"),
                new StorageConfig(this, getDataFolder() + "/Storage.json")
        ));

        reload();
        registerManagers();
        // SQLUtil.establishConnection(this, false);

        System.out.println("\u001B[32m" + this.getDescription().getName() + " by: " + this.getDescription().getAuthors().get(0) + " has been enabled.");
    }

    public void reload() {
        // old ~ new
        ArrayList<MsgUtil.Pair<AbstractConfiguration, AbstractConfiguration>> toReplace = new ArrayList<>();
        configs.forEach(abstractConfiguration -> toReplace.add(new MsgUtil.Pair<>(abstractConfiguration, ConfigUtil.load(abstractConfiguration, this))));
        toReplace.forEach(pairs -> {
            configs.remove(pairs.getKey());
            configs.add(pairs.getValue());
        });
    }


    @SneakyThrows
    @Override
    public void onDisable() {
        configs.clear();
        managers.forEach(AbstractManager::deinitialize);
        HandlerList.unregisterAll(this);
        managers.clear();
        Bukkit.getScheduler().cancelTasks(this);
        Connection connection = SQLUtil.getConnection();
        if (connection != null) connection.close();
        System.out.println("\u001B[31m" + this.getDescription().getName() + " by: " + this.getDescription().getAuthors().get(0) + " has been disabled.");
    }

    public <T> T getManager(Class<T> clazz) {
        return (T) managers.stream().filter(manager -> manager.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public <T> T getConfig(Class<T> clazz) {
        return (T) configs.stream().filter(config -> config.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public void registerManagers() {
        managers.forEach(abstractManager -> {
            if (abstractManager.isLoaded()) abstractManager.deinitialize();
            if (abstractManager instanceof AbstractListener)
                getServer().getPluginManager().registerEvents((AbstractListener) abstractManager, this);
            else if (abstractManager instanceof AbstractCommand) {
                getCommand(((AbstractCommand) abstractManager).getCommand()).setExecutor(((AbstractCommand) abstractManager));
                getCommand(((AbstractCommand) abstractManager).getCommand()).setTabCompleter(((AbstractCommand) abstractManager));
            }
            abstractManager.initialize();
            abstractManager.setLoaded(true);
        });
    }

    public GeneralConfig cnf() {
        return getConfig(GeneralConfig.class);
    }

    public MessageConfig msg() {
        return getConfig(MessageConfig.class);
    }

    public StorageConfig storage() {
        return getConfig(StorageConfig.class);
    }

}
