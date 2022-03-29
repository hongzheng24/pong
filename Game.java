package pong;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {

    private Pane _gamePane;
    private Label _scoreLabel;
    private Timeline _timeline;
    private KeyHandler _keyHandler;
    private Ball _ball;
    private Platform _platform1;
    private Platform _platform2;

    public Game(Pane gamePane, Label scoreLabel) {

        _gamePane = gamePane;
        _scoreLabel = scoreLabel;
        this.setupTimeline();

        _keyHandler = new Game.KeyHandler();
        _gamePane.addEventHandler(KeyEvent.KEY_PRESSED, _keyHandler);

        _platform1 = new Platform(_gamePane, Constants.PLATFORM1_X);
        _platform2 = new Platform(_gamePane, Constants.PLATFORM2_X);
        _ball = new Ball(_gamePane, _scoreLabel, _timeline, _platform1, _platform2);
    }

    public void setupTimeline() {

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
        _timeline = new Timeline(keyFrame);
        _timeline.setCycleCount(Animation.INDEFINITE);
        //_timeline.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            _ball.move();
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {

            KeyCode keyPressed = e.getCode();
            switch (keyPressed) {

                case SPACE:
                    _timeline.play();
                    break;
                case UP:
                    _platform2.moveUp();
                    break;
                case DOWN:
                    _platform2.moveDown();
                    break;
                case W:
                    _platform1.moveUp();
                    break;
                case S:
                    _platform1.moveDown();
                    break;
            }
            e.consume();
        }
    }
}
