package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;

import java.util.List;

public class ManageRoomFormController {
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtRoomTypeDescription;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomId;
    public JFXComboBox<RoomTypeDTO> cbRoomType;
    public TableView<RoomTM> tblRoom;
    public TableColumn<RoomTM,String> colRoomId;
    public TableColumn<RoomTM,String> colRoomType;
    public TableColumn<RoomTM,Double> colKeyMoney;
    public JFXComboBox cmbRoomTypeDescription;


    private RoomTypeService roomTypeService;
    private RoomService roomService;

    public void initialize(){
        roomService=new RoomServiceImpl();
        roomTypeService=new RoomTypeServiceImpl();
        setComboBox();
        visualizeComboBox();
        visualize();
        getDataToTable();
        String[] type = {"Ac-Food", "Non Ac - Food", "Ac - Non Food", "Non Ac - Non Food "};
        ObservableList<String> list = FXCollections.observableArrayList(type);
        cmbRoomTypeDescription.setItems(list);
    }

    public void setComboBox(){
        List<RoomTypeDTO> all = roomTypeService.getAll();
        cbRoomType.setItems(FXCollections.observableArrayList(all));
    }

    public void visualizeComboBox(){
        cbRoomType.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO object) {
                if(object==null)return null;
                return object.getDescription();
            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });
    }

    public void btnAddRoomTypeOnAction(ActionEvent actionEvent) {
        RoomTypeDTO roomTypeDTO = collectRoomTypeDto();
        RoomTypeDTO save = roomTypeService.save(roomTypeDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            setComboBox();
        }else {
            new Alert(Alert.AlertType.ERROR,"Data Adding Failed").show();
        }
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = collectRoomData();
        RoomDTO save = roomService.save(roomDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Room Added Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Details Adding Failed").show();
        }
    }

    public RoomTypeDTO collectRoomTypeDto(){
        String roomTypeId = txtRoomTypeId.getText();
        String description = cmbRoomTypeDescription.getSelectionModel().getSelectedItem().toString();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());

        return new RoomTypeDTO(roomTypeId,description,keyMoney,null);
    }

    public RoomDTO collectRoomData(){
        String id = txtRoomId.getText();
        String availability = "Available";
        RoomTypeDTO roomType = cbRoomType.getSelectionModel().getSelectedItem();

        return new RoomDTO(id,availability,roomType,null);
    }

    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
    }

    public void getDataToTable(){
        List<RoomTM> data = roomService.getRoomDataToTable();
        if(data==null)return;
        ObservableList<RoomTM> tblData = FXCollections.observableArrayList(data);
        tblRoom.setItems(tblData);
    }

    public void btnUpdateRoomsOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = collectRoomData();
        boolean update = roomService.update(roomDTO);
        if(update){
            new Alert(Alert.AlertType.INFORMATION,"Room Update Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Details Updating Failed").show();
        }
    }

    public void btnDeleteRoomsOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = collectRoomData();
        boolean delete = roomService.delete(roomDTO);
        if(delete){
            new Alert(Alert.AlertType.INFORMATION,"Room deleted Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Details deleting Failed").show();
        }
    }

    public void clearFiledsOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
        cbRoomType.setPromptText("Type");
    }

    public void btnUpdateRoomTypesOnAction(ActionEvent actionEvent) {
        RoomTypeDTO roomTypeDTO = collectRoomTypeDto();
        boolean update = roomTypeService.update(roomTypeDTO);
        if(update){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            setComboBox();
        }else {
            new Alert(Alert.AlertType.ERROR,"Data Adding Failed").show();
        }
    }

    public void btnDeleteRoomTypesOnAction(ActionEvent actionEvent) {
        RoomTypeDTO roomTypeDTO = collectRoomTypeDto();
        boolean delete = roomTypeService.delete(roomTypeDTO);
        if(delete){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            setComboBox();
        }else {
            new Alert(Alert.AlertType.ERROR,"Data Adding Failed").show();
        }
    }

    public void clearTypesOnAction(ActionEvent actionEvent) {
        txtRoomTypeId.clear();
        txtRoomTypeDescription.clear();
        txtKeyMoney.clear();
    }
}
