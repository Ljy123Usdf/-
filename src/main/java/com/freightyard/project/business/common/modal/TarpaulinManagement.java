package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarpaulinManagement {
    private Integer tarpaulinId;

    private String tarpaulinType;

    private Integer tarpaulinNumber;

    private Integer mayuseNumber;

    private Integer repairNumber;

    private String scopeApplication;

    private String tmComment;
}