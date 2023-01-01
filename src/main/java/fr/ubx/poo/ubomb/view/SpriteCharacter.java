package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Character;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class SpriteCharacter extends Sprite {

    private final int FRAME_TO_SKIP = 6;
    private int blipSequence; // frame blip sequence

    protected Image image;

    public SpriteCharacter(Pane layer, Character character) {
        super(layer, null, character);
        updateImage();
        blipSequence = FRAME_TO_SKIP;
        image = null;
    }

    @Override
    public void updateImage() {
        Character character = (Character) getGameObject();
        if (character.getInvicibilityTimer() != null && blipSequence == 0) {
            image = null;
            blipSequence = FRAME_TO_SKIP;
        } else if (character.getInvicibilityTimer() != null) {
            blipSequence -= 1;
        } else {
            blipSequence = FRAME_TO_SKIP;
        }
    }
}
