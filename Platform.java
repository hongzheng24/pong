package pong;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Platform {

    private Rectangle _platform;

    public Platform(Pane gamePane, int x) {

        _platform = new Rectangle(x, Constants.PLATFORM_Y, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        gamePane.getChildren().add(_platform);
    }

    public void moveUp() {

        double y = _platform.getY();
        if (y > 0) {
            _platform.setY(y - Constants.PLATFORM_SPEED);
        }
    }

    public void moveDown() {

        double y = _platform.getY();
        if (y + Constants.PLATFORM_HEIGHT < Constants.SCENE_HEIGHT) {
            _platform.setY(y + Constants.PLATFORM_SPEED);
        }
    }

    public double getX() {
        return _platform.getX();
    }

    public double getY() {
        return _platform.getY();
    }
}
