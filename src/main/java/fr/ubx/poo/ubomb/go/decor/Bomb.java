package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.Player;

import java.util.ArrayList;

public class Bomb extends Decor implements Walkable {
    private Timer timer;
    private int gridNumber;
    private int range;
    private boolean detonated; // Prevent overflow issue in case of concurrency

    private ArrayList<Position> explosionBounds;

    public Bomb(Game game, Position position) {
        super(game, position);
        timer = new Timer(3000);
        timer.start();
        range = game.player().getBombRange();
        gridNumber = game.getGridNumber();
        explosionBounds = new ArrayList<>();
        detonated = false;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getGridNumber() {
        return gridNumber;
    }

    public ArrayList<Position> getExplosionBounds() {
        return explosionBounds;
    }

    /**
     * Propagate the shockwave in the specified direction and return the ending position
     * @param direction The shockwave direction.
     * @return The shockwave ending position.
     */
    private Position propagateExplosion(Direction direction) {
        Position currentPosition = getPosition();
        Position previousValidPosition = getPosition();
        for(int i = 0; i < range; i++) {
            currentPosition = direction.nextPosition(currentPosition);
            if(game.grid().inside(currentPosition)) {
                Decor currentEncounter = game.getGrid(gridNumber).get(currentPosition);
                if(game.getGridNumber() == gridNumber && currentPosition.equals(game.player().getPosition())) {
                    game.player().explode();
                    previousValidPosition = currentPosition;
                } else if (currentEncounter == null) { // Empty tiles
                    previousValidPosition = currentPosition;
                } else if (currentEncounter.walkableBy(game.player()) && !(currentEncounter instanceof Box)) { // Non-Blocking tiles
                    currentEncounter.explode();
                    previousValidPosition = currentPosition;
                } else { // Blocking tiles
                    currentEncounter.explode();
                    break;
                }
            } else {
                return previousValidPosition;
            }
        }
        return currentPosition;
    }

    @Override
    public void explode() {
        if(!detonated) {
            detonated = true;
            for (Direction d : Direction.values()) {
                explosionBounds.add(propagateExplosion(d));
            }
            game.player().postExplosionTreatment(this);
        }
    }

    public boolean hasDetonated() {
        return detonated;
    }

    @Override
    public boolean walkableBy(Player player) {
        return true;
    }
}
