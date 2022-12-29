package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public class Monster extends GameObject implements Movable {

    private Direction direction;

    public Monster(Game game, Position position) {
        super(game, position);
        direction = Direction.DOWN;
    }
    //TODO : Faire une interface pour les monstres et les joueurs
    @Override
    public boolean canMove(Direction direction) {
        return false;
    }

    @Override
    public void doMove(Direction direction) {

    }

    @Override
    public void explode() {

    }

    @Override
    public boolean walkableBy(Player player) {
        return super.walkableBy(player);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }
}
