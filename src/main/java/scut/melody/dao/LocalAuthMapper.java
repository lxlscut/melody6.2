package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.LocalAuth;

import java.util.Date;
import java.util.List;

@Repository
public interface LocalAuthMapper {
    int count();

    int deleteByPrimaryKey(Integer localAuthId);

    int insert(LocalAuth record);

    int insertSelective(LocalAuth record);

    LocalAuth selectByPrimaryKey(Integer localAuthId);

    int updateByPrimaryKeySelective(LocalAuth record);

    int updateByPrimaryKey(LocalAuth record);

    List<LocalAuth> localselectbyidpage(@Param("start") int start, @Param("end")int end, @Param("pstart")int pstart, @Param("psize")int psize);

    List<LocalAuth> queryallwithpage(@Param("start") int start, @Param("end") int end);

    int selectrangepageno(@Param("start")int start,@Param("end")int end);

    int update(@Param("userId") int user_id, @Param("username")String username, @Param("createTime") Date create_time, @Param("updateTime")Date update_time, @Param("localAuthId")int local_auth_id);

}