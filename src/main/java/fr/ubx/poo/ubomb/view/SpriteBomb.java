package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.decor.Bomb;
import javafx.scene.layout.Pane;

public class SpriteBomb extends Sprite {

    public SpriteBomb(Pane layer, Bomb bomb) {
        super(layer, null, bomb);
        updateImage();
    }

    @Override
    public void updateImage() {
        setImage(ImageResourceFactory.getBomb(((int)((Bomb)getGameObject()).getTimer().remaining())/1000).getImage());
    }
}
