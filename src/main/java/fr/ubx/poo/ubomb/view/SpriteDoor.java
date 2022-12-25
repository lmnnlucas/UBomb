/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.door.Door;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteDoor extends Sprite {

    public SpriteDoor(Pane layer, Door door) {
        super(layer, null, door);
        updateImage();
    }

    @Override
    public void updateImage() {
        Door door = (Door) getGameObject();
        setImage(door.isLocked() ? ImageResource.DOOR_CLOSED.getImage() : ImageResource.DOOR_OPENED.getImage());
    }
}

