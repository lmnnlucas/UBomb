/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.*;
import fr.ubx.poo.ubomb.go.decor.door.DoorNextClosed;
import fr.ubx.poo.ubomb.go.decor.door.DoorNextOpened;
import fr.ubx.poo.ubomb.go.decor.door.DoorPrevOpened;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.*;


public final class SpriteFactory {

    public static Sprite create(Pane layer, GameObject gameObject) {
        if (gameObject instanceof Stone)
            return new Sprite(layer, STONE.getImage(), gameObject);
        if (gameObject instanceof Tree)
            return new Sprite(layer, TREE.getImage(), gameObject);
        if (gameObject instanceof Key)
            return new Sprite(layer, KEY.getImage(), gameObject);
        if (gameObject instanceof Hearth)
            return new Sprite(layer, HEART.getImage(), gameObject);
        if(gameObject instanceof Box)
            return new Sprite(layer, BOX.getImage(), gameObject);
        if(gameObject instanceof Princess)
            return new Sprite(layer, PRINCESS.getImage(), gameObject);
        if(gameObject instanceof BombRangeDec)
            return new Sprite(layer, BONUS_BOMB_RANGE_DEC.getImage(), gameObject);
        if(gameObject instanceof BombRangeInc)
            return new Sprite(layer, BONUS_BOMB_RANGE_INC.getImage(), gameObject);
        if(gameObject instanceof BombNumberInc)
            return new Sprite(layer, BONUS_BOMB_NB_INC.getImage(), gameObject);
        if(gameObject instanceof BombNumberDec)
            return new Sprite(layer, BONUS_BOMB_NB_DEC.getImage(), gameObject);
        if(gameObject instanceof DoorNextOpened)
            return new Sprite(layer, DOOR_OPENED_PLUS.getImage(), gameObject);
        if(gameObject instanceof DoorNextClosed)
            return new Sprite(layer, DOOR_CLOSED_PLUS.getImage(), gameObject);
        if(gameObject instanceof DoorPrevOpened)
            return new Sprite(layer, DOOR_OPENED_MINUS.getImage(), gameObject);
        throw new RuntimeException("Unsupported sprite for decor " + gameObject);
    }
}
