package com.freightyard.project.business.cargoplanManagement.service;

import com.freightyard.project.business.cargoplanManagement.modal.waybillCondition;
import com.freightyard.project.business.common.modal.CargoWaybill;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface waybillService {

    PageInfo selectAllwaybillForpage(Integer pageNum, Integer pageSize);

    List<CargoWaybill> queryWaybillByCondition(waybillCondition waybillCondition);

    Integer deleteManywaybills(List<Integer> cwidList);

    void downloadwayBillTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception;

    Integer insertManyWayBill(MultipartFile multipartFile) throws Exception;
}
