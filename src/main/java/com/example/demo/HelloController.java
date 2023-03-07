package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;

public class HelloController {
    @FXML
    private Button openButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label propertyText;
    @FXML
    private RadioButton jpg;
    @FXML
    private RadioButton png;
    @FXML
    private RadioButton gif;
    @FXML
    private RadioButton bmp;
    @FXML
    private RadioButton tiff;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Button downloadButton;

    private File selectedFile;

    @FXML
    protected void handleOpenFile(ActionEvent event) {
        // Create fileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please choose the file");

        // Create file filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.jpg", "*.jpeg",
                "*.png",
                "*.gif",
                "*.bmp", "*.dib",
                "*.tiff", "*.tif");
        fileChooser.getExtensionFilters().add(extFilter);

        // display dialog
        selectedFile = fileChooser.showOpenDialog(openButton.getScene().getWindow());

        if (selectedFile != null) {
            // Read image from path
            try {
                Image image = new Image(selectedFile.getPath());
                String text = "Height: "+image.getHeight()+ '\n'
                        +"Width: "+image.getWidth()+'\n'
                        +"Location: "+image.getUrl()+'\n';
                propertyText.setText(text);
                imageView.setImage(image);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to load image: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    protected void handleDownloadFile(ActionEvent event){
        System.out.println(toggleGroup.getSelectedToggle().toString());
    }
}