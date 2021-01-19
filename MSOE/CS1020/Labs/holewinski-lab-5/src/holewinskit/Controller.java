/**
 * CS1021 - Berisha
 * Winter 2021
 * Lab5 - Game Of Life
 * Name: Tyler Holewinski
 * Created 1/12/2021
 */

package holewinskit;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Pane gamePane;
    @FXML
    private LifeGrid lifeGrid;

    @FXML
    private Label lblDeadCells;
    @FXML
    private Label lblAliveCells;
    public void initialize(URL location, ResourceBundle resources) {
        assert gamePane != null :"fx:id=\"gamePane\" was not injected: check your FXML file 'game.fxml'.";
        lifeGrid = new LifeGrid(gamePane, 400, 400);
    }

    private void countCells() {
        double alive = 0; // double for percent calculation
        double dead = 0;

        for (Node cell : gamePane.getChildren()) {
            if (! (cell instanceof Cell))
                continue;

            if (((Cell) cell) .isAlive())
                alive++;
            else
                dead++;
        }

        lblAliveCells.setText("Alive Cells: " + alive + " [" + (int) (alive / (alive + dead) * 100) + "%]");
        lblDeadCells.setText("Dead Cells: " + dead + " [" + (int) (dead / (alive + dead) * 100) + "%]");
    }

    public void btnRandom_handleClick(ActionEvent actionEvent) {
        lifeGrid.randomize();
        countCells();
    }

    public void btnNext_handleClick(ActionEvent actionEvent) {
        lifeGrid.iterate();
        countCells();
    }

    public void gamePane_handleClick(MouseEvent mouseEvent) {
        Cell cell = null;

        final double x = mouseEvent.getX();
        final double y =mouseEvent.getY();

        for (Node n : gamePane.getChildren()) {
            if (n instanceof Cell && cell == null) {
                Cell c = (Cell) n;

                final double cx = c.getLayoutX();
                final double cy = c.getLayoutY();

                if (x < (c.getLayoutX() + c.getWidth()) && x > c.getLayoutX() && y < (c.getLayoutY() + c.getHeight()) && y > c.getLayoutY()) {
                    cell = c;
                }
            }
        }

        if (cell == null)
            return;

        cell.setAlive(! cell.isAlive());
        cell.updateColors();

        countCells();
    }
}
