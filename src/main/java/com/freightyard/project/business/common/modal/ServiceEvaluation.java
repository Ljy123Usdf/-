package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceEvaluation {
    private Integer seId;

    private String cargoTransportCode;

    private Double transportationSpeed;

    private Integer damageSituation;

    private String cargoRateLevel;

    private String explain;

    private String seComment;
}