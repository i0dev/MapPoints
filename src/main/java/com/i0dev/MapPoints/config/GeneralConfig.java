package com.i0dev.MapPoints.config;

import com.i0dev.MapPoints.Heart;
import com.i0dev.MapPoints.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneralConfig extends AbstractConfiguration {

    public GeneralConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

    String pointsFormat =  "&8[&6{points}&8]";

}
