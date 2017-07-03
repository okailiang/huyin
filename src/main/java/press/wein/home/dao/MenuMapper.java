package press.wein.home.dao;

import press.wein.home.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> listAllMenus();

    List<Menu> listMenus(Menu menu);

    int checkRepeatName(Menu menu);
}