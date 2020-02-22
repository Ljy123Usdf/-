package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CargoYardDriveout {
    private Integer cydId;

    private Integer cargoTransportCode;

    private Date drivingTime;

    private String drivingContents;

    private Integer drivingNumber;

    private String cydComment;

    public Integer getCydId() {
        return cydId;
    }

}