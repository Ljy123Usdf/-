package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoYardAccident {
    private Integer cyaId;

    private Integer accidentCode;

    private Integer accidentLevel;

    private String accidentType;

    private Double damagesAmount;

    private Double compensationAmount;

    private String accidentCause;

    private String cyaComment;

}