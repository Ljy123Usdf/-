package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoWaybill {
    private Integer cwId;

    private Integer cargoTransportCode;

    private String receivingParty;

    private Integer receivingPhone;

    private String receivingAddress;

    private String cargoName;

    private Integer cargoNum;

    private Double weight;

    private Double volume;

    private Double freight;

    private String beginStation;

    private String endStation;

    private Date receivingTime;

    private String cwComment;

    private Date creatTime;

    private Date beginTime;

}