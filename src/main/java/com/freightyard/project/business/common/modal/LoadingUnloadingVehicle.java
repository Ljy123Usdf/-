package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadingUnloadingVehicle {
    private Integer luvId;

    private String cargoTransportCode;

    private String stationTrackCar;

    private Integer loadUnloadNumber;

    private Date loadUnloadTime;

    private String loadUnloadPlace;

    private String luComment;

}