package com.freightyard.project.business.cargoplanManagement.dao;

import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.common.modal.MonthPlan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthplanDao {

    @Update("UPDATE month_plan SET car_number=#{monthplan.carNumber},day_avaerage=#{monthplan.dayAvaerage}," +
            "cargo_name =#{monthplan.cargoName},net_load=#{monthplan.netLoad},\n" +
            "cargo_transport_rate =#{monthplan.cargoTransportRate}," +
            "transmit_weight=#{monthplan.transmitWeight}, begin_station=#{monthplan.beginStation},\n" +
            "end_station =#{monthplan.endStation},date=#{monthplan.date},car_type=#{monthplan.carType},\n" +
            "mp_comment=#{monthplan.mpComment}," +
            "source_cargo=#{monthplan.sourceCargo}\n" +
            "where mp_id=#{monthplan.mpId} ")
    Integer updatemonthplan(@Param("monthplan")MonthPlan monthPlan);

    @Delete("delete from month_plan where mp_id=#{mpid}")
    Integer deletemonthplan(@Param("mpid") Integer mpid);

    @Select("<script>" +
            "select * from month_plan " +
            "<where>" +
            "<if test = \"monthPlanCondition.mpId neq null and monthPlanCondition.mpId neq 0\">" +
            "mp_id=#{monthPlanCondition.mpId}" +
            "</if>" +
            "<if test = \"monthPlanCondition.cargoName neq null and monthPlanCondition.cargoName neq ''\">" +
            "and cargo_name like '%${monthPlanCondition.cargoName}%'" +
            "</if>" +
            "<if test = \"monthPlanCondition.endStation neq null and monthPlanCondition.endStation neq ''\">" +
            "and end_station like '%${monthPlanCondition.endStation}%'" +
            "</if>" +
            "<if test = \"monthPlanCondition.beginStation neq null and monthPlanCondition.beginStation neq ''\">" +
            "and begin_station like '%${monthPlanCondition.beginStation}%'" +
            "</if>" +
            "<if test = \"monthPlanCondition.date neq null and monthPlanCondition.date neq ''\">" +
            "and date=#{monthPlanCondition.date}" +
            "</if>" +
            "<if test = \"monthPlanCondition.sourceCargo neq null and monthPlanCondition.sourceCargo neq ''\">" +
            "and source_cargo like '%${monthPlanCondition.sourceCargo}%'" +
            "</if>" +
            "</where>" +
            "</script>")
    List<MonthPlan> selectMonthplanByObject(@Param("monthPlanCondition")monthPlanCondition monthPlanCondition);

    @Insert("<script>" +
            "<foreach collection=\"monthplanList\" item=\"item\" separator=\";\">" +
            "insert into month_plan values(default,#{item.cargoName}, #{item.dayAvaerage}" +
            ",#{item.netLoad},#{item.carNumber},#{item.cargoTransportRate},#{item.transmitWeight})" +
            ",#{item.endStation},#{item.beginStation},#{item.date},#{item.carType}" +
            ",#{item.sourceCargo},#{item.mpComment}" +
            "</foreach>" +
            "</script>")
    Integer insertManyplans(@Param("monthplanList")List<MonthPlan> monthplanList);
}
