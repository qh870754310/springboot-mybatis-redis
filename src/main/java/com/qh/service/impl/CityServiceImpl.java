package com.qh.service.impl;

import com.qh.dao.CityDao;
import com.qh.domain.City;
import com.qh.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 城市业务逻辑实现类
 *
 * Created by Administrator on 2017/9/18.
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;
    /**
     * RedisTemplate 封装了 RedisConnection，具有连接管理，序列化和 Redis 操作等功能。还有针对 String 的支持对象 StringRedisTemplate。
     * Redis 操作视图接口类用的是 ValueOperations，对应的是 Redis String/Value 操作。还有其他的操作视图，
     * ListOperations、SetOperations、ZSetOperations 和 HashOperations 。
     * ValueOperations 插入缓存是可以设置失效时间，这里设置的失效时间是 10 s。
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    @Override
    public City findCityById(Long id) {
        //从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        //缓存存在
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);
            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }
        //从 DB 中获取城市信息
        City city = cityDao.findById(id);
        //插入缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
        return city;
    }


    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);
        //缓存存在，删除缓存
        String key = "city_" + city.getId();
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }
        return ret;
    }

    @Override
    public Long deleteCity(Long id) {
        Long ret = cityDao.deleteCity(id);
        //缓存存在，删除缓存
        String key = "city_" + id;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }
}