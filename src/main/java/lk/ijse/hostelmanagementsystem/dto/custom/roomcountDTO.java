package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class roomcountDTO implements SuperEntity {
    private int count;
    private String type;
}
