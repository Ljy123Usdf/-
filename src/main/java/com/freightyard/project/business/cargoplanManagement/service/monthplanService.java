package com.freightyard.project.business.cargoplanManagement.service;

import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.common.modal.MonthPlan;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface monthplanService {

    /**
     *  分页查询
     * @param pageNum
     * @param pageSize
     * @return PageInfo
     */
    PageInfo selectAllForPage(Integer pageNum, Integer pageSize);

    Integer updateMonthplan(MonthPlan monthPlan);

    Integer deleteMonthplan(Integer mpid);

    List<MonthPlan> selectmonthplanBycondition(monthPlanCondition monthPlanCondition);

    Integer insertManyplans(List<MonthPlan> monthPlanList, MultipartFile multipartFile) throws Exception;

    void downloadMonthplanTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
