package com.freightyard.project.business.cargoplanManagement.controller;

import com.freightyard.project.base.result.Result;
import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.cargoplanManagement.service.monthplanService;
import com.freightyard.project.business.common.dao.CargoWaybillMapper;
import com.freightyard.project.business.common.dao.MonthPlanMapper;
import com.freightyard.project.business.common.modal.MonthPlan;
import com.freightyard.project.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags="月计划接口")
@RestController
@RequestMapping("cargoplanManagementController")
public class cargoplanManagementController {
    @Autowired
    MonthPlanMapper monthPlanMapper;

    @Autowired
    monthplanService monthplanService;

    //月计划查询
    @ApiOperation(value = "查询全部月计划")
    @GetMapping("selectMonthplanAll")
    public Result monthplanList(){
        return Result.result(monthPlanMapper.selectAll());
    }

    //月计划分页查询
    @ApiOperation(value = "分页查询月计划")
    @GetMapping("selectMonthplanBypage")
    public Result monthplanListBypage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return Result.result(monthplanService.selectAllForPage(pageNum,pageSize));
    }

    //根据主键修改月计划
    @ApiOperation(value = "修改月计划")
    @PutMapping("updateMonthplanById")
    public Result updateMonthplan(@RequestBody MonthPlan monthPlan){
        return Result.result(monthplanService.updateMonthplan(monthPlan),"修改成功","修改失败");
    }

    //根据主键删除
    @ApiOperation(value = "删除月计划")
    @DeleteMapping("deletemonthplanById/{mpid}")
    public Result deletemonthplan(@PathVariable Integer mpid){
        return Result.result(monthplanService.deleteMonthplan(mpid),"删除成功","删除失败");
    }

    //月计划自定义查询
    @ApiOperation(value = "自定义分页查询月计划")
    @PostMapping("selectmonthplanByObject")
    public Result selectmonthplanByObject(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize, @RequestBody monthPlanCondition monthPlanCondition){
        PageUtils pageUtils=new PageUtils();
        pageUtils.setDataList(monthplanService.selectmonthplanBycondition(monthPlanCondition));
        pageUtils.setCurrentPage(pageNum);
        pageUtils.setPageSizes(pageSize);
        return Result.result(pageUtils.paging());
    }

    @ApiOperation(value = "学生信息模板下载")
    @PostMapping("/downloadStudentTemplate")
    public void downloadmonthplanTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        monthplanService.downloadMonthplanTemplate(request,response);
    }
}
