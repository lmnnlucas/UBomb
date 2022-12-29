package fr.ubx.poo.ubomb.launcher;

import fr.ubx.poo.ubomb.game.*;
import javafx.beans.property.IntegerProperty;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static javafx.beans.property.IntegerProperty.integerProperty;

public class GameLauncher {

    public static Game load() {
        Configuration configuration = new Configuration(new Position(0, 0), 3, 5, 4000, 5, 1000);
        return new Game(configuration, new Level(new MapLevelDefault()));
    }

    public static Game load(File file) {
        Properties config = new Properties();
        Game game = null;
        try {
            Reader in = new FileReader(file);
            config.load(in);
            Configuration configuration = new Configuration(
                    new Position(config.getProperty("player")),
                    3,
                    Integer.parseInt(config.getProperty("playerLives")),
                    Integer.parseInt(config.getProperty("playerInvincibilityTime")),
                    Integer.parseInt(config.getProperty("monsterVelocity")),
                    Integer.parseInt(config.getProperty("monsterInvincibilityTime")));
            int nbLevel = Integer.parseInt(config.getProperty("levels"));
            ArrayList<Grid> levels = new ArrayList<>();
            if(Boolean.parseBoolean(config.getProperty("compression"))){
                for(int i = 1; i <= nbLevel; i++) {
                    levels.add(new Level(MapLevelFileRLE.getInstance().load(config.getProperty("level"+i))));
                }
            } else {
                for(int i = 1; i <= nbLevel; i++) {
                    levels.add(new Level(MapLevelFile.getInstance().load(config.getProperty("level"+i))));
                }
            }

            game = new Game(configuration,levels);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return game;
    }
}
