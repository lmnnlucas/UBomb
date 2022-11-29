package fr.ubx.poo.ubomb.launcher;

public class MapLevelFileRLE implements MapRepo {
    private static MapLevelFileRLE instance = new MapLevelFileRLE();

    private MapLevelFileRLE() {}

    public static MapLevelFileRLE getInstance() {
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
