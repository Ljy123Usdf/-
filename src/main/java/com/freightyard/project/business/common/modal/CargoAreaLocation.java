package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoAreaLocation {
    private Integer calId;

    private String cargoName;

    private String category;

    private String cargoArea;

    private String cargoLocation;

    private Double weight;

    private Double volume;

    private String proportionCargo;

    private String proportionCargoLocation;

    private Double utilizationRatio;

    private String calComment;

}