/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteMonster extends Sprite {

    public SpriteMonster(Pane layer, Monster monster) {
        super(layer, null, monster);
        updateImage();
    }

    @Override
    public void updateImage() {
        Monster monster = (Monster) getGameObject();
        Image image = ImageResourceFactory.getMonster(monster.getDirection()).getImage();
        setImage(image);
    }
}

