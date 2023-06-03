package com.i0dev.mappoints.cmd;

import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.util.MUtil;

public class CmdMapPointsSetFaction extends MapPointsCommand {

    public CmdMapPointsSetFaction() {
        this.addParameter(TypeFaction.get(), "faction");
        this.addParameter(TypeInteger.get(), "amount");
        this.setVisibility(Visibility.SECRET);
    }

    @Override
    public void perform() throws MassiveException {
        Faction faction = this.readArg();
        Integer amount = this.readArg();

        faction.getMPlayers().forEach(mPlayer -> CmdMapPointsSetPlayer.get().execute(me, MUtil.list(mPlayer.getPlayer().getName(), String.valueOf(amount))));
    }
}
