package com.freightyard.project.business.cargoplanManagement.controller;

import com.freightyard.project.base.result.Result;
import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.cargoplanManagement.service.monthplanService;
import com.freightyard.project.business.common.dao.MonthPlanMapper;
import com.freightyard.project.business.common.modal.MonthPlan;
import com.freightyard.project.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "查询全部月计划")
    @GetMapping("selectMonthplanAll")
    public Result monthplanList(){
        return Result.result(monthPlanMapper.selectAll());
    }

    @ApiOperation(value = "分页查询月计划")
    @GetMapping("selectMonthplanBypage")
    public Result monthplanListBypage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return Result.result(monthplanService.selectAllForPage(pageNum,pageSize));
    }

    @ApiOperation(value = "修改月计划")
    @PutMapping("updateMonthplanById")
    public Result updateMonthplan(@RequestBody MonthPlan monthPlan){
        return Result.result(monthplanService.updateMonthplan(monthPlan),"修改成功","修改失败");
    }

    @ApiOperation(value = "删除月计划")
    @DeleteMapping("deletemonthplanById/{mpid}")
    public Result deletemonthplan(@PathVariable Integer mpid){
        return Result.result(monthplanService.deleteMonthplan(mpid),"删除成功","删除失败");
    }

    @ApiOperation(value = "自定义分页查询月计划")
    @PostMapping("selectmonthplanByObject")
    public Result selectmonthplanByObject(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize, @RequestBody monthPlanCondition monthPlanCondition){
        PageUtils pageUtils=new PageUtils();
        pageUtils.setDataList(monthplanService.selectmonthplanBycondition(monthPlanCondition));
        pageUtils.setCurrentPage(pageNum);
        pageUtils.setPageSizes(pageSize);
        return Result.result(pageUtils.paging());
    }

    @ApiOperation(value = "月计划信息模板下载")
    @PostMapping("downloadmonthplanTemplate")
    public void downloadmonthplanTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        monthplanService.downloadMonthplanTemplate(request,response);
    }

    @ApiOperation(value = "月计划信息批量导入")
    @PostMapping("insertMonthplanMany")
    public Result insertManyplans(MultipartFile multipartFile) throws Exception {
       return Result.result(monthplanService.insertManyplans(multipartFile),"上传成功","上传失败");
    }

    @ApiOperation(value = "月计划信息单次导入")
    @PostMapping("insertOneMonthPlan")
    public Result insertonemonthplan(@RequestBody MonthPlan monthPlan) throws Exception {
        return Result.result(monthPlanMapper.insert(monthPlan),"新增成功","新增失败");
    }

    @ApiOperation(value = "月计划信息批量删除")
    @DeleteMapping("deleteMonthPlanMany")
    public Result deletemonthplanMany(@RequestParam List<Integer> mpidList) throws Exception {
        return Result.result(monthplanService.deleteMany(mpidList),"删除成功","删除失败");
    }
}
