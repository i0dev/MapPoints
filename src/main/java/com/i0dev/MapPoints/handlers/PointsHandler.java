package com.i0dev.MapPoints.handlers;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.managers.PointsManager;
import com.i0dev.MapPoints.templates.AbstractListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.AbstractList;

public class PointsHandler extends AbstractListener {
    public PointsHandler(Heart heart) {
        super(heart);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String format = event.getFormat();
        Long amt = heart.getManager(PointsManager.class).getAmount(event.getPlayer().getUniqueId());
        String replacement = heart.cnf().getPointsFormat().replace("{points}", amt.toString());
        format = format.replace("{mappoints_points}", replacement);
        event.setFormat(format);
    }

}
