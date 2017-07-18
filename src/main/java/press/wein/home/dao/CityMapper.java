package press.wein.home.dao;

import press.wein.home.model.City;

import java.util.List;
import java.util.Map;

public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    List<City> listCitysByIds(List<Integer> ids);

    List<City> listAllCitys();

    int countCitys(Map<String, Object> param);

    List<City> listCitysWithPage(Map<String, Object> param);

    List<City> listCitys(City city);

    List<City> listCitysByName(City city);

    List<City> listCitysByParentId(Integer cityParentId);
}