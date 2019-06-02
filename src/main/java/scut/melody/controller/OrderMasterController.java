package scut.melody.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.dao.OrderMasterMapper;
import scut.melody.entity.OrderMaster;
import scut.melody.service.service.OrderMasterService;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/ordermaster")
public class OrderMasterController {
    @Autowired
    private OrderMasterService oms;
    @Autowired
    private OrderMasterMapper omm;
    @RequestMapping("/queryall")
    @ResponseBody
    public String queryall(@RequestParam(name = "page",required = false) int page,
                           @RequestParam(name = "rows",required = false)int rows,
                           @RequestParam(name = "start",required = false)Integer start,
                           @RequestParam(name = "end",required = false)Integer end){
        String str = null;
        if(start!=null||end!=null) {
            str = oms.querywithpage(start,end,rows,page);
            System.out.println(str);
        }else {
            str = oms.queryallwithpage(rows*(page-1),rows);
        }
        return str;
    }

    @RequestMapping("/querywithuser")
    @ResponseBody
    public String querywithopenid(@RequestParam(name = "userid") String userid,
                                  @RequestParam(name = "page",required = false) int page,
                                  @RequestParam(name = "rows",required = false)int rows){
        System.out.println(userid);
        System.out.println(page);
        System.out.println(rows);

        return oms.selectopenidpage(userid,page,rows);
    }




    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(name = "orderId",required = true) String orderId,
                         @RequestParam(name = "buyerName",required = false)String buyerName,
                         @RequestParam(name = "evaluate")String evaluate,
                         @RequestParam(name = "orderAmount")String orderAmount
){
        System.out.println(orderId);
        System.out.println(evaluate);
        System.out.println(orderAmount);
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(buyerName);
        orderMaster.setOrderId(orderId);
        BigDecimal bd=new BigDecimal(orderAmount);
        orderMaster.setOrderAmount(bd);
        orderMaster.setEvaluate(evaluate);

        int i = omm.updateByPrimaryKeySelective(orderMaster);
        return String.valueOf(i);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(name = "orderId")String orderId){
        int i = oms.delete(orderId);
        return String.valueOf(i);
    }
}
