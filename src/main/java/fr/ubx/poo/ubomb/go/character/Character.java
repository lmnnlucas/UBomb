package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;

public abstract class Character extends GameObject implements Movable {

    protected int lives;
    private Timer invicibilityTimer;

    public Character(Game game, Position position) {
        super(game,position);
        invicibilityTimer = null;
    }

    public void damageHandler(long time) {
        if(invicibilityTimer == null) {
            invicibilityTimer = new Timer(time);
            invicibilityTimer.start();
            lives -= 1;
            if(lives <= 0) {
                remove();
            }
        }
    }

    public void update(long now) {
        if(invicibilityTimer != null && invicibilityTimer.isRunning()) {
            invicibilityTimer.update(now);
            setModified(true);
        } else if (invicibilityTimer != null){
            invicibilityTimer = null;
            setModified(true);
        }
    }

    public void damage() {}

    public Timer getInvicibilityTimer() {
        return invicibilityTimer;
    }
}
