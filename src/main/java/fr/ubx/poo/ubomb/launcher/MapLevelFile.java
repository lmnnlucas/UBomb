package fr.ubx.poo.ubomb.launcher;

import java.util.Map;

public class MapLevelFile implements MapRepo{

    private static MapLevelFile instance = new MapLevelFile();

    private MapLevelFile() {}

    public static MapLevelFile getInstance() {
        return instance;
    }

    @Override
    public MapLevel load(String string) {
        return null;
    }

    @Override
    public String export(MapLevel mapLevel) {
        return null;
    }
}
