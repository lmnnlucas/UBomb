package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.character.Player;

public interface Walkable {
    default boolean walkableBy(Player player) { return false; }
}
