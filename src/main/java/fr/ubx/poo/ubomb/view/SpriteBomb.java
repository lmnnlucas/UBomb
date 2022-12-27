package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.decor.Bomb;
import javafx.scene.layout.Pane;

public class SpriteBomb extends Sprite {
    private Bomb bomb;

    public SpriteBomb(Pane layer, Bomb bomb) {
        super(layer, null, bomb);
        this.bomb = bomb;
        updateImage();
    }

    @Override
    public void updateImage() {
        setImage(ImageResourceFactory.getBomb(((int)bomb.getTimer().remaining())/1000).getImage());
    }
}
