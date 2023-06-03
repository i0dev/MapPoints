package com.i0dev.mappoints.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;

@EditorName("config")
public class MLang extends Entity<MLang> {

    protected static transient MLang i;

    public static MLang get() {
        return i;
    }

    public String prefix = "&8[&6MapPoints&8]&7";

    public String added = "%prefix% &aYou have added %amount% points to %player%&a.";
    public String removed = "%prefix% &aYou have removed %amount% points from %player%&a.";
    public String removedToZero = "%prefix% &a%player%'s points would be negative, so it has been set to 0.&a.";
    public String set = "%prefix% &aYou have set %player%&a's points to %amount%&a.";
    public String cantBeNegative = "%prefix% &aYou can't set a player's points to a negative number, so it has been set to 0&a.";
    public String get = "%prefix% &a%player%&a's points: %amount%&a.";

    @Override
    public MLang load(MLang that) {
        super.load(that);
        return this;
    }
}
