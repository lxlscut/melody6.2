package scut.melody.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import scut.melody.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(String detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> selectidpage(@Param("order_id")String orderid, @Param("pstart")int pstart, @Param("psize")int psize);

    int count(String id);

    OrderDetail selectorderid(String orderid);
}