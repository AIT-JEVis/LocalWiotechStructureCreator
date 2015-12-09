/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localwiotechstructurecreator;

import at.ac.ait.structurecreator.WiotechStructureCreator;
import java.awt.Dialog;
import java.awt.Frame;
import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author ait-user
 */
public class LocalWiotechStructureCreator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane rootPane = new GridPane();
        
        Label dbHost = new Label("DB Host");
        TextField hostTxt = new TextField();
        
        Label dbUser = new Label("DB User");
        TextField userTxt = new TextField();
        
        Label dbPwd = new Label("DB Passwort");
        TextField pwdTxt = new TextField();
        
        TextField fileTxt = new TextField();
        DirectoryChooser dirChooser = new DirectoryChooser();
        
        Label fileName = new Label("File Name");
        TextField fileNameTxt = new TextField();
        
        Button chooseBtn = new Button("Choose Directory");
        chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
                
                File selectedFile = dirChooser.showDialog(null);
                
                if (selectedFile != null) {
                    fileTxt.setText(selectedFile.getPath());
                } else {
                    fileTxt.setText("File selection cancelled.");
                }
            }
        });
        
        Button startBtn = new Button("Start");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            
                

                

        
                WiotechStructureCreator wsc = new WiotechStructureCreator(hostTxt.getText(), 3306, "db_lm_cbv2", 
                        userTxt.getText(), pwdTxt.getText(), (fileTxt.getText() + "/" + fileNameTxt.getText()));
                    
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Structure Creator");
                alert.setHeaderText(null);
                alert.setContentText("Sensor Structure saved in" + fileTxt.getText() + "/" + fileNameTxt.getText());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                   primaryStage.close();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                
                
            }
        });
        
         
        
        hostTxt.setPrefWidth(200);
        userTxt.setPrefWidth(200);
        pwdTxt.setPrefWidth(200);
        
       /* DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File("c:/dev/javafx");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(primaryStage);*/
        
        rootPane.addRow(0, dbHost, hostTxt);
        rootPane. addRow(1, dbUser, userTxt);
        rootPane.addRow(2, dbPwd, pwdTxt);
        rootPane.addRow(3, chooseBtn, fileTxt);
        rootPane.addRow(4, fileName, fileNameTxt);
        rootPane.addRow(5, startBtn);
       // rootPane.addRow(4, chooser.);
        
        rootPane.setHgap(10);//horizontal gap in pixels 
        rootPane.setVgap(10);//vertical gap in pixels
        rootPane.setPadding(new Insets(50, 20, 20, 20));
        
        root.setTop(rootPane);
       // root.getChildren().add(rootPane);
        
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setTitle("Offline Wiotech Structure Creator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
