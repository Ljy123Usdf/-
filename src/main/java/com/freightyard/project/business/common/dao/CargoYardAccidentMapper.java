package com.freightyard.project.business.common.dao;

import com.freightyard.project.business.common.modal.CargoYardAccident;
import com.freightyard.project.business.common.need.CommonMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoYardAccidentMapper extends CommonMapper<CargoYardAccident> {

}