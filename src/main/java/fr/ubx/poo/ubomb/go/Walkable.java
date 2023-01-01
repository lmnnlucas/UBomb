package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;

public interface Walkable {
    default boolean walkableBy(Player player) { return false; }

    default boolean walkableBy(Monster monster) {return false; }
}
