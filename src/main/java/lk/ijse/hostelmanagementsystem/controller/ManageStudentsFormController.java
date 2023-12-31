package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;

import java.io.IOException;
import java.util.List;

public class ManageStudentsFormController {
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public AnchorPane addStudentPane;
    public TableView<StudentTM> tblStudents;
    public TableColumn<StudentTM,String> colName;
    public TableColumn<StudentTM,String> colAddress;
    public TableColumn<StudentTM,String> colContact;
    public TableColumn<StudentTM,String> colCity;
    public TableColumn<StudentTM,String> colGmail;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtCity;
    public JFXTextField txtGmail;

    private final StudentService  studentService = new StudentServiceImpl();


    public void initialize() throws IOException {
        setCellValueFactory();
        getDataToTable();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StudentTM studentTM = collectDataTM();
        StudentDTO student = covertToDto(studentTM);
            boolean save = studentService.update(student);
            if(save){
                new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                getDataToTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
            }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        StudentTM studentTM = collectDataTM();
        StudentDTO student = covertToDto(studentTM);

            boolean save = studentService.delete(student);
            if(save){
                new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                getDataToTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
            }

    }

    private StudentDTO covertToDto(StudentTM student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getCity(),
                student.getContact(),student.getGmail(),null);
    }

    private StudentTM convertToTM(StudentDTO student){
        return new StudentTM(student.getId(),student.getName(),student.getAddress(),
                student.getContact(),student.getCity(),student.getGmail());
    }

    private StudentTM collectDataTM(){
        return new StudentTM(txtStudentId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),
                txtCity.getText(),txtGmail.getText());
    }

    private void getDataToTable(){
        List<StudentDTO> all = studentService.getAll();
        ObservableList<StudentTM> list = FXCollections.observableArrayList();
        for (StudentDTO ob:all){
            list.add(convertToTM(ob));
        }
        tblStudents.setItems(list);
    }

    public void setCellValueFactory(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
    }

    public void tblStudentOnClickAction(MouseEvent mouseEvent) {
        StudentTM student = tblStudents.getSelectionModel().getSelectedItem();
        if (student != null) setTextFields(covertToDto(student));
    }

    public void setTextFields(StudentDTO student){
        txtStudentId.setText(student.getId());
        txtName.setText(student.getName());
        txtContact.setText(student.getContact());
        txtAddress.setText(student.getAddress());
        txtCity.setText(student.getCity());
        txtGmail.setText(student.getGmail());
    }
    public void btnSaveStudentsOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = collectData();
        StudentDTO save = studentService.save(studentDTO);
        if(save!=null){
//            tblStudents.getItems().add(new StudentTM(studentDTO.getId(),studentDTO.getName(),studentDTO.getAddress(),
//                    studentDTO.getContact(),studentDTO.getCity(),studentDTO.getGmail()));
            getDataToTable();
            new Alert(Alert.AlertType.INFORMATION,"Student Added Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Student Adding Failed").show();
        }
    }

    public StudentDTO collectData(){
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String city = txtCity.getText();
        String gmail = txtGmail.getText();

        StudentDTO student = new StudentDTO(id,name,address,city,contact,gmail,
                null);
        return student;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtGmail.clear();
        txtContact.clear();
    }
}
