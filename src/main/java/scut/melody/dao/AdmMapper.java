package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.Adm;
@Repository
public interface AdmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adm record);

    int insertSelective(Adm record);

    Adm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adm record);

    int updateByPrimaryKey(Adm record);

    /**
     * 根据管理员名称获取对象
     * @param username
     * @return
     */
     Adm selectbyusername(@Param("username") String username);
}