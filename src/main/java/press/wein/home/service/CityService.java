package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.City;
import press.wein.home.model.Province;
import press.wein.home.model.vo.CityVo;
import press.wein.home.model.vo.ProvinceVo;

import java.util.List;
import java.util.Map;

/**
 * 城市管理service
 *
 * @author oukailiang
 * @create 2017-07-13 下午8:16
 */

public interface CityService {

    List<ProvinceVo> listAllProvince() throws ServiceException;

    Map<Integer, ProvinceVo> getProvinceMap() throws ServiceException;

    ProvinceVo getProvince(Integer provinceId) throws ServiceException;

    List<CityVo> listAllCitys() throws ServiceException;

    Page<CityVo> listCitysWithPage(Page<CityVo> page,CityVo cityVo) throws ServiceException;

    List<CityVo> listCitysByIds(List<Integer> ids) throws ServiceException;

    List<CityVo> listCitys(CityVo cityVo) throws ServiceException;

    /**
     * 通过名称和对应的拼音查询
     * @param cityVo
     * @return
     * @throws ServiceException
     */
    List<CityVo> listCitysByName(CityVo cityVo) throws ServiceException;

    List<CityVo> listCitysByParentId(Integer parentId) throws ServiceException;

    CityVo getCityById(Integer cityId) throws ServiceException;

    Map<Integer, CityVo> getCityMap() throws ServiceException;

    List<CityVo> listCitysByProvinceId(Integer provinceId) throws ServiceException;
}
