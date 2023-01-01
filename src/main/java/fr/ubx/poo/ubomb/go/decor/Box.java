package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;

public class Box extends Decor implements Movable, Takeable {
    public Box(Position position) {
        super(position);
    }
    private Game game;
    @Override
    public boolean walkableBy(Player player) {
        if (game == null)
            game = player.getGame();
        return canMove(player.getDirection());
    }

    @Override
    public boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        if (!game.grid().inside(nextPos)) {
            return false;
        }
        GameObject next = game.grid().get(nextPos);
        return next == null;
    }

    @Override
    public void doMove(Direction direction) {
        game.grid().remove(getPosition());
        this.setPosition(direction.nextPosition(getPosition()));
        game.grid().set(getPosition(), this);
        this.setModified(true);
    }

    @Override
    public void takenBy(Player player) {
        this.doMove(player.getDirection());
    }
}
