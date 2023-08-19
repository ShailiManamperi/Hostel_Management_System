package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class SignInFrameController {
    public JFXPasswordField tpspassword;
    public ImageView showpassword;
    public ImageView showpassword1;
    public JFXTextField txtpassword;
    public AnchorPane pane;
    public JFXButton btnlogin;
    public JFXTextField txtusername;

    public void initialize()  {
        showpassword1.setVisible(false);
        txtpassword.setVisible(false);
    }

    public void forgetpasswordframeOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void MainDashBoradOnAction(ActionEvent actionEvent) throws IOException {
       if (actionEvent.getSource() == btnlogin) {
            String username = txtusername.getText();
            String password = txtpassword.getText();
            if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
                System.out.println("login success");
                Stage window = (Stage) pane.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
                window.centerOnScreen();
            } else if
            (txtusername.getText().isEmpty() && txtpassword.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING,"Please enter your data.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING,"Wrong username or password!", ButtonType.OK).show();
            }
        }
    }

    public void signinFrameONMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/SignUpframe.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    public void ShowPasswordOnMouseClicked(MouseEvent mouseEvent) {
        showpassword.setVisible(false);
        showpassword1.setVisible(true);
        String text = tpspassword.getText();
        tpspassword.setVisible(false);
        txtpassword.setVisible(true);
        txtpassword.setText(text);
    }

    public void hodePasswordOnMpouseClicked(MouseEvent mouseEvent) {
        showpassword1.setVisible(false);
        showpassword.setVisible(true);
        String text = txtpassword.getText();
        txtpassword.setVisible(false);
        tpspassword.setVisible(true);
        tpspassword.setText(text);
    }
}
