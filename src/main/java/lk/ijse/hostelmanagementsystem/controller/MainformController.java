package lk.ijse.hostelmanagementsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostelmanagementsystem.util.task.ApplicationStartTask;

import java.io.IOException;
import java.net.URL;

public class MainformController {
    public AnchorPane pane;
    public Label lblmain;
    public Label lblmain2;
    public Label lblcp;
    public Rectangle recbar;
    public Rectangle recLoad;
    public Label lblLoading;

    public void initialize(){
        animation();
    }

    public void animation() {
        ApplicationStartTask task = new ApplicationStartTask();
        task.progressProperty().addListener((a,b,c)->{
            recLoad.setWidth(recbar.getWidth()*c.doubleValue());
            //lblPercentage.setText((c.doubleValue()*100)+"%");
        });

        task.valueProperty().addListener((a,b,c)->{
            if(c.equals("Starting Application...")){
                try {
                URL resource = this.getClass().getResource("/view/Loginformframe.fxml");
                Stage stage = new Stage();
                AnchorPane container = FXMLLoader.load(resource);
                AnchorPane pane =(AnchorPane) container.lookup("#supane");
                pane.getChildren().clear();
                URL resource1 = this.getClass().getResource("/view/SignInFrame.fxml");
                AnchorPane welcome = FXMLLoader.load(resource1);
                pane.getChildren().add(welcome);
                stage.setScene(new Scene(container));
                stage.centerOnScreen();
                Stage window = (Stage) recbar.getScene().getWindow();
                window.hide();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            lblLoading.setText(c);

        });

        Thread t1 = new Thread(task);
        t1.start();
    }
}
