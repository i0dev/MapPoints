package com.i0dev.MapPoints.managers;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.templates.AbstractManager;
import com.i0dev.MapPoints.utility.ConfigUtil;

import java.util.UUID;

public class PointsManager extends AbstractManager {
    public PointsManager(Heart heart) {
        super(heart);
    }

    public Long getAmount(UUID uuid) {
        return heart.storage().getStorage().getOrDefault(uuid.toString(), 0L);
    }


    public void addPoints(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), getAmount(uuid) + points);
        save();
    }

    public void addPointsNoSave(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), getAmount(uuid) + points);
    }

    public void removePoints(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), getAmount(uuid) - points);
        save();
    }

    public void removePointsNoSave(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), getAmount(uuid) - points);
    }

    public void setPoints(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), points);
        save();
    }

    public void setPointsNoSave(UUID uuid, long points) {
        getHeart().storage().getStorage().put(uuid.toString(), points);
    }


    public void save() {
        ConfigUtil.save(heart.storage());
    }

}
