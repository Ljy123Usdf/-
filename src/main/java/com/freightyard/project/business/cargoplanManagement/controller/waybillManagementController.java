package com.freightyard.project.business.cargoplanManagement.controller;

import com.freightyard.project.base.result.Result;
import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.cargoplanManagement.modal.waybillCondition;
import com.freightyard.project.business.cargoplanManagement.service.waybillService;
import com.freightyard.project.business.common.dao.CargoWaybillMapper;
import com.freightyard.project.business.common.modal.CargoWaybill;
import com.freightyard.project.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "运单接口")
@RestController
@RequestMapping("waybillManagementController")
public class waybillManagementController {
    @Autowired
    CargoWaybillMapper cargoWaybillMapper;

    @Autowired
    waybillService waybillService;

    @ApiOperation(value = "查询全部运单")
    @GetMapping("selectAllwaybill")
    public Result selectAllwaybill(){
        return Result.result(cargoWaybillMapper.selectAll());
    }

    @ApiOperation(value = "分页查询运单")
    @GetMapping("selectAllwaybillBypage")
    public Result selectAllwaybillBypage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return Result.result(waybillService.selectAllwaybillForpage(pageNum,pageSize));
    }

    @ApiOperation(value = "自定义分页查询运单")
    @PostMapping("selectmonthplanByObject")
    public Result selectmonthplanByObject(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize, @RequestBody waybillCondition waybillCondition){
        PageUtils pageUtils=new PageUtils();
        pageUtils.setDataList(waybillService.queryWaybillByCondition(waybillCondition));
        pageUtils.setCurrentPage(pageNum);
        pageUtils.setPageSizes(pageSize);
        return Result.result(pageUtils.paging());
    }

    @ApiOperation(value = "运单模板下载")
    @PostMapping("downloadwayBillTemplate")
    void downloadwayBillTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        waybillService.downloadwayBillTemplate(request,response);
    }

    @ApiOperation(value = "批量删除运单")
    @DeleteMapping("deleteManyWaybills")
    public Result deleteManyWaybills(@RequestParam("cwidList")List<Integer> cwidList){
        return Result.result(waybillService.deleteManywaybills(cwidList),"删除成功","删除失败");
    }

    @ApiOperation(value = "新增运单")
    @DeleteMapping("insertWaybill")
    public Result insertWaybill(@RequestParam("cargoWaybill") CargoWaybill cargoWaybill){
        return Result.result(cargoWaybillMapper.insert(cargoWaybill),"删除成功","删除失败");
    }
}
