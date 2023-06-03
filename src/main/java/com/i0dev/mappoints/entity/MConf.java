package com.i0dev.mappoints.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

@EditorName("config")
public class MConf extends Entity<MConf> {

    protected static transient MConf i;

    public static MConf get() {
        return i;
    }

    public List<String> aliasesMapPoints = MUtil.list("mappoints", "mp");
    public String placeholderFormat = "&8[&6%points%&8]";

    @Override
    public MConf load(MConf that) {
        super.load(that);
        return this;
    }

}
