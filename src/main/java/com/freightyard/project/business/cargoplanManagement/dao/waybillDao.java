package com.freightyard.project.business.cargoplanManagement.dao;

import com.freightyard.project.business.cargoplanManagement.modal.waybillCondition;
import com.freightyard.project.business.common.modal.CargoWaybill;
import com.freightyard.project.business.common.modal.MonthPlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface waybillDao {
    @Select("<script>" +
            "select * from cargo_waybill" +
            "<where>" +
            "<if test=\"waybillCondition.cargoTransportCode neq 0 and waybillCondition.cargoTransportCode neq null\">" +
            "cargo_transport_code=#{waybillCondition.cargoTransportCode}" +
            "<if test=\"waybillCondition.receivingParty neq '' and waybillCondition.receivingParty neq null\">" +
            "and receiving_party=#{waybillCondition.receivingParty}" +
            "<if test=\"waybillCondition.receivingAddress neq '' and waybillCondition.receivingAddress neq null\">" +
            "and receiving_address=#{waybillCondition.receivingAddress}" +
            "<if test=\"waybillCondition.cargoName neq '' and waybillCondition.cargoName neq null\">" +
            "and cargo_name=#{waybillCondition.cargoName}" +
            "<if test=\"waybillCondition.beginStation neq '' and waybillCondition.beginStation neq null\">" +
            "and begin_station=#{waybillCondition.beginStation}" +
            "<if test=\"waybillCondition.endStation neq '' and waybillCondition.endStation neq null\">" +
            "and end_station=#{waybillCondition.endStation}" +
            "</where>" +
            "</script>")
    List<CargoWaybill> queryWaybillByCondition(@Param("waybillCondition")waybillCondition waybillCondition);

    @Insert("<script>" +
            "<foreach collection=\"cargoWaybillList\" item=\"item\" separator=\";\">" +
            "insert into cargo_waybill values(default,#{item.cargoTransportCode}, #{item.receivingParty}" +
            ",#{item.receivingPhone},#{item.receivingAddress},#{item.cargoName},#{item.cargoNum}" +
            ",#{item.weight},#{item.volume},#{item.freight},#{item.beginStation}" +
            ",#{item.endStation},#{item.receivingTime}" +
            ",#{item.cwComment},#{item.creatTime}" +
            ",#{item.beginTime})" +
            "</foreach>" +
            "</script>")
    Integer insertManywaybills(@Param("monthplanList")List<CargoWaybill> cargoWaybillList);

    @Delete("<script>" +
            "delete from cargo_waybill where cw_id in (" +
            "<foreach collection=\"cwidList\" item=\"item\" separator=\",\">" +
            " #{item}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer deleteManywaybills(@Param("cwidList") List<Integer> cwidList);
}
