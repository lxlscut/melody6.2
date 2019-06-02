package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.WechatAuth;

import java.util.List;

@Repository
public interface WechatAuthMapper {
    int deleteByPrimaryKey(Integer wechatAuthId);

    int insert(WechatAuth record);

    int insertSelective(WechatAuth record);

    WechatAuth selectByPrimaryKey(Integer wechatAuthId);

    int updateByPrimaryKeySelective(WechatAuth record);

    int updateByPrimaryKey(WechatAuth record);

    List<WechatAuth> queryallwithpage(@Param("start") int start, @Param("end") int end);

    List<WechatAuth> quereywithpage(@Param("start") int start,@Param("end") int end,@Param("pstart") int pstart,@Param("psize") int psize);

    int delete(@Param("wechatAuthId") int id);

    int count();

    int selectrangepageno(@Param("start")int start,@Param("end")int end);

    /**
     * 通过userid找到openid
     * @param userid
     * @return
     */
    String findopenidbyuserid(String userid);
}