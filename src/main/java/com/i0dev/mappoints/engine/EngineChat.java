package com.i0dev.mappoints.engine;

import com.i0dev.mappoints.entity.MConf;
import com.i0dev.mappoints.entity.MPlayer;
import com.i0dev.mappoints.util.Utils;
import com.massivecraft.massivecore.Engine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EngineChat extends Engine {

    private static EngineChat i = new EngineChat();

    public static EngineChat get() {
        return i;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        String format = event.getFormat();
        int amt = MPlayer.get(event.getPlayer()).mapPoints;
        String replacement = Utils.color(MConf.get().placeholderFormat.replace("%points%", String.valueOf(amt)));
        format = format.replace("{mappoints_points}", replacement);
        event.setFormat(format);
    }

}
