package nl.thairosi.sat.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.thairosi.sat.Models.Cube;
import nl.thairosi.sat.Models.ShapeType;
import nl.thairosi.sat.Utils.ShapeDAO;

/**
 * The AddCubeController class handles all necessary actions for adding the shape in a new FXML Stage
 */
public class AddCubeController {

    @FXML
    public TextField textName;
    public TextField textEdge;
    public TextField textVolume;
    public Button buttonSaveShape;
    public Label labelFeedback;

    /**
     * Creates a new Shape Object and adds it to the database with the save shape button
     */
    public void addShape() {
        try {
            if (textEdge.getText().isBlank() || textName.getText().isBlank()) {
                labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                labelFeedback.setText("Something went wrong, make sure all fields are set");
            } else {
                String name = textName.getText();
                double edge = Double.parseDouble(textEdge.getText());
                Cube cube = new Cube(name, ShapeType.CUBE, edge);
                ShapeDAO shapeDAO = new ShapeDAO();
                shapeDAO.createShape(cube);
                Stage stage = (Stage) buttonSaveShape.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            labelFeedback.setText("Something went wrong, check if the values are correct");
        }
    }

    /**
     * Gets and shows the calculated volume live conform the inserted values in the add shape window
     */
    public void calcVolume() {
        try {
            double edge = Double.parseDouble(textEdge.getText());
            Cube cube = new Cube("cube", ShapeType.CUBE, edge);
            double volume = cube.calcVolume();
            textVolume.setText(String.valueOf(volume));
        } catch (Exception e) {
            textVolume.setText("N/A");
        }
    }
}
