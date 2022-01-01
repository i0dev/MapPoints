package com.i0dev.MapPoints.objects;

import java.util.List;

public class IndexableSkullConfigItem extends IndexableConfigItem {

    String skullData;

    public IndexableSkullConfigItem(String displayName, int amount, short data, String material, List<String> lore, boolean glow, int index, String skullData) {
        super(displayName, amount, data, material, lore, glow, index);
        this.skullData = skullData;
    }
}
