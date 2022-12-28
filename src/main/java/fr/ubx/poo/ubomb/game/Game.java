package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.door.Door;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private ArrayList<Grid> levels;
    private final Configuration configuration;
    private final Player player;
    private final ArrayList<Monster> monsters;
    private Grid grid; // final
    private int gridNumber;
    private boolean gridNeedUpdate;

    public Game(Configuration configuration, Grid grid) {
        this.configuration = configuration;
        this.grid = grid;
        this.gridNumber = 1;
        this.levels = new ArrayList<>();
        monsters = new ArrayList<>();
        player = new Player(this, configuration.playerPosition());
        monsters.add(new Monster(this, new Position(2,2)));
        monsters.add(new Monster(this, new Position(2,3)));
    }

    public Game(Configuration configuration, List<Grid> levels) {
        this.configuration = configuration;
        this.gridNumber = 1;
        this.grid = levels.get(0);
        this.levels = new ArrayList<>(levels);
        player = new Player(this, configuration.playerPosition());
        monsters = new ArrayList<>();
    }

    public Configuration configuration() {
        return configuration;
    }

    // Returns the player, monsters and bomb at a given position
    public List<GameObject> getGameObjects(Position position) {
        List<GameObject> gos = new LinkedList<>();
        if (player().getPosition().equals(position))
            gos.add(player);
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position))
                gos.add(monster);
        }
        if (grid.get(position) != null)
            gos.add(grid.get(position));
        return gos;
    }

    public ArrayList<Monster> monster() { return this.monsters; }

    public void changeLevel(int levelModifier) {
        gridNeedUpdate = true;
        gridNumber += levelModifier;
    }

    public void updateGridForNewLevel() {
        grid = levels.get(gridNumber-1);
        player.setPosition(grid.values().stream()
                .filter(v -> v instanceof Door && (((Door) v).getLevelModifier() == -1))
                .map(v -> v.getPosition())
                .findFirst()
                .orElse(configuration.playerPosition()));
    }

    public int getGridNumber() {
        return gridNumber;
    }

    public boolean gridNeedUpdate() {
        return gridNeedUpdate;
    }

    public void gridUpdated() {
        this.gridNeedUpdate = false;
    }

    public Grid grid() {
        return grid;
    }

    public Player player() {
        return this.player;
    }
}
