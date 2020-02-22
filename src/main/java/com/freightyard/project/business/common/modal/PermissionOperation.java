package com.freightyard.project.business.common.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionOperation {
    private Integer poId;

    private String userName;

    private String password;

    private Integer userCode;

    private String poComment;

    private Integer userType;

}