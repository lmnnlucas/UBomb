/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.StatusBar;
import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.launcher.Entity;

import java.util.List;

/**
 * The type Player.
 */
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

    /**
     * Takes a key.
     *
     * @param key the key
     */
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

    /**
     * Indique si le déplacement du joueur est possible.
     * Si le déplacement est possible, on vérifie si le joueur se trouve un bonus
     * Si le joueur touche un ennemi, il perd une vie
     *
     * @param direction La direction du déplacement demandé
     */
    public void doMove(Direction direction) {
        // This method is called only if the move is possible, do not check again
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.grid().get(nextPos);
        // Adapter pour récupérer pas que décors
        if (next != null) {
            System.out.println("Next : " + next.toString());
        }
        if (next instanceof Bonus bonus) {
            bonus.takenBy(this);
        }

        // TODO: Récupérer le monstre

            /*if ((GameObject) entity instanceof Monster monster) {
                System.out.println("Monstre touché, vie perdue");
                this.lives--;
                if (lives == 0) {
                    System.out.println("Game Over");
                } else {
                    System.out.println("Vies restantes : " + this.getLives());
                }
            }*/

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
        boolean isMonster = next instanceof Monster;

        if (nextPos.x() > game.grid().width() - 1 || nextPos.x() < 0) {
            return false;
        } else if (nextPos.y() > game.grid().height() - 1 || nextPos.y() < 0) {
            return false;
        }
        if (isBonus) {
            return true;
        } else if (isMonster) {
            return false;
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
