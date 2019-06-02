package scut.melody.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.service.service.OrderMasterService;

import java.util.Map;

@Controller
@RequestMapping("/detail")
public class OrderDetailController {
    @Autowired
    private OrderMasterService oms;
    @RequestMapping("/order")
    public String detail(@RequestParam(name = "orderid") String orderid, Map<String,Object> data){
        System.out.println("orderid"+orderid);
        data.put("order",orderid);
        return "admin/detail";
    }
    @RequestMapping("/show")
    @ResponseBody
    public String show(@RequestParam(name = "order_id",required = true)String orderid,
                       @RequestParam(name = "rows",required = false)int rows,
                       @RequestParam(name = "page",required = false)int page
    ){

        String str = oms.selectidpage(orderid,rows,page);
        System.out.println(str);
        return str;
    }
}
