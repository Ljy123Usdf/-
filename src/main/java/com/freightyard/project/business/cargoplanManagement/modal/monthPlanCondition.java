package com.freightyard.project.business.cargoplanManagement.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class monthPlanCondition {
    //月计划主键
   private Integer mpId;
    //货物名称
   private String cargoName;
    //终到站
   private String endStation;
    //始发站
   private String beginStation;
    //日期
   private Date date;
    //货源
   private String sourceCargo;
}
