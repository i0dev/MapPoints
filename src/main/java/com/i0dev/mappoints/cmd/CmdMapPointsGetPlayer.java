package com.i0dev.mappoints.cmd;

import com.i0dev.mappoints.entity.MLang;
import com.i0dev.mappoints.entity.MPlayer;
import com.i0dev.mappoints.util.Pair;
import com.i0dev.mappoints.util.Utils;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import org.bukkit.entity.Player;

public class CmdMapPointsGetPlayer extends MapPointsCommand {

    private static CmdMapPointsGetPlayer i = new CmdMapPointsGetPlayer();

    public static CmdMapPointsGetPlayer get() {
        return i;
    }

    public CmdMapPointsGetPlayer() {
        this.addParameter(TypePlayer.get(), "player");
        this.setVisibility(Visibility.SECRET);
    }

    @Override
    public void perform() throws MassiveException {
        Player player = this.readArg();

        MPlayer mPlayer = MPlayer.get(player);
        msg(Utils.prefixAndColor(MLang.get().get,
                new Pair<>("%amount%", String.valueOf(mPlayer.mapPoints)),
                new Pair<>("%player%", player.getName())));
    }
}
