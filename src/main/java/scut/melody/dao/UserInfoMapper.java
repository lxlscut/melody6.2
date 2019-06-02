package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.UserInfo;

import java.util.List;
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userInfoId);

    /**
     * 查询数据库中的数据条数
     * @return
     */
    int count();

    /**
     * 数据库中相应id范围的数量
     * @return
     */
    int selectidno(@Param("start") int idstart,@Param("end") int idend);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    /**
     * 分页查询所有
     * @param page
     * @param rows
     * @return
     */
    List<UserInfo> selectallwithpage(@Param("pageNumber") int page,@Param("pageSize") int rows);

    /**
     * 分页查询对应id内的数据
     * @param idstart
     * @param idend
     * @param page
     * @param rows
     * @return
     */
    List<UserInfo> querywithidpage(@Param("start") int idstart,@Param("end") int idend,@Param("pstart") int page,@Param("psize") int rows);

    UserInfo selectByPrimaryKey(Integer userInfoId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

}