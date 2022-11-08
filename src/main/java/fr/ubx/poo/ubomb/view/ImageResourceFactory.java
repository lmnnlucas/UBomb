package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.game.Direction;
import javafx.scene.image.Image;

public class ImageResourceFactory {
    public static ImageResource digit(int i) {
        if (i < 0 || i > 9) throw new IllegalArgumentException("Digit must be in [0-9]");
        return ImageResource.valueOf("DIGIT_" + i);
    }

    public static ImageResource getPlayer(Direction direction) {
        return ImageResource.valueOf("PLAYER_" + direction);
    }

    public static ImageResource getMonster(Direction direction) {
        return ImageResource.valueOf("MONSTER_" + direction);
    }

    public static ImageResource getBomb(int i) {
        if (i < 0 || i > 3)
            throw new IllegalArgumentException();
        return ImageResource.valueOf("BOMB_"+i);
    }

}
