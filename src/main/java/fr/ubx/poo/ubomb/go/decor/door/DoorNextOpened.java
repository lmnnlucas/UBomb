package fr.ubx.poo.ubomb.go.decor.door;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Decor;

public class DoorNextOpened extends Door {
    public DoorNextOpened(Position position) {
        super(position,false,1);
    }
}
