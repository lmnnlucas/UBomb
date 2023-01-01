package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.decor.Decor;

import java.util.List;

public class Monster extends Character implements Movable, Walkable {

    private Direction direction;
    private int gridNumber;
    private boolean revealed;
    private long lastMovementTime;
    private boolean movementTimeExceeded;

    public Monster(Game game,Position position) {
        super(game,position);
        direction = Direction.DOWN;
        revealed = false;
        lives = 1;
    }

    @Override
    public boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.getGrid(gridNumber).get(nextPos);

        return movementTimeExceeded
                && revealed
                && (next == null || next.walkableBy(this))
                && game.getGrid(gridNumber).inside(nextPos);
    }

    @Override
    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        setModified(true);
    }

    public void setup(int gridNumber) {
        this.gridNumber = gridNumber;
        if(gridNumber % 2 == 0) {
            lives = (gridNumber / 2) + 1;
        } else if(gridNumber == 0) {
            reveal();
        }
    }

    @Override
    public void update(long now) {
        super.update(now);
        if(game.isOnSameGrid(gridNumber) && !revealed) {
            reveal();
        }
        movementTimeExceeded = now >= lastMovementTime + (Math.pow(10,10) / game.configuration().monsterVelocity());
        Direction tmp = Direction.random();
        if (canMove(tmp)) {
            doMove(tmp);
            lastMovementTime = now;
            direction = tmp;
        }
    }

    public void reveal() {
        revealed = true;
    }

    public int getGridNumber() {
        return gridNumber;
    }

    @Override
    public void explode() {
        damageHandler(game.configuration().monsterInvincibilityTime());
    }

    @Override
    public boolean walkableBy(Player player) {
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void remove() {
        super.remove();
        game.monster().remove(this);
    }

    @Override
    public void damage() {
        damageHandler(game.configuration().monsterInvincibilityTime());
    }
}
