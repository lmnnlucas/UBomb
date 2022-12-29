/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpritePlayer extends Sprite {
    private final int FRAME_TO_SKIP = 6;
    private int blipSequence; // frame blip sequence

    public SpritePlayer(Pane layer, Player player) {
        super(layer, null, player);
        updateImage();
        blipSequence = FRAME_TO_SKIP;
    }

    @Override
    public void updateImage() {
        Player player = (Player) getGameObject();
        Image image = ImageResourceFactory.getPlayer(player.getDirection()).getImage();
        if(player.getInvincibilityTimer() != null && blipSequence == 0) {
            image = null;
            blipSequence = FRAME_TO_SKIP;
        } else if (player.getInvincibilityTimer() != null) {
            blipSequence -= 1;
        } else {
            blipSequence = FRAME_TO_SKIP;
        }
        setImage(image);
    }
}

