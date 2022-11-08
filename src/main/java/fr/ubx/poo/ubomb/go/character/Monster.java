package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public class Monster extends GameObject implements Movable {

    public Monster(Game game, Position position) {
        super(game, position);
    }

    @Override
    public boolean canMove(Direction direction) {
        return false;
    }

    @Override
    public void doMove(Direction direction) {

    }

    @Override
    public boolean walkableBy(Player player) {
        return super.walkableBy(player);
    }
}
