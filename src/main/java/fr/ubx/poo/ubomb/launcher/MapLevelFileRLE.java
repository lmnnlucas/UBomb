package fr.ubx.poo.ubomb.launcher;

import java.util.Arrays;

public class MapLevelFileRLE implements MapRepo {
    private static MapLevelFileRLE instance = new MapLevelFileRLE();

    private MapLevelFileRLE() {}

    public static MapLevelFileRLE getInstance() {
        return instance;
    }

    @Override
    public MapLevel load(String string) {
        StringBuilder gridString = new StringBuilder();

        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == 'x') {
                gridString.append(string.charAt(i));
            } else if (!Character.isDigit(string.charAt(i)) && Character.isDigit(string.charAt(i + 1))) {
                int numberOfTheChar = Character.getNumericValue(string.charAt(i + 1));
                for (int c = 0; c <= numberOfTheChar; c++) {
                    gridString.append(string.charAt(i));
                }
            } else if (!Character.isDigit(string.charAt(i))) {
                gridString.append(string.charAt(i));
            }
        }
        return MapLevelFile.getInstance().load(gridString.toString());
    }

    @Override
    public String export(MapLevel mapLevel) {
        return null;
    }
}
