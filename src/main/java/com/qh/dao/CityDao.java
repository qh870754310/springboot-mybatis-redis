package com.qh.dao;

import com.qh.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市 DAO 接口类
 * Created by Administrator on 2017/9/18.
 */
@Mapper
public interface CityDao {
    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();
    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City findById(@Param("id") Long id);

    /**
     * 保存城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateCity(City city);

    /**
     * 删除城市信息
     *
     * @param id
     * @return
     */
    Long deleteCity(Long id);
}
