/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.decor.bonus.Bonus;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

public class Player extends GameObject implements Movable, TakeVisitor {

    private Direction direction;
    private boolean moveRequested = false;
    private int lives;
    private int keys;
    private int bombRange;
    private int bombBag;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.lives = game.configuration().playerLives();
        this.bombRange = 1;
        this.bombBag = 1;
    }

    @Override
    public void take(Key key) {
        keys++;
        key.remove();
    }

    @Override
    public void take(Hearth hearth) {
        lives++;
        hearth.remove();
    }

    public void doMove(Direction direction) {
        // This method is called only if the move is possible, do not check again
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.grid().get(nextPos);
        if (next instanceof Bonus bonus) {
                bonus.takenBy(this);
        }
        setPosition(nextPos);
    }

    public void plantBomb() {
        if(bombBag >= 1) {

        }
    }

    public int getLives() {
        return lives;
    }

    public int getKeys() {
        return keys;
    }

    public int getBombBag() {
        return bombBag;
    }

    public int getBombRange() {
        return bombRange;
    }

    public Direction getDirection() {
        return direction;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }

    public final boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.grid().get(nextPos);
        boolean isDecor = next instanceof Decor;
        boolean isBonus = next instanceof Bonus;


        if (nextPos.x() > game.grid().width()-1 || nextPos.x() < 0) {
            return false;
        } else if (nextPos.y() > game.grid().height()-1 || nextPos.y() < 0) {
            return false;
        }
        if(isBonus) {
            return true;
        } else {
            return !isDecor;
        }
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    @Override
    public void explode() {
        // TODO
    }
}
