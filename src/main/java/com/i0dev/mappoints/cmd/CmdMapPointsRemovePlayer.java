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

public class CmdMapPointsRemovePlayer extends MapPointsCommand {

    private static CmdMapPointsRemovePlayer i = new CmdMapPointsRemovePlayer();

    public static CmdMapPointsRemovePlayer get() {
        return i;
    }

    public CmdMapPointsRemovePlayer() {
        this.addParameter(TypePlayer.get(), "player");
        this.addParameter(TypeInteger.get(), "amount");
        this.setVisibility(Visibility.SECRET);
    }

    @Override
    public void perform() throws MassiveException {
        Player player = this.readArg();
        Integer amount = this.readArg();

        MPlayer mPlayer = MPlayer.get(player);

        if (mPlayer.mapPoints - amount < 0) {
            msg(Utils.prefixAndColor(MLang.get().removedToZero,
                    new Pair<>("%amount%", String.valueOf(amount)),
                    new Pair<>("%player%", player.getName())));
            mPlayer.set(0);
            return;
        }

        mPlayer.remove(amount);
        msg(Utils.prefixAndColor(MLang.get().removed,
                new Pair<>("%amount%", String.valueOf(amount)),
                new Pair<>("%player%", player.getName())));
    }
}
