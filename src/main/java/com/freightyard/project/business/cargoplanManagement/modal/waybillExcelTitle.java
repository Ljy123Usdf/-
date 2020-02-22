package com.freightyard.project.business.cargoplanManagement.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("titles.waybill")
public class waybillExcelTitle {

    //运单模板
    private List<String> excelTitle = new ArrayList<>();
}
