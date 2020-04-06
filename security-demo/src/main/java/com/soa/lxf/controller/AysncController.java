package com.soa.lxf.controller;

import com.soa.lxf.aysnc.DeferredResultHolder;
import com.soa.lxf.aysnc.MockQueue;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: lxf
 * @create: 2020-04-03 16:12
 * @description: 异步多线程处理
 */
@RestController
@Slf4j
public class AysncController {
    @GetMapping("/order1")
    public Map<String,Object> getOrder1() throws Exception {
        log.info("主线程开始");
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> map=new HashMap<>();
//        map.put("id","0001");
//        map.put("price",200);
//        log.info("主线程结束");
        Callable<Map<String,Object>> callable= ()->{
                log.info("副线程开始");
                Map<String, Object> map=new HashMap<>();
                TimeUnit.SECONDS.sleep(2);
                map.put("id","0001");
                map.put("price",200);
                log.info("副线程结束");
            return map;
        };
        log.info("主线程开始");
        return  callable.call();
    }

    /**
     * 这个异步处理将更复杂
     * 基本的思路：前端发起订单请求，服务器将消息放到消息队列，监听线程发现消息队列信息处理信息。另外一个监听线程监听消息队列处理结果。
     * 然后 将消息返回到前端
     * @return
     */
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @GetMapping("/order")
    public DeferredResult<String> getOrder() throws InterruptedException {
        Map<String, Object> map=new HashMap<>();
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceHolder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
//        map.put(orderNumber,result.getResult());
        return result;
    }
}
