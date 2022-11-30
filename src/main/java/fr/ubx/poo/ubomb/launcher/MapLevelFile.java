package fr.ubx.poo.ubomb.launcher;

import java.util.Arrays;
import java.util.Map;

public class MapLevelFile implements MapRepo{

    private static MapLevelFile instance = new MapLevelFile();

    private MapLevelFile() {}

    public static MapLevelFile getInstance() {
        return instance;
    }

    @Override
    public MapLevel load(String string) {
        String[] split = string.split("x");
        int height = split.length;
        int width = Arrays.stream(split).mapToInt(i -> i.length()).max().getAsInt();
        //Fill the gap in the properties
        for (int j = 0; j < split.length; j++) {
            if(split[j].length() < width) {
                for (int i = split[j].length()-width; i < 0; i++) {
                    split[j] = split[j] + Entity.Empty;
                }
            }
        }

        MapLevel level = new MapLevel(height,width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                level.set(y,x,Entity.fromCode(split[y].charAt(x)));
            }
        }
        return level;
    }

    @Override
    public String export(MapLevel mapLevel) {
        return null;
    }
}
