/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteMonster extends SpriteCharacter {

    public SpriteMonster(Pane layer, Monster monster) {
        super(layer, monster);
        updateImage();
    }

    @Override
    public void updateImage() {
        Monster monster = (Monster) getGameObject();
        if(getGameObject().game.isOnSameGrid(monster.getGridNumber())) {
            image = ImageResourceFactory.getMonster(monster.getDirection()).getImage();
            super.updateImage();
            setImage(image);
        } else {
            setImage(null);
        }
    }
}

