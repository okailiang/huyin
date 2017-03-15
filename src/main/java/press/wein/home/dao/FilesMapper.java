package press.wein.home.dao;

import press.wein.home.model.Files;

public interface FilesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
}