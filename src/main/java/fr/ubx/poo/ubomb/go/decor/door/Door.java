package fr.ubx.poo.ubomb.go.decor.door;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Decor;

public class Door extends Decor implements Takeable, Walkable {
    private boolean locked;

    private final int levelModifier;

    public Door(Position position, boolean isLocked, int levelModifier) {
        super(position);
        this.locked = isLocked;
        this.levelModifier = levelModifier;
    }

    public int getLevelModifier() {
        return levelModifier;
    }

    public void unlockDoor() {
        locked = false;
        setModified(true);
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean walkableBy(Player player) {
        return !locked;
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }
}
