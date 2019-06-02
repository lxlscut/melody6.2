package scut.melody.service.service;
import scut.melody.entity.OrderDetail;
import java.math.BigDecimal;
import java.util.Date;

public interface OrderMasterService {
    String querywithpage(int start, int end, int pstart, int psize);
    String queryallwithpage(int start, int end);
    int update(String orderId, String buyerName, String buyerOpenid, BigDecimal orderAmount, Byte orderStatus, Byte payStatus, Date createTime, Date updateTime);
    int delete(String orderId);
    OrderDetail selectbyorderid(String orderid);
    String selectidpage(String orderid, int pstart, int psize);
    String selectopenidpage(String openid, int pstart, int psize);
}
