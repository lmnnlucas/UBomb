package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.engine.GameEngine;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.launcher.GameLauncher;
import fr.ubx.poo.ubomb.launcher.MapLevel;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class GameLauncherView extends BorderPane {
    private final FileChooser fileChooser = new FileChooser();

    public GameLauncherView(Stage stage)  {
        // Create menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem loadItem = new MenuItem("Load from file ...");
        MenuItem defaultItem = new MenuItem("Load default configuration");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        menuFile.getItems().addAll(
                loadItem, defaultItem, new SeparatorMenuItem(),
                exitItem);

        menuBar.getMenus().addAll(menuFile);
        this.setTop(menuBar);

        Text text = new Text("UBomb 2022");
        text.getStyleClass().add("message");
        VBox scene = new VBox();
        scene.getChildren().add(text);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        scene.getStyleClass().add("message");
        this.setCenter(scene);

        // Load from file
        loadItem.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // TODO
                System.err.println("[TODO] Not implemented");
            }
        });


        defaultItem.setOnAction(e -> {
            Game game = GameLauncher.load();
            GameEngine engine = new GameEngine(game, stage);
            engine.start();
        });

        // Exit
        exitItem.setOnAction(e -> System.exit(0));

    }


}
