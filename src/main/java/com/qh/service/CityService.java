package com.qh.service;

import com.qh.domain.City;

/**
 * 城市业务逻辑接口类
 *
 * Created by Administrator on 2017/9/18.
 */
public interface CityService {
    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Long id);

    /**
     * 新增城市信息
     *
     * @param city
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     */
    Long updateCity(City city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     */
    Long deleteCity(Long id);
}
