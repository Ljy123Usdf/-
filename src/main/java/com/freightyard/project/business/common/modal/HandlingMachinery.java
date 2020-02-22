package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HandlingMachinery {
    private Integer machineId;

    private String machineName;

    private Integer machineNumber;

    private Integer mayuseNumber;

    private Integer repairNumber;

    private String scopeApplication;

    private String hmComment;

}