package com.i0dev.mappoints.cmd;

import com.i0dev.mappoints.MapPointsPlugin;
import com.i0dev.mappoints.Perm;
import com.i0dev.mappoints.entity.MConf;
import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdMapPoints extends MapPointsCommand {

    private static CmdMapPoints i = new CmdMapPoints();

    public CmdMapPointsAdd cmdMapPointsAdd = new CmdMapPointsAdd();
    public CmdMapPointsRemove cmdMapPointsRemove = new CmdMapPointsRemove();
    public CmdMapPointsSet cmdMapPointsSet = new CmdMapPointsSet();
    public CmdMapPointsGet cmdMapPointsGet = new CmdMapPointsGet();
    public MassiveCommandVersion cmdFactionsVersion = new MassiveCommandVersion(MapPointsPlugin.get()).setAliases("v", "version").addRequirements(RequirementHasPerm.get(Perm.VERSION));

    public static CmdMapPoints get() {
        return i;
    }

    @Override
    public List<String> getAliases() {
        return MConf.get().aliasesMapPoints;
    }

}
