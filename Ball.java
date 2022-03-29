package pong;

import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private Label _scoreLabel;
    private Circle _ball;
    private Timeline _timeline;
    private int[] _direction;
    private Platform _platform1;
    private Platform _platform2;
    private int _player1Score;
    private int _player2Score;

    public Ball(Pane gamePane, Label scoreLabel, Timeline timeline, Platform platform1, Platform platform2) {

        _scoreLabel = scoreLabel;
        _timeline = timeline;
        _ball = new Circle(Constants.BALL_X, Constants.SCENE_MID_HEIGHT, Constants.BALL_RADIUS, Color.BLACK);
        gamePane.getChildren().add(_ball);
        _direction = new int[]{1, 1};

        _platform1 = platform1;
        _platform2 = platform2;

        _player1Score = 0;
        _player2Score = 0;
    }

    public void move() {

        double newX = _ball.getCenterX() + _direction[0];
        double newY = _ball.getCenterY() + _direction[1];

        // Checks if new ball location is out of bounds. If the ball hits the top or bottom edge of screen, it bounces. If the
        // ball hits the left or right edge of the screen, points are given accordingly.
        if (newX + Constants.BALL_RADIUS <= 0) {

            _player2Score++;
            this.resetRound();
        } else if (newX + Constants.BALL_RADIUS >= Constants.SCENE_WIDTH) {

            _player1Score++;
            this.resetRound();
        } else if (newY + Constants.BALL_RADIUS <= 0 || newY + Constants.BALL_RADIUS >= Constants.SCENE_HEIGHT) {

            _direction[1] *= -1;
            this.relocateBall();
        } else if ( (_ball.intersects(_platform1.getX(), _platform1.getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT) &&
                _direction[0] == -1) || (_ball.intersects(_platform2.getX(), _platform2.getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT) &&
                _direction[0] == 1) ) {

            _direction[0] *= -1;
            this.relocateBall();
        } else {

            this.relocateBall();
        }
    }

    public void resetRound() {

        _scoreLabel.setText("Player 1: " + _player1Score + "     Player 2: " + _player2Score);
        _ball.setCenterX(Constants.BALL_X);
        _ball.setCenterY(Constants.SCENE_MID_HEIGHT);
        _direction = new int[]{1, 1};
        _timeline.stop();
    }

    private void relocateBall() {

        _ball.setCenterX(_ball.getCenterX() + _direction[0]*Constants.BALL_SPEED);
        _ball.setCenterY(_ball.getCenterY() + _direction[1]*Constants.BALL_SPEED);
    }
}
