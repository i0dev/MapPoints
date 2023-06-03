package com.i0dev.mappoints;

import com.i0dev.mappoints.entity.MConfColl;
import com.i0dev.mappoints.entity.MLangColl;
import com.i0dev.mappoints.entity.MPlayerColl;
import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.collections.MassiveList;

import java.util.List;

public class MapPointsPlugin extends MassivePlugin {

    private static MapPointsPlugin i;

    public MapPointsPlugin() {
        MapPointsPlugin.i = this;
    }

    public static MapPointsPlugin get() {
        return i;
    }

    @Override
    public void onEnableInner() {
        this.activateAuto();
    }


    @Override
    public List<Class<?>> getClassesActiveColls() {
        return new MassiveList<>(
                MConfColl.class,
                MLangColl.class,
                MPlayerColl.class
        );
    }

}