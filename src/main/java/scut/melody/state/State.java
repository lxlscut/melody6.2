package scut.melody.state;

import java.util.HashMap;
import java.util.Map;

public class State {
    //账户状态
   public Map<String,String> map = new HashMap<>();
   //订单状态
   public Map<String,String> paymap = new HashMap<>();
   //交易类型
   public Map<String,String> tradetype=new HashMap<>();
   //支付状态
   public Map<String,String> paystatus = new HashMap<>();
   public State(){
       map.put("0","锁定");
       map.put("1","活动");

       map.put("锁定","0");
       map.put("活动","1");

       paymap.put("0","新订单");
       paymap.put("1","未填写openid");
       paymap.put("2","估价中");
       paymap.put("3","待支付");
       paymap.put("4","制作中");
       paymap.put("5","发货中");
       paymap.put("6","完结");
       paymap.put("7","已取消");

       paymap.put("新订单","0");
       paymap.put("未填写openid","1");
       paymap.put("估价中","2");
       paymap.put("待支付","3");
       paymap.put("制作中","4");
       paymap.put("发货中","5");
       paymap.put("完结","6");
       paymap.put("已取消","7");

       tradetype.put("1","诚意金");
       tradetype.put("2","曲谱定制");
       tradetype.put("诚意金","1");
       tradetype.put("曲谱定制","2");

       paystatus.put("1","待支付");
       paystatus.put("2","支付成功");
       paystatus.put("待支付","1");
       paystatus.put("支付成功","2");

   }
}
