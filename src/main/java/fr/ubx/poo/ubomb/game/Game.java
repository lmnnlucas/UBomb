package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Decor;
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

    private int lastGridNumberChange;

    public Game(Configuration configuration, Grid grid) {
        this.configuration = configuration;
        this.grid = grid;
        this.gridNumber = 0;
        this.levels = new ArrayList<>();
        this.levels.add(grid);
        monsters = new ArrayList<>();
        player = new Player(this, configuration.playerPosition());
        setupMonsters();
    }

    public Game(Configuration configuration, List<Grid> levels) {
        this.configuration = configuration;
        this.gridNumber = 0;
        this.grid = levels.get(0);
        this.levels = new ArrayList<>(levels);
        player = new Player(this, configuration.playerPosition());
        monsters = new ArrayList<>();
        setupMonsters();
    }

    private void setupMonsters() {
        for (int i = 0; i < levels.size(); i++) {
            for (Position mp : levels.get(i).getMonstersPositions()) {
                Monster monster = new Monster(this,mp);
                monster.setup(i);
                monsters.add(monster);
            }
        }
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
            if (monster.getPosition().equals(position) && isOnSameGrid(monster.getGridNumber()))
                gos.add(monster);
        }
        if (grid.get(position) != null)
            gos.add(grid.get(position));
        return gos;
    }

    public ArrayList<Monster> monster() { return this.monsters; }

    /**
     * Trigger the level change logic by notifying the GameEngine that the grid will change,
     * the grid modifier (-1 or +1) is stored in lastGridNumberChange to retrieve the door by which the player
     * has entered the level.
     *
     * @param levelModifier The grid number change (-1 or +1)
     */
    public void changeLevel(int levelModifier) {
        gridNeedUpdate = true;
        gridNumber += levelModifier;
        lastGridNumberChange = levelModifier;
    }

    /**
     * Update the current grid and set the player position to the door that has the opposite modifier on the next level
     */
    public void updateGridForNewLevel() {
        grid = levels.get(gridNumber);
    }

    public int getGridNumber() {
        return gridNumber;
    }

    public boolean gridNeedUpdate() {
        return gridNeedUpdate;
    }

    public void gridUpdated() {
        this.gridNeedUpdate = false;
        player.setPosition(grid.values().stream()
                .filter(v -> v instanceof Door && (((Door) v).getLevelModifier() == -lastGridNumberChange))
                .map(v -> v.getPosition())
                .findFirst()
                .orElse(configuration.playerPosition()));
    }

    public Grid getGrid(int level) {
        return levels.get(level);
    }

    public Grid grid() {
        return grid;
    }

    public Player player() {
        return this.player;
    }

    public boolean isOnSameGrid(int nbGrid) {
        return gridNumber == nbGrid;
    }
}
