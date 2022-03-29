package pong;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private BorderPane _root;
    private Pane _gamePane;
    private Label _scoreLabel;

    public PaneOrganizer() {

        /*_root = new BorderPane();
        _gamePane = new Pane();
        _root.setCenter(_gamePane);
        _gamePane.setFocusTraversable(true); //// request focus?
        new Game(_gamePane);*/

        _root = new BorderPane();
        _gamePane = new Pane();
        Pane bottomPane = new Pane();
        _scoreLabel = new Label();
        _scoreLabel.setText("Player 1: 0     Player 2: 0");

        _scoreLabel.setLayoutX(50);
        Button quitButton = new Button("Quit");
        bottomPane.getChildren().addAll(quitButton, _scoreLabel);
        quitButton.setOnAction(new PaneOrganizer.MoveHandler());

        _gamePane.setFocusTraversable(true);
        bottomPane.setFocusTraversable(false);
        _gamePane.requestFocus();

        _root.setCenter(_gamePane);
        _root.setBottom(bottomPane);

        new Game(_gamePane, _scoreLabel);
    }

    public BorderPane getRoot() {
        return _root;
    }

    private class MoveHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            System.exit(0);
        }
    }
}
