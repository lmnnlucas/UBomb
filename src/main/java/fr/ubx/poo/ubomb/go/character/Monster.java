package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.decor.Decor;

public class Monster extends Decor implements Movable, Walkable {

    private Direction direction;

    public Monster(Position position) {
        super(position);
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
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }
}
