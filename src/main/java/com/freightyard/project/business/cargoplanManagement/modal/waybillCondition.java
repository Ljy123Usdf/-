package com.freightyard.project.business.cargoplanManagement.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class waybillCondition {

    private Integer cargoTransportCode;

    private String receivingParty;

    private String receivingAddress;

    private String cargoName;

    private String beginStation;

    private String endStation;

}
