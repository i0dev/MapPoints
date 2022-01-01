package com.i0dev.MapPoints.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.config.GeneralConfig;
import com.i0dev.MapPoints.config.MessageConfig;
import com.i0dev.MapPoints.config.StorageConfig;
import com.i0dev.MapPoints.hooks.MCoreFactionsHook;
import com.i0dev.MapPoints.managers.PointsManager;
import com.i0dev.MapPoints.templates.AbstractCommand;
import com.i0dev.MapPoints.utility.ConfigUtil;
import com.i0dev.MapPoints.utility.MsgUtil;
import com.i0dev.MapPoints.utility.Utility;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.rmi.CORBA.Util;
import java.io.UTFDataFormatException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CmdMapPoints extends AbstractCommand {

    MessageConfig msg;
    GeneralConfig config;
    StorageConfig storage;
    PointsManager pointsManager;

    public CmdMapPoints(Heart heart, String command) {
        super(heart, command);
    }

    @Override
    public void initialize() {
        msg = getHeart().getConfig(MessageConfig.class);
        config = getHeart().getConfig(GeneralConfig.class);
        storage = getHeart().getConfig(StorageConfig.class);
        pointsManager = getHeart().getManager(PointsManager.class);
    }

    @Override
    public void deinitialize() {
        msg = null;
        storage = null;
        pointsManager = null;
        config = null;
    }

    @SneakyThrows
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            MsgUtil.msg(sender, msg.getHelpPageHeader());
            MsgUtil.msg(sender, msg.getHelpUsage());
            MsgUtil.msg(sender, msg.getReloadUsage());
            MsgUtil.msg(sender, msg.getAddUsage());
            MsgUtil.msg(sender, msg.getRemoveUsage());
            MsgUtil.msg(sender, msg.getSetUsage());
            MsgUtil.msg(sender, msg.getGetUsage());
            MsgUtil.msg(sender, msg.getAddFacUsage());
            MsgUtil.msg(sender, msg.getSetFacUsage());
            MsgUtil.msg(sender, msg.getRemoveFacUsage());
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("MapPoints.reload.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            getHeart().reload();
            MsgUtil.msg(sender, msg.getReloadedConfig());
            return;
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (!sender.hasPermission("MapPoints.add.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getAddUsage());
                return;
            }
            UUID player = MsgUtil.getPlayerUUID(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (player == null) {
                MsgUtil.msg(sender, msg.getCantFindPlayer());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            pointsManager.addPoints(player, amt);
            MsgUtil.msg(sender, msg.getAddedPoints(), new MsgUtil.Pair<>("{player}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));
            return;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (!sender.hasPermission("MapPoints.remove.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getRemoveUsage());
                return;
            }
            UUID player = MsgUtil.getPlayerUUID(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (player == null) {
                MsgUtil.msg(sender, msg.getCantFindPlayer());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            pointsManager.removePoints(player, amt);
            MsgUtil.msg(sender, msg.getRemovedPoints(), new MsgUtil.Pair<>("{player}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));

            return;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (!sender.hasPermission("MapPoints.set.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getSetUsage());
                return;
            }
            UUID player = MsgUtil.getPlayerUUID(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (player == null) {
                MsgUtil.msg(sender, msg.getCantFindPlayer());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            pointsManager.setPoints(player, amt);
            MsgUtil.msg(sender, msg.getSetPoints(), new MsgUtil.Pair<>("{player}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));

            return;
        }
        if (args[0].equalsIgnoreCase("get")) {
            if (!sender.hasPermission("MapPoints.get.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 2) {
                MsgUtil.msg(sender, msg.getGetUsage());
                return;
            }
            UUID player = MsgUtil.getPlayerUUID(args[1]);
            if (player == null) {
                MsgUtil.msg(sender, msg.getCantFindPlayer());
                return;
            }

            MsgUtil.msg(sender, msg.getUsersPoints(), new MsgUtil.Pair<>("{player}", args[1]), new MsgUtil.Pair<>("{amount}", pointsManager.getAmount(player) + ""));
            return;
        }
        if (args[0].equalsIgnoreCase("addFac")) {
            if (!sender.hasPermission("MapPoints.addFac.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getAddFacUsage());
                return;
            }
            Object faction = MCoreFactionsHook.getFactionByName(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (faction == null) {
                MsgUtil.msg(sender, msg.getCantFindFaction());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            for (UUID uuid : MCoreFactionsHook.getAllPlayersInFaction(faction)) {
                pointsManager.addPointsNoSave(uuid, amt);
            }
            pointsManager.save();
            MsgUtil.msg(sender, msg.getAddedFacPoints(), new MsgUtil.Pair<>("{faction}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));

            return;
        }
        if (args[0].equalsIgnoreCase("removeFac")) {
            if (!sender.hasPermission("MapPoints.removeFac.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getRemoveFacUsage());
                return;
            }
            Object faction = MCoreFactionsHook.getFactionByName(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (faction == null) {
                MsgUtil.msg(sender, msg.getCantFindFaction());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            for (UUID uuid : MCoreFactionsHook.getAllPlayersInFaction(faction)) {
                pointsManager.removePointsNoSave(uuid, amt);
            }
            pointsManager.save();
            MsgUtil.msg(sender, msg.getRemovedFacPoints(), new MsgUtil.Pair<>("{faction}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));

            return;
        }
        if (args[0].equalsIgnoreCase("setFac")) {
            if (!sender.hasPermission("MapPoints.setFac.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            if (args.length != 3) {
                MsgUtil.msg(sender, msg.getSetFacUsage());
                return;
            }
            Object faction = MCoreFactionsHook.getFactionByName(args[1]);
            Integer amt = Utility.parseIntOrNull(args[2]);
            if (faction == null) {
                MsgUtil.msg(sender, msg.getCantFindFaction());
                return;
            }
            if (amt == null) {
                MsgUtil.msg(sender, msg.getInvalidNumber());
                return;
            }

            for (UUID uuid : MCoreFactionsHook.getAllPlayersInFaction(faction)) {
                pointsManager.setPointsNoSave(uuid, amt);
            }
            pointsManager.save();
            MsgUtil.msg(sender, msg.getSetFacPoints(), new MsgUtil.Pair<>("{faction}", args[1]), new MsgUtil.Pair<>("{amount}", amt.toString()));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1)
            return tabCompleteHelper(args[0], Arrays.asList("reload", "help", "add", "remove", "set", "get", "addFac", "setFac", "removeFac"));
        if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            return null;
        }
        if (args.length == 2 || args.length == 3)
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set")) {
                if (args.length == 2)
                    return null;
                return tabCompleteHelper(args[2], Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
            }
        if (args.length == 2 || args.length == 3)
            if (args[0].equalsIgnoreCase("addFac") || args[0].equalsIgnoreCase("setFac") || args[0].equalsIgnoreCase("removeFac")) {
                if (args.length == 2) {
                    return tabCompleteHelper(args[1], MCoreFactionsHook.getAllFactions());
                }
                return tabCompleteHelper(args[2], Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
            }
        return blank;
    }
}
