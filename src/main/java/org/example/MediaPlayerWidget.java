package org.example;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MediaPlayerWidget {
    MediaPlayer mediaPlayer;
    ArrayList<Media> musicList;

    Button reverseButton;
    Button playButton;
    Button forwardButton;

    HBox widget;

    int currentSong = 0;

    public MediaPlayerWidget() {
        musicList = new ArrayList<Media>();

        //Add default
        try {
            musicList.add(new Media(getClass().getResource("/music/fight.mp3").toURI().toString()));
            musicList.add(new Media(getClass().getResource("/music/fight-remix-2-.mp3").toURI().toString()));
            musicList.add(new Media(getClass().getResource("/music/training-remix.mp3").toURI().toString()));
            musicList.add(new Media(getClass().getResource("/music/fight-rock-remix-.mp3").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mediaPlayer = new MediaPlayer(musicList.get(currentSong));
        mediaPlayer.getAudioEqualizer();
        mediaPlayer.seek(Duration.millis(0));

        reverseButton = new Button("Reverse");
        reverseButton.setOnAction(e -> {
            currentSong--;
            stop();
            mediaPlayer = new MediaPlayer(musicList.get(currentSong % 5));
        });

        mediaPlayer.setOnEndOfMedia(this::forward);

        playButton = new Button("Play");
        playButton.setOnAction(e -> {
            toggle();
        });

        forwardButton = new Button("Forward");
        forwardButton.setOnAction(e -> {
            forward();
        });

        widget = new HBox(10, reverseButton, playButton, forwardButton);
    }

    private void stop(){
        playButton.setText("Play");
        mediaPlayer.stop();
    }

    private void forward(){
        currentSong++;
        stop();
        mediaPlayer = new MediaPlayer(musicList.get(currentSong % 5));
    }

    private void toggle(){
        if (playButton.getText().equals("Play")) {
            playButton.setText("Pause");
            mediaPlayer.play();
        } else {
            playButton.setText("Play");
            mediaPlayer.pause();
        }
    }
    public Node getWidget(){
        return widget;
    }
}
