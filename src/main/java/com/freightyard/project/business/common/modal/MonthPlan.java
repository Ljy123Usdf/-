package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthPlan {

    private Integer mpId;

    private String cargoName;

    private Integer dayAvaerage;

    private Double netLoad;

    private Integer carNumber;

    private Double cargoTransportRate;

    private Double transmitWeight;

    private String endStation;

    private String beginStation;

    private Date date;

    private String carType;

    private String sourceCargo;

    private String mpComment;
}