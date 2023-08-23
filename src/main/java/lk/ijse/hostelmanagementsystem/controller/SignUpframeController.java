package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.UserServiceImpl;

import java.io.IOException;

public class SignUpframeController {
    public JFXTextField txtemployeeid;
    public JFXTextField txtusername;
    public JFXPasswordField tpspassword;
    public JFXPasswordField tpsrepassword;
    public JFXTextField txtpassword;
    public JFXTextField txtrepassword;
    public ImageView showPssword;
    public ImageView hidepassword;
    public JFXButton btncreate;
    public JFXTextField txthint;
    public Text txtlogin;
    public AnchorPane pane;
    private final UserService userService = new UserServiceImpl();

    public void initialize()  {
        hidepassword.setVisible(false);
        txtpassword.setVisible(false);
        txtrepassword.setVisible(false);
    }


    public void hidepasswordOnMouseClicked(MouseEvent mouseEvent) {
        hidepassword.setVisible(false);
        showPssword.setVisible(true);
        String text1 = txtpassword.getText();
        String text = txtrepassword.getText();
        txtpassword.setVisible(false);
        tpspassword.setVisible(true);
        tpspassword.setText(text1);
        txtrepassword.setVisible(false);
        tpsrepassword.setVisible(true);
        tpsrepassword.setText(text);

    }

    public void showpasswordOnMOuseClicekd(MouseEvent mouseEvent) {
        showPssword.setVisible(false);
        hidepassword.setVisible(true);
        String text = tpspassword.getText();
        String text1 = tpsrepassword.getText();
        tpspassword.setVisible(false);
        txtpassword.setVisible(true);
        txtpassword.setText(text);
        tpsrepassword.setVisible(false);
        txtrepassword.setVisible(true);
        if (text1.equals(null)) {
            txtrepassword.setPromptText("Re-enter password");
        }
        txtrepassword.setText(text1);
    }

    public void createacountOnAction(ActionEvent actionEvent) {
        if (tpspassword.getText().equals(tpsrepassword.getText())){
            UserDTO userDTO = makeObject();
            UserDTO save = userService.save(userDTO);
            if(save!=null){
                new Alert(Alert.AlertType.INFORMATION,"User Added Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"User Adding Failed").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Password is Not matching");
        }

    }

    private UserDTO makeObject() {
        String username = txtusername.getText();
        String password = tpspassword.getText();
        return new UserDTO(username,password);
    }

    public void loginFrameONMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/SignInFrame.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
}
