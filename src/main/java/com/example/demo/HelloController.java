package com.example.demo;

import com.example.demo.service.ImageStrategy;
import com.example.demo.service.ImageStrategyFactory;
import com.example.demo.service.model.Response;
import com.example.demo.service.util.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
                String text = "Height: " + image.getHeight() + '\n'
                        + "Width: " + image.getWidth() + '\n'
                        + "Location: " + image.getUrl() + '\n';
                propertyText.setText(text);
                imageView.setImage(image);
            } catch (Exception e) {
                alertError("Failed to load image: " + e.getMessage());
            }
        }
    }

    private static void alertError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    private static void alertInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    protected void handleDownloadFile(ActionEvent event) {
        if (selectedFile == null) {
            return;
        }
        // must choose a file
        if (selectedFile.isDirectory()) {
            alertError("Must Choose a file");
            return;
        }
        ImageStrategy strategy = ImageStrategyFactory.getStrategy(ImageUtil.getFileType(selectedFile.getName()));
        String targetType = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
        // valid
        Response validResponse = strategy.validSourceFile(selectedFile.getAbsolutePath(), targetType);
        if (validResponse.getCode() == Response.ERROR) {
            alertError(validResponse.getMessage());
            return;
        } else if (validResponse.getCode() == Response.WARNING) {
            alertInfo(validResponse.getMessage());
        }

        // Choose folder to save file
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");
        File destDirectory = directoryChooser.showDialog(new Stage());
        if (destDirectory == null) {
            return;
        }
        // target file info
        String targetFileName = selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.')) + "." + targetType;

        // convert image
        Response convert = strategy.convert(selectedFile.getAbsolutePath(), destDirectory.getAbsolutePath() + File.separator + targetFileName);
        if (convert.getCode() != Response.OK) {
            alertError(convert.getMessage());
        } else {
            alertInfo("Convert Success!");
        }

    }
}