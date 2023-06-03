package com.i0dev.mappoints;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified {

    BASECOMMAND,

    ADD,
    ADD_PLAYER,
    ADD_FACTION,

    REMOVE,
    REMOVE_PLAYER,
    REMOVE_FACTION,

    SET,
    SET_PLAYER,
    SET_FACTION,

    GET,
    GET_PLAYER,
    GET_FACTION,

    VERSION;

    private final String id;

    Perm() {
        this.id = PermissionUtil.createPermissionId(MapPointsPlugin.get(), this);
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean has(Permissible permissible, boolean verboose) {
        return PermissionUtil.hasPermission(permissible, this, verboose);
    }

    public boolean has(Permissible permissible) {
        return PermissionUtil.hasPermission(permissible, this);
    }

}
