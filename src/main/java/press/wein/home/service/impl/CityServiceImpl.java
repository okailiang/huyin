package press.wein.home.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.dao.CityMapper;
import press.wein.home.dao.ProvinceMapper;
import press.wein.home.enumerate.CityEnum;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.City;
import press.wein.home.model.Province;
import press.wein.home.model.vo.CityVo;
import press.wein.home.model.vo.ProvinceVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.CityService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.StringUtil;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * type: 0:省或特别行政区 1：直辖市 2：省会城市 3：市 4：区
 *
 * @author oukailiang
 * @create 2017-07-13 下午8:16
 */
@Service(value = "cityService")
public class CityServiceImpl extends BaseService implements CityService {

    public static final Logger LOG = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    private static List<ProvinceVo> provinceVoList;

    @Override
    public int updateCity(CityVo cityVo) throws ServiceException {
        checkParamNull(cityVo, cityVo.getId());
        City city = cityMapper.selectByPrimaryKey(cityVo.getId());
        if (city == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NOT_EXIST);
        }
        BeanUtil.beanCopier(cityVo, city);
        return cityMapper.updateByPrimaryKeySelective(city);
    }

    @Override
    public List<ProvinceVo> listAllProvince() throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(provinceVoList)) {
            List<Province> provinceList = provinceMapper.listAllProvince();

            provinceVoList = CollectionUtil.copyToDescList(provinceList, ProvinceVo.class);
        }
        return provinceVoList;
    }

    @Override
    public Map<Integer, ProvinceVo> getProvinceMap() throws ServiceException {
        List<ProvinceVo> provinceVoList = this.listAllProvince();
        return provinceVoList.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
    }

    @Override
    public ProvinceVo getProvince(Integer provinceId) throws ServiceException {
        return this.getProvinceMap().get(provinceId);
    }

    @Override
    public List<CityVo> listProvinces() throws ServiceException {
        List<City> cityList = cityMapper.listCitysByParentId(0);
        if (CollectionUtil.isNullOrEmpty(cityList)) {
            return Collections.emptyList();
        }
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    @Override
    public List<CityVo> listAllCitys() throws ServiceException {
        List<City> cityList = cityMapper.listAllCitys();
        if (CollectionUtil.isNullOrEmpty(cityList)) {
            return Collections.emptyList();
        }
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    @Override
    public Page<CityVo> listCitysWithPage(Page<CityVo> page, CityVo cityVo) throws ServiceException {
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(cityVo);
        //分页参数
        setPageParam(searchParam, page);

        List<CityVo> cityVoList = new ArrayList<>();
        int count = cityMapper.countCitys(searchParam);
        if (count <= 0) {
            return new Page<>(0, cityVoList);
        }
        List<City> cityList = cityMapper.listCitysWithPage(searchParam);
        if (CollectionUtil.isNotEmpty(cityList)) {
            cityVoList = CollectionUtil.copyToDescList(cityList, CityVo.class);
        }
        return new Page<>(count, cityVoList);
    }

    @Override
    public List<CityVo> listCitysByIds(List<Integer> ids) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(ids)) {
            return Collections.emptyList();
        }
        List<City> cityList = cityMapper.listCitysByIds(ids);
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    @Override
    public List<CityVo> listCitys(CityVo cityVo) throws ServiceException {
        this.checkParamNull(cityVo);
        City city = new City();
        BeanUtil.beanCopier(cityVo, city);
        List<City> cityList = cityMapper.listCitys(city);
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    /**
     * 通过名称和对应的拼音查询
     *
     * @param cityVo
     * @return
     * @throws ServiceException
     */
    @Override
    public List<CityVo> listCitysByName(CityVo cityVo) throws ServiceException {
        this.checkParamNull(cityVo);
        if (StringUtil.isBlank(cityVo.getName()) && StringUtil.isBlank(cityVo.getPinyin())) {
            return Collections.emptyList();
        }
        City city = new City();
        BeanUtil.beanCopier(cityVo, city);
        List<City> cityList = cityMapper.listCitysByName(city);
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    @Override
    public List<CityVo> listCitysByParentId(Integer parentId) throws ServiceException {
        if (parentId == null) {
            return Collections.emptyList();
        }
        List<City> cityList = cityMapper.listCitysByParentId(parentId);
        return CollectionUtil.copyToDescList(cityList, CityVo.class);
    }

    @Override
    public CityVo getCityById(Integer cityId) throws ServiceException {
        checkParamNull(cityId);

        City city = cityMapper.selectByPrimaryKey(cityId);
        if (city == null) {
            return null;
        }
        CityVo cityVo = new CityVo();
        BeanUtil.beanCopier(city, cityVo);

        this.setProvinceCityArea(cityVo);
        if (StringUtil.isBlank(cityVo.getFullName())) {
            cityVo.setFullName(cityVo.getName());
        }
        return cityVo;
    }

    @Override
    public Map<Integer, CityVo> getCityMap() throws ServiceException {
        List<CityVo> cityVoList = this.listAllCitys();
        return cityVoList.stream().collect(Collectors.toMap(c -> c.getId(), c -> c));
    }

    @Override
    public List<CityVo> listCityAreasByProvinceId(Integer provinceId) throws ServiceException {
        if (provinceId == null) {
            return Collections.emptyList();
        }
        City province = cityMapper.selectByPrimaryKey(provinceId);
        if (province.getParentId() != 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NO_PROVINCE_ID);
        }

        List<City> resultCityList = new ArrayList<>();
        List<City> cityList = cityMapper.listCitysByParentId(provinceId);
        resultCityList.addAll(cityList);
        //循环获得city下面的区
        for (City city : cityList) {
            resultCityList.addAll(cityMapper.listCitysByParentId(city.getId()));
        }
        return CollectionUtil.copyToDescList(resultCityList, CityVo.class);
    }

    public Map<String, List<CityVo>> listProvinceCityAreas(Integer areaId) throws ServiceException {
        Map<String, List<CityVo>> pCityAreaMap = new HashMap<>();
        City area = cityMapper.selectByPrimaryKey(areaId);
        if (area == null || CityEnum.Type.FOUR.getValue().intValue() != area.getType()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NO_AREA_ID);
        }
        City city = cityMapper.selectByPrimaryKey(area.getParentId());
        pCityAreaMap.put("provinces", this.listProvinces());
        pCityAreaMap.put("citys", this.listCitysByParentId(city.getParentId()));
        pCityAreaMap.put("areas", this.listCitysByParentId(city.getId()));
        return pCityAreaMap;
    }

    private void setProvinceCityArea(CityVo cityVo) {
        LOG.info("setProvinceCityArea CityVo = [{}]", JSON.toJSONString(cityVo));
        City city;
        StringBuilder sb = new StringBuilder();
        if (cityVo.getType().intValue() == CityEnum.Type.FOUR.getValue()) {
            cityVo.setAreaName(StringUtil.isBlank(cityVo.getFullName()) ? cityVo.getName() : cityVo.getFullName());
            city = cityMapper.selectByPrimaryKey(cityVo.getParentId());
            if (city == null) {
                return;
            }
            cityVo.setCityName(city.getFullName());
            sb.append(cityVo.getCityName()).append("-").append(cityVo.getAreaName());

            city = cityMapper.selectByPrimaryKey(city.getParentId());
            if (city == null) {
                return;
            }
            cityVo.setProvinceName(city.getFullName());
            sb.insert(0, "-").insert(0, cityVo.getProvinceName());
            cityVo.setProvinceCityArea(sb.toString());
        }
        if (cityVo.getType().intValue() == CityEnum.Type.THREE.getValue()
                || cityVo.getType().intValue() == CityEnum.Type.TWO.getValue()
                || cityVo.getType().intValue() == CityEnum.Type.ONE.getValue()) {
            cityVo.setCityName(cityVo.getFullName());
            city = cityMapper.selectByPrimaryKey(cityVo.getParentId());
            if (city != null) {
                cityVo.setProvinceName(city.getFullName());
                sb.append(cityVo.getProvinceName()).append("-").append(cityVo.getCityName());
            }
            cityVo.setProvinceCityArea(sb.toString());
        }
        if (cityVo.getType().intValue() == CityEnum.Type.ZERO.getValue()) {
            cityVo.setProvinceName(cityVo.getFullName());
            cityVo.setProvinceCityArea(cityVo.getFullName());
        }
    }
}
