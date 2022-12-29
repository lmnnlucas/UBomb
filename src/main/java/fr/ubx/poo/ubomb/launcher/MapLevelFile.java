package fr.ubx.poo.ubomb.launcher;

import java.util.Arrays;
public class MapLevelFile implements MapRepo{

    private final static MapLevelFile instance = new MapLevelFile();

    private MapLevelFile() {}

    public static MapLevelFile getInstance() {
        return instance;
    }

    @Override
    public MapLevel load(String string) {
        String[] split = string.split("x");
        int width = Arrays.stream(split).mapToInt(i -> i.length()).max().getAsInt();
        int height = split.length;



        MapLevel level = new MapLevel(width,height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                level.set(x,y,Entity.fromCode(split[y].charAt(x)));
            }
        }

        return level;
    }

    @Override
    public String export(MapLevel mapLevel) {
        return null;
    }
}
