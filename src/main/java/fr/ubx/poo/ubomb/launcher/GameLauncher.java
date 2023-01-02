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
        int playerLives = 5;
        int playerInvincibilityTime = 4000;
        int monsterVelocity = 5;
        int monsterInvincibilityTime = 1000;
        try {
            Reader in = new FileReader(file);
            config.load(in);
            try {
                playerLives = Integer.parseInt(config.getProperty("playerLives"));
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("playerLives not found, using default value");
            }
            try {
                playerInvincibilityTime = Integer.parseInt(config.getProperty("playerInvincibilityTime"));
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("playerInvincibilityTime not found, using default value");
            }
            try {
                monsterVelocity = Integer.parseInt(config.getProperty("monsterVelocity"));
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("monsterVelocity not found, using default value");
            }
            try {
                monsterInvincibilityTime = Integer.parseInt(config.getProperty("monsterInvincibilityTime"));
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("monsterInvincibilityTime not found, using default value");
            }
            Configuration configuration = new Configuration(
                    new Position(config.getProperty("player")),
                    3,
                    playerLives,
                    playerInvincibilityTime,
                    monsterVelocity,
                    monsterInvincibilityTime);
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
