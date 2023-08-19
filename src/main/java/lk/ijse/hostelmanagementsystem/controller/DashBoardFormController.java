package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public JFXButton btnHome;
    public JFXButton btnManageStudents;
    public JFXButton btnManageRooms;
    public JFXButton btnReserveRooms;
    public JFXButton btnDetails;
    public JFXButton btnRemaining;
    public AnchorPane dashBoardContext;
    public JFXButton btnLogout;
    public AnchorPane dashBoardMainContext;

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        ((Stage)dashBoardMainContext.getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
    }

    public void btnRemainingOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RemainingKeyMoneyForm.fxml")));
    }

    public void btnReserveRoomsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReserveRoomsForm.fxml")));
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageRoomForm.fxml")));
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageStudentsForm.fxml")));
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml")));
    }

    public void settingOnMouseClicked(MouseEvent mouseEvent) {
    }
}
