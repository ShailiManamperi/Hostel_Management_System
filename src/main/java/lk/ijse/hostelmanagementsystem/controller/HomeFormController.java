package lk.ijse.hostelmanagementsystem.controller;

import javafx.scene.control.Label;
import lk.ijse.hostelmanagementsystem.dto.custom.roomcountDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;

import java.util.HashMap;
import java.util.List;

public class HomeFormController {
    public Label lbl1;
    public Label lbl2;
    public Label lbl3;
    public Label lbl4;

    private RoomService roomService;
    
    public void initialize(){
        roomService = new RoomServiceImpl();
        setRoomCount();
    }

    private void setRoomCount() {
        List<roomcountDTO> roomCount =roomService.getRoomCount();
        roomcountDTO set1 = roomCount.get(0);
        roomcountDTO set2 = roomCount.get(1);
        roomcountDTO set3 = roomCount.get(2);
        roomcountDTO set4 = roomCount.get(3);


        if (set1.getCount()==0){
            lbl1.setText("00");
        }else {
            lbl1.setText(String.valueOf(set1.getCount()));
        }
        if (set2.getCount()==0){
            lbl2.setText("00");
        }else {
            lbl2.setText(String.valueOf(set2.getCount()));
        }
        if (set3.getCount()==0){
            lbl3.setText("00");
        }else {
            lbl3.setText(String.valueOf(set3.getCount()));
        }
        if (set4.getCount()==0){
            lbl4.setText("00");
        }else {
            lbl4.setText(String.valueOf(set4.getCount()));
        }
//      
//        lbl1.setText(String.valueOf(roomCount.get("RT-001")));
//        lbl2.setText(String.valueOf(roomCount.get("RT-002")));
//        lbl3.setText(String.valueOf(roomCount.get("RT-003")));
//        lbl4.setText(String.valueOf(roomCount.get("RT-004")));
    }
}
