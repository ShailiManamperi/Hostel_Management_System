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
//        Timeline timeline = new Timeline();
//
//        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), actionEvent -> {
//            lblLoading.setText("Initilizing Application.. ");
//            recLoad.setWidth(recbar.getWidth()*0.3);
//        });
//        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(1000),actionEvent -> {
//            lblLoading.setText("Loading Internal Resources.. ");
//            recLoad.setWidth(recbar.getWidth()*0.4);
//        });
//        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1500),actionEvent -> {
//            lblLoading.setText("Loading Database..");
//            recLoad.setWidth(recbar.getWidth()*0.5);
//        });
//        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(2000),actionEvent -> {
//            lblLoading.setText("Loading System Ui.. ");
//            recLoad.setWidth(recbar.getWidth()*0.6);
//        });
//        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(2500),actionEvent -> {
//            lblLoading.setText("Loading Images.. ");
//            recLoad.setWidth(recbar.getWidth()*0.7);
//        });
//        KeyFrame keyFrame5 = new KeyFrame(Duration.millis(3000),actionEvent -> {
//            lblLoading.setText("Getting Started.. ");
//            recLoad.setWidth(recbar.getWidth()*0.9);
//        });
//        KeyFrame keyFrame6 = new KeyFrame(Duration.millis(3500),actionEvent -> {
//            lblLoading.setText("Welcome to System.");
//            recLoad.setWidth(recbar.getWidth());
//        });
//
//        KeyFrame keyFrame7 = new KeyFrame(Duration.millis(4000),actionEvent -> {
//            try {
//                URL resource = this.getClass().getResource("/view/Loginformframe.fxml");
//                Stage stage = new Stage();
//                AnchorPane container = FXMLLoader.load(resource);
//                AnchorPane pane =(AnchorPane) container.lookup("#supane");
//                pane.getChildren().clear();
//                URL resource1 = this.getClass().getResource("/view/SignInFrame.fxml");
//                AnchorPane welcome = FXMLLoader.load(resource1);
//                pane.getChildren().add(welcome);
//                stage.setScene(new Scene(container));
//                stage.centerOnScreen();
//                Stage window = (Stage) recbar.getScene().getWindow();
//                window.hide();
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        timeline.getKeyFrames().addAll(keyFrame,keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6,keyFrame7);
//        timeline.play();
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
