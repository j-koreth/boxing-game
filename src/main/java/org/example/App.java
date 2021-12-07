package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {
//    MediaPlayer mediaPlayer;
    MediaPlayerWidget mediaPlayerWidget;
    IntegerProperty score = new SimpleIntegerProperty();
    IntegerProperty health = new SimpleIntegerProperty(10);
    IntegerProperty enemyHealth = new SimpleIntegerProperty(10);
    Game game;

    static String  s1 = "";

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        mediaPlayerWidget = new MediaPlayerWidget();

        HBox top = new HBox();

        Region spacer = new Region();

        HBox scoreHBox = new HBox(10);
        Label scoreValueLabel = new Label();
        scoreValueLabel.textProperty().bind(score.asString());
        scoreHBox.getChildren().addAll(new Label("Score:"), scoreValueLabel);
        scoreHBox.setPadding(new Insets(10));

        HBox healthHBox = new HBox(10);
        ImageView heartImage = new ImageView(new Image("8bitheart.png"));
        heartImage.setFitWidth(30);
        heartImage.setFitHeight(30);
        ProgressBar healthBar = new ProgressBar();
        healthBar.progressProperty().bind(health.divide(10.0));
        healthHBox.getChildren().addAll(heartImage, healthBar);
        healthHBox.setPadding(new Insets(10));


        HBox enemyHealthBox = new HBox(10);
        ImageView enemyHeartImage = new ImageView(new Image("enemyheart.png"));
        enemyHeartImage.setFitWidth(30);
        enemyHeartImage.setFitHeight(30);
        ProgressBar enemyHealthBar = new ProgressBar();
        enemyHealthBar.progressProperty().bind(enemyHealth.divide(10.0));
        enemyHealthBox.getChildren().addAll(enemyHeartImage, enemyHealthBar);
        enemyHealthBox.setPadding(new Insets(10));



        top.getChildren().addAll(scoreHBox, new Separator(Orientation.VERTICAL), healthHBox, new Separator(Orientation.VERTICAL), enemyHealthBox, spacer, mediaPlayerWidget.getWidget());
        HBox.setHgrow(spacer, Priority.ALWAYS);
        top.setPadding(new Insets(10));

        borderPane.setTop(top);

        Canvas canvas = new Canvas(750,600);
        Canvas overlay = new Canvas(750,600);
        game = new Game(canvas.getGraphicsContext2D(), overlay.getGraphicsContext2D());
        Pane pane = new Pane(canvas, overlay);
        borderPane.setCenter(pane);

        var scene = new Scene(borderPane);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem play = new MenuItem("Play");
        MenuItem pause = new MenuItem("Pause");
        pane.setOnContextMenuRequested(e -> contextMenu.show(pane, e.getScreenX(), e.getScreenY()));

        pause.setOnAction(game::pause);
        play.setOnAction(game::play);
        contextMenu.getItems().addAll(play, pause);


        scene.getStylesheets().add(getClass().getResource("/css/main.css").toString());

        stage.setScene(scene);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.UP)){
                game.punch();
            }
            if(keyEvent.getCode().equals(KeyCode.DOWN)){
                game.block();
            }
        });

        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update();
                score.setValue(game.getScore());
                health.setValue(game.getHealth());
                enemyHealth.setValue(game.getEnemyHealth());

                if(game.win){
                    stop();
                }
//                else if(game.dead){
//                    stop();
//                }
            }
        }.start();

    }

    public static void main(String[] args) {
        launch();
    }

}