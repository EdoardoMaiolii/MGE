package io;

import java.awt.Color;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;

public class ColorYamlNodeConverter {
    private final Color color;

    public static ColorYamlNodeConverter of(final Color color) {
        return new ColorYamlNodeConverter(color);
    }

    private ColorYamlNodeConverter(final Color color) {
        this.color = color;
    }

    YamlNode colorYaml() {
        return Yaml.createYamlMappingBuilder().add("r", Integer.toString(color.getRed()))
                .add("g", Integer.toString(color.getGreen())).add("b", Integer.toString(color.getBlue())).build();
    }

}