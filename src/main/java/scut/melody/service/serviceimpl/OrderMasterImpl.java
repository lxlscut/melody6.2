package scut.melody.service.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scut.melody.dao.OrderDetailMapper;
import scut.melody.dao.OrderMasterMapper;
import scut.melody.entity.OrderDetail;
import scut.melody.entity.OrderMaster;
import scut.melody.service.service.OrderMasterService;
import scut.melody.state.State;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderMasterImpl implements OrderMasterService {
    @Autowired
    private OrderMasterMapper omm;
    @Autowired
    private OrderDetailMapper odm;

    //需要用new初始化对象，否则会有空指针
    private State state = new State();
    @Override
    public String querywithpage(int start, int end, int rows, int page) {
        int i = omm.selectrangepageno(start,end);
        List<OrderMaster> list = omm.quereywithpage(start,end,rows*(page-1),rows*page);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(OrderMaster u:list){
                str.append("{\"orderId\":\"");
                str.append(u.getOrderId());

                str.append("\",\"buyerName\":\"");
                str.append(u.getBuyerName());

                str.append("\",\"buyerUserid\":\"");
                str.append(u.getUserid());

                str.append("\",\"buyerPadId\":\"");
                str.append(u.getBuyerPadId());

                str.append("\",\"orderAmount\":\"");
                str.append(u.getOrderAmount());

                str.append("\",\"orderStatus\":\"");
                str.append(state.paymap.get(String.valueOf(u.getOrderStatus())));

                str.append("\",\"payStatus\":\"");
                str.append(state.paystatus.get(String.valueOf(u.getPayStatus())));

                str.append("\",\"tradeType\":\"");
                str.append(state.tradetype.get(String.valueOf(u.getTradeType())));

                str.append("\",\"evaluate\":\"");
                str.append(u.getEvaluate());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());


                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        return b;
    }

    @Override
    public String queryallwithpage(int page, int psize) {
        int i = omm.count();
        List<OrderMaster> list = omm.queryallwithpage(page,psize);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(OrderMaster u:list){
                str.append("{\"orderId\":\"");
                str.append(u.getOrderId());

                str.append("\",\"buyerName\":\"");
                str.append(u.getBuyerName());

                str.append("\",\"buyerUserid\":\"");
                str.append(u.getUserid());

                str.append("\",\"buyerPadId\":\"");
                str.append(u.getBuyerPadId());

                str.append("\",\"orderAmount\":\"");
                str.append(u.getOrderAmount());

                str.append("\",\"orderStatus\":\"");
             String orderstatus =   state.paymap.get(String.valueOf(u.getOrderStatus()));
               str.append(orderstatus);

                str.append("\",\"payStatus\":\"");
               String paystatus =  state.paystatus.get(String.valueOf(u.getPayStatus()));
                str.append(paystatus);

                str.append("\",\"tradeType\":\"");
                String tradetype = state.tradetype.get(String.valueOf(u.getTradeType()));
                str.append(tradetype);

                str.append("\",\"evaluate\":\"");
                str.append(u.getEvaluate());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());


                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        System.out.println("字符串为"+b);
        return b;
    }

    /**
     * 保留修改功能，但管理员也无此权限
     * @param orderId
     * @param buyerName
     * @param buyerOpenid
     * @param orderAmount
     * @param orderStatus
     * @param payStatus
     * @param createTime
     * @param updateTime
     * @return
     */
    @Override
    public int update(String orderId, String buyerName, String buyerOpenid, BigDecimal orderAmount, Byte orderStatus, Byte payStatus, Date createTime, Date updateTime) {
       OrderMaster order_master = new OrderMaster();

       int i = omm.updateByPrimaryKey(order_master);
       return i;
    }

    @Override
    public int delete(String orderId) {
        int i = omm.deleteByPrimaryKey(orderId);
        return i;
    }

    @Override
    public OrderDetail selectbyorderid(String orderid) {
        OrderDetail order_detail = odm.selectorderid(orderid);
        return order_detail;
    }

    @Override
    public String selectidpage(String userid, int rows, int page) {
         int i =odm.count(userid);
         List<OrderDetail> list= odm.selectidpage(userid,rows*(page-1),rows*page);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(OrderDetail u:list){
                str.append("{\"detailId\":\"");
                str.append(u.getOrderId());

                str.append("\",\"orderId\":\"");
                str.append(u.getOrderId());

                str.append("\",\"productAddress\":\"");
                str.append(u.getProductAddress());

                str.append("\",\"productName\":\"");
                str.append(u.getProductName());

                str.append("\",\"productPrice\":\"");
                str.append(u.getProductPrice());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());

                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        return b;
    }

    @Override
    public String selectopenidpage(String openid, int pstart, int psize) {
        int i = omm.countuserid(openid);
        List<OrderMaster> list = omm.querywithuserid(openid,(pstart-1)*psize,psize);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(OrderMaster u:list){
                str.append("{\"orderId\":\"");
                str.append(u.getOrderId());

                str.append("\",\"buyerName\":\"");
                str.append(u.getBuyerName());

                str.append("\",\"buyerUserid\":\"");
                str.append(u.getUserid());

                str.append("\",\"buyerPadId\":\"");
                str.append(u.getBuyerPadId());

                str.append("\",\"orderAmount\":\"");
                str.append(u.getOrderAmount());

                str.append("\",\"orderStatus\":\"");
                String orderstatus =   state.paymap.get(String.valueOf(u.getOrderStatus()));
                str.append(orderstatus);

                str.append("\",\"payStatus\":\"");
                String paystatus =  state.paystatus.get(String.valueOf(u.getPayStatus()));
                str.append(paystatus);

                str.append("\",\"tradeType\":\"");
                String tradetype = state.tradetype.get(String.valueOf(u.getTradeType()));
                str.append(tradetype);

                str.append("\",\"evaluate\":\"");
                str.append(u.getEvaluate());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());


                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        System.out.println("字符串为"+b);
        return b;
    }
}
