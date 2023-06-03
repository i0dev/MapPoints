package com.i0dev.mappoints.entity;

import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.MUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class MPlayer extends SenderEntity<MPlayer> {

    public static MPlayer get(Object oid) {
        return MPlayerColl.get().get(oid);
    }

    @Override
    public MPlayer load(MPlayer that) {
        this.setMapPoints(that.mapPoints);
        return this;
    }

    @Setter
    @Getter
    public int mapPoints = 0;

    public void add(int amount) {
        mapPoints += amount;
        this.changed();
    }

    public void remove(int amount) {
        mapPoints -= amount;
        this.changed();
    }

    public void set(int amount) {
        mapPoints = amount;
        this.changed();
    }

}
