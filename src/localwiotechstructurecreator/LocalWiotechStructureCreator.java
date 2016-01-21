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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        
        Label dbHost = new Label("Local Manager IP:");
        TextField hostTxt = new TextField("localhost");
        
        Label dbUser = new Label("Loacal Manager Database User:");
        TextField userTxt = new TextField();
        
        Label dbPwd = new Label("Loacal Manager Database Passwort:");
        TextField pwdTxt = new TextField();
        
        
        Label choose = new Label("Wiotech Config File Name:");
        TextField fileTxt = new TextField();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Wiotech Config File(*.config)", "*.config");
        fileChooser.getExtensionFilters().add(extFilter);
     
        
        Button chooseBtn = new Button("Choose");
        chooseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
                
                File selectedFile = fileChooser.showSaveDialog(primaryStage);
                
                if (selectedFile != null) {
                    fileTxt.setText(selectedFile.getPath());
                } else {
                    fileTxt.setText("File selection cancelled.");
                }
            }
        });
        

        Button startBtn = new Button("Create Wiotech Config File");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            
                

                

        
                WiotechStructureCreator wsc = new WiotechStructureCreator(hostTxt.getText(), 3306, "db_lm_cbv2", 
                        userTxt.getText(), pwdTxt.getText(), (fileTxt.getText()));
                    
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Structure Creator");
                alert.setHeaderText(null);
                alert.setContentText("Sensor Structure saved in" + fileTxt.getText());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                   primaryStage.close();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
  
                
            }
        });
        
         
        
        /*hostTxt.setPrefWidth(200);
        userTxt.setPrefWidth(200);
        pwdTxt.setPrefWidth(200);*/
        
        
        int i = 0;
        rootPane.addRow(i++, dbHost, hostTxt);
        rootPane. addRow(i++, dbUser, userTxt);
        rootPane.addRow(i++, dbPwd, pwdTxt);
        rootPane.addRow(i++, choose,fileTxt,chooseBtn );
        rootPane.addRow(i++, startBtn);
       // rootPane.addRow(4, chooser.);
        
        rootPane.setHgap(10);//horizontal gap in pixels 
        rootPane.setVgap(10);//vertical gap in pixels
        rootPane.setPadding(new Insets(50, 20, 20, 20));
        
        root.setTop(rootPane);
       // root.getChildren().add(rootPane);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Offline Wiotech Config File Creator");
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
