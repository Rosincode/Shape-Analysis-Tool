package nl.thairosi.sat.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.thairosi.sat.App;
import nl.thairosi.sat.Models.Shape;
import nl.thairosi.sat.Models.ShapeType;
import nl.thairosi.sat.Utils.DoubleUtils;
import nl.thairosi.sat.Utils.ShapeDAO;
import nl.thairosi.sat.Utils.ShapeIO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The HomeViewController class handles all necessary actions for the HomeView GUI of the application
 * This Class implements the Initializable class to automatically execute actions after the FXML attributes are loaded
 */
public class HomeViewController implements Initializable {
    private ShapeDAO shapeDAO;
    private ShapeIO shapeIO;

    @FXML
    public Button buttonImportShapes;
    public Button buttonExportTxt;
    public Button buttonExportObj;
    public Button buttonAddShape;
    public Button buttonDeleteShape;
    public TableView<String> tableSelectShape;
    public TableColumn<String, String> columnSelectShapeType;
    public TableView<Shape> tableShapes;
    public TableColumn<Shape, String> columnShapeType;
    public TableColumn<Shape, String> columnShapeID;
    public TableColumn<Shape, String> columnShapeName;
    public TableColumn<Shape, String> columnShapeSizes;
    public TextField textShapeVolume;
    public TextField textTotalVolume;
    public Label labelFeedback;

    /**
     * Initializes the ShapeDAO and ShapeIO class for the HomeViewController
     * Executes the getShapes() and the getShapeType() methods after loading the FXML GUI
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.shapeDAO = new ShapeDAO();
        this.shapeIO = new ShapeIO();
        getShapes();
        getShapeTypes();
    }

    /**
     * Fills the GUI select shape table with all shapeTypes present in the ShapeType Enum
     */
    private void getShapeTypes() {
        ObservableList<String> shapeTypes = FXCollections.observableArrayList();
        for (ShapeType shapeType : ShapeType.values()) {
            shapeTypes.add(shapeType.toString());
        }
        tableSelectShape.getItems().clear();
        tableSelectShape.getItems().addAll(shapeTypes);
        columnSelectShapeType.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue())));
    }

    /**
     * Fills the GUI shape table with all shapes that are present in the database
     */
    public void getShapes() {
        ObservableList<Shape> shapes;
        try {
            shapes = shapeDAO.readShapes();
        } catch (SQLException throwable) {
            tableShapes.getItems().clear();
            labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            labelFeedback.setText("There is no connection with the database");
            textTotalVolume.setText("N/A");
            textShapeVolume.setText("");
            buttonImportShapes.setDisable(true);
            buttonExportTxt.setDisable(true);
            buttonExportObj.setDisable(true);
            buttonAddShape.setDisable(true);
            buttonDeleteShape.setDisable(true);
            return;
        }
        if (calcTotalVolume() == 0.0) {
            textTotalVolume.setText("N/A");
        } else {
            textTotalVolume.setText(calcTotalVolume() + " L");
        }
        tableShapes.getItems().clear();
        tableShapes.getItems().addAll(shapes);
        columnShapeType.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getShapeType())));
        columnShapeID.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        columnShapeName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        columnShapeSizes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDimensions()));
        labelFeedback.setText("");
        buttonImportShapes.setDisable(false);
        buttonExportTxt.setDisable(false);
        buttonExportObj.setDisable(false);
        buttonAddShape.setDisable(false);
        buttonDeleteShape.setDisable(false);
    }

    /**
     * Calculates the total volume of the loaded shapes
     *
     * @return the total volume
     */
    public double calcTotalVolume() {
        double totalVolume = 0;
        ArrayList<Shape> shapes = getShapesArrayList();
        if (shapes == null) {
            textTotalVolume.setText("N/A");
        } else {
            for (Shape shape : shapes) {
                double shapeVolume = shape.calcVolume();
                totalVolume += shapeVolume;
            }
        }

        return DoubleUtils.round(totalVolume);
    }

    /**
     * Starts a new create shape stage conform the selected shape
     */
    public void addShape() {
        int countBefore = getShapesArrayList().size();
        String newShapeSelected = null;
        try {
            String selectedNewShape = tableSelectShape.getSelectionModel().getSelectedItem();
            String lowerCase = selectedNewShape.toLowerCase();
            newShapeSelected = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
        } catch (Exception e) {
            labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            labelFeedback.setText("Please select a shape to add");
        }
        try {
            Stage stage = new Stage();
            stage.setTitle("Shape Analysis Tool");
            Scene scene = new Scene(loadFXML("add" + newShapeSelected));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countAfter = getShapesArrayList().size();
        if (countAfter > countBefore) {
            getShapes();
            labelFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            labelFeedback.setText("New " + newShapeSelected + " has been successfully added to the database");
        }


    }

    /**
     * Loads the FXML that is assigned in the fxml parameter
     *
     * @param fxml represents the name of the fxml file to be loaded
     * @return the new initialized FXMLLoader object and its load() method
     * @throws IOException when the fxml file is not found
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Deletes the in the GUI table selected shape
     */
    public void deleteShape() {
        try {
            Shape selectedShape = tableShapes.getSelectionModel().getSelectedItem();
            ShapeDAO shapeDAO = new ShapeDAO();
            shapeDAO.deleteShape(selectedShape);
            getShapes();
            textShapeVolume.setText("");
        } catch (Exception e) {
            labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            labelFeedback.setText("Please select a shape to delete");
        }
    }

    /**
     * Exports all shapes present in the database into an object file
     */
    public void exportShapesToObj() {
        ArrayList<Shape> shapes = getShapesArrayList();
        if (shapes == null) {
            return;
        }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter objExtensionFilter = new FileChooser.ExtensionFilter("OBJ files (*.obj)", "*.obj");
        fileChooser.getExtensionFilters().addAll(objExtensionFilter);
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            shapeIO.exportObj(file, shapes);
            labelFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            labelFeedback.setText("Shapes are successfully exported to file: " + file.getName());
        }
    }

    /**
     * Exports all shapes present in the database into a text file
     */
    public void exportShapesToTxt() {
        ArrayList<Shape> shapes = getShapesArrayList();
        if (shapes == null) {
            return;
        }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter txtExtensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(txtExtensionFilter);
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            shapeIO.exportTxT(file, shapes);
            labelFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            labelFeedback.setText("Shapes are successfully exported to file: " + file.getName());
        }
    }

    /**
     * Import shapes from a text or object file into the database without duplicating the currently loaded shapes
     */
    public void importShapes() {
        ArrayList<Shape> shapes = getShapesArrayList();
        if (shapes == null) {
            return;
        }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter objExtensionFilter = new FileChooser.ExtensionFilter("OBJ files, TXT files (*.obj, *.txt)", "*.obj", "*.txt");
        fileChooser.getExtensionFilters().addAll(objExtensionFilter);
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        ArrayList<Shape> importedShapes = new ArrayList<>();
        ArrayList<Shape> shapesAlreadyInDatabase = new ArrayList<>();
        if (file != null) {
            try {
                importedShapes = shapeIO.readShapesFromFile(file);
            } catch (IOException | ClassNotFoundException e) {
                labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                labelFeedback.setText("The file: " + file + " could not be imported into the database!");
            }

            for (Shape importedShape : importedShapes) {
                if (shapes.contains(importedShape)) {
                    shapesAlreadyInDatabase.add(importedShape);
                    continue;
                }
                try {
                    shapeDAO.createShape(importedShape, importedShape.getId());
                } catch (SQLException throwable) {
                    tableShapes.getItems().clear();
                    labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                    labelFeedback.setText("There is no connection with the database");
                    textTotalVolume.setText("N/A");
                    textShapeVolume.setText("");
                    buttonImportShapes.setDisable(true);
                    buttonExportTxt.setDisable(true);
                    buttonExportObj.setDisable(true);
                    buttonAddShape.setDisable(true);
                    buttonDeleteShape.setDisable(true);
                }
            }

            getShapes();

            if (shapesAlreadyInDatabase.size() > 0) {
                StringBuilder shapesAlreadyKnown = new StringBuilder();
                for (Shape shape : shapesAlreadyInDatabase) {
                    shapesAlreadyKnown.append(shape.getName()).append("(").append(shape.getId()).append(") ");
                }
                labelFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
                labelFeedback.setText("The shapes are successfully imported into the database!\n"
                        + "The following " + shapesAlreadyInDatabase.size() + " shapes already exist: \n"
                        + shapesAlreadyKnown + "\n\n"
                );
            } else {
                labelFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
                labelFeedback.setText("The shapes are successfully imported into the database!");
            }

        }
    }

    /**
     * Gets al shapes that are present in the database and returns
     *
     * @return the shapes as an ArrayList
     */
    public ArrayList<Shape> getShapesArrayList() {
        ArrayList<Shape> shapes;
        try {
            shapes = shapeDAO.readShapesAsArrayList();
        } catch (SQLException throwable) {
            tableShapes.getItems().clear();
            labelFeedback.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            labelFeedback.setText("There is no connection with the database");
            textTotalVolume.setText("N/A");
            textShapeVolume.setText("");
            buttonImportShapes.setDisable(true);
            buttonExportTxt.setDisable(true);
            buttonExportObj.setDisable(true);
            buttonAddShape.setDisable(true);
            buttonDeleteShape.setDisable(true);
            return null;
        }
        return shapes;
    }

    /**
     * Gets the calculated volume of the selected shape and shows it in the Shape Volume text part of the GUI
     */
    public void updateSelectedShapeVolume() {
        try {
            double selectedShapeVolume = DoubleUtils.round(tableShapes.getSelectionModel().getSelectedItem().calcVolume());
            textShapeVolume.setText(selectedShapeVolume + " L");
        } catch (Exception e) {
            textShapeVolume.setText("");
        }
    }

    /**
     * Pressing the F5 key reloads the Shapes present in the database into the loaded Shapes section of the GUI
     *
     * @param keyEvent is necessary for reading key events in the FXML handle() method
     */
    @FXML
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F5) {
            getShapes();
        }
    }

}
