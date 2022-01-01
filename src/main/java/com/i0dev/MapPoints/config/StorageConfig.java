package com.i0dev.MapPoints.config;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.templates.AbstractConfiguration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StorageConfig extends AbstractConfiguration {
    public StorageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

    HashMap<String, Long> storage = new HashMap<>();

    public void add(String key, long value) {
        storage.put(key, value);
    }

    public long get(String key) {
        return storage.get(key);
    }

    public void remove(String key) {
        storage.remove(key);
    }

    public boolean contains(String key) {
        return storage.containsKey(key);
    }


}
