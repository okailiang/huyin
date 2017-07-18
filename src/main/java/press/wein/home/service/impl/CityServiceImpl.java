package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.dao.CityMapper;
import press.wein.home.dao.ProvinceMapper;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
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
        CityVo cityVo = new CityVo();
        BeanUtil.beanCopier(city, cityVo);
        return cityVo;
    }

    @Override
    public Map<Integer, CityVo> getCityMap() throws ServiceException {
        List<CityVo> cityVoList = this.listAllCitys();
        return cityVoList.stream().collect(Collectors.toMap(c -> c.getId(), c -> c));
    }

    @Override
    public List<CityVo> listCitysByProvinceId(Integer provinceId) throws ServiceException {
        if (provinceId == null) {
            return Collections.emptyList();
        }

        List<City> resultCityList = new ArrayList<>();
        List<City> cityList = cityMapper.listCitysByParentId(provinceId);
        for (City city : cityList) {
            
        }

        return null;
    }
}
