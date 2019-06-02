package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.OrderMaster;

import java.util.List;

@Repository
public interface OrderMasterMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);


    List<OrderMaster> queryallwithpage(@Param("start") int start, @Param("end") int end);

    List<OrderMaster> quereywithpage(@Param("start") int start, @Param("end") int end, @Param("pstart") int pstart, @Param("psize") int psize);

    /**
     * 根据openid来查询订单
     * @param userid
     * @param pstart
     * @param psize
     * @return
     */
    List<OrderMaster> querywithuserid(@Param("userid") String userid,@Param("pstart") int pstart,@Param("psize") int psize);

    int count();

    int countuserid(String userid);

    int selectrangepageno(@Param("start")int start,@Param("end")int end);
}