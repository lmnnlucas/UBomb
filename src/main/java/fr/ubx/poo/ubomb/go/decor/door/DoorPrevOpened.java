package fr.ubx.poo.ubomb.go.decor.door;

import fr.ubx.poo.ubomb.game.Position;

public class DoorPrevOpened extends Door {
    public DoorPrevOpened(Position position) {
        super(position, false, -1);
    }
}
