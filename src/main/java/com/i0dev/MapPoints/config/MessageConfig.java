package com.i0dev.MapPoints.config;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageConfig extends AbstractConfiguration {


    String helpPageHeader = "&7&m_______&r&8[&r &c&lMapPoints &8]&m_______";
    String reloadUsage = " &c* &7/MapPoints reload";
    String helpUsage = " &c* &7/MapPoints help";
    String addUsage = " &c* &7/MapPoints add <player> <amount>";
    String removeUsage = " &c* &7/MapPoints remove <player> <amount>";
    String setUsage = " &c* &7/MapPoints set <player> <amount>";
    String getUsage = " &c* &7/MapPoints get <player>";
    String addFacUsage = " &c* &7/MapPoints addFac <faction> <amount>";
    String removeFacUsage = " &c* &7/MapPoints removeFac <faction> <amount>";
    String setFacUsage = " &c* &7/MapPoints setFac <faction> <amount>";

    String usersPoints = "&c{player}&7 has &f{amount}&7 Map Points.";

    String addedPoints = "&7You have added &f{amount}&7 Map Points to &c{player}&7.";
    String removedPoints = "&7You have removed &f{amount}&7 Map Points from &c{player}&7.";
    String setPoints = "&7You have set &c{player}&7 to &f{amount}&7 Map Points.";

    String addedFacPoints = "&7You have added &f{amount}&7 Map Points to &c{faction}&7.";
    String removedFacPoints = "&7You have removed &f{amount}&7 Map Points from &c{faction}&7.";
    String setFacPoints = "&7You have set &c{faction}&7 to &f{amount}&7 Map Points.";

    String reloadedConfig = "&7You have&a reloaded&7 the configuration.";
    String noPermission = "&cYou don not have permission to run that command.";
    String cantFindPlayer = "&cThat player cannot be found!";
    String cantFindFaction = "&cThat faction cannot be found!";
    String invalidNumber = "&cThe number &f{num} &cis invalid! Try again.";
    String cantRunAsConsole = "&cYou cannot run this command from console.";

    public MessageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }
}
