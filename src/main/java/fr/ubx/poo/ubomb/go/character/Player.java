/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.decor.Bomb;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.door.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Player.
 */
public class Player extends Character implements Movable, TakeVisitor {

    private Direction direction;
    private boolean moveRequested = false;
    private int keys;
    private int bombRange;
    private int bombBag;
    private boolean bombPlaced;
    private boolean haveWon = false;
    private ArrayList<Bomb> bombs;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.lives = game.configuration().playerLives();
        this.bombRange = 1;
        this.bombBag = 1;
        this.bombs = new ArrayList<>();
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

    @Override
    public void take(Princess princess) {
        princess.remove();
        haveWon = true;
    }

    @Override
    public void take(Door door) {
        if(keys > 0 && door.isLocked()) {
            door.unlockDoor();
            keys--;
        } else if (!door.isLocked()) {
            game.changeLevel(door.getLevelModifier());
        }
    }

    @Override
    public void take(BombRangeModifier bombRangeModifier) {
        this.bombRange += bombRangeModifier.getRangeModifier();
        if(bombRange < 1) {
            bombRange = 1;
        }
        bombRangeModifier.remove();
    }

    @Override
    public void take(BombNumberModifier bombNumberModifier) {
        this.bombBag += bombNumberModifier.getModifier();
        if(bombBag < 1) {
            bombBag = 1;
        }
        bombNumberModifier.remove();
    }

    public void doMove(Direction direction) {

        // This method is called only if the move is possible, do not check again
        Position nextPos = direction.nextPosition(getPosition());

        // Check if the next position is a takeable object
        List<GameObject> objectList = game.getGameObjects(nextPos);
        for (GameObject object : objectList) {
            object.takenBy(this);
        }
        setPosition(nextPos);
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

    public boolean isBombPlaced() {
        return bombPlaced;
    }

    public void bombIsRendered() {
        bombPlaced = false;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void postExplosionTreatment(Bomb bomb) {
        bombBag += 1;
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

        if (!game.grid().inside(nextPos)) {
            return false;
        }

        return next==null || next.walkableBy(this);
    }

    public void interactWithDoor() {
        List<GameObject> object = game.getGameObjects(direction.nextPosition(getPosition()));
        if(object.size() > 0) {
            object.get(0).takenBy(this);
        }
    }

    public void placeABomb() {
        if(bombBag > 0) {
            Bomb bomb = new Bomb(game,getPosition());
            bombs.add(bomb);
            game.grid().set(getPosition(),bomb);
            bombBag -= 1;
            bombPlaced = true;
        }
    }

    @Override
    public void update(long now) {
        super.update(now);
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    @Override
    public void explode() {
        damageHandler(game.configuration().playerInvincibilityTime());
    }

    public boolean haveWon(){
        return haveWon;
    }
    public Game getGame() {
        return game;
    }

    @Override
    public void damage() {
        damageHandler(game.configuration().playerInvincibilityTime());
    }
}
