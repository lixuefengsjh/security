package com.soa.lxf.aysnc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: lxf
 * @create: 2020-04-03 18:05
 * @description: 队列监听器
 */
@Component
@Slf4j
public class MockQueueListener implements ApplicationListener {
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        new Thread(
                ()->{
                    while (true){
                        String orderId=mockQueue.getPlaceHolder();
                        if(StringUtils.isNotBlank(orderId)){
                            log.info("开始处理订单");
                            mockQueue.setCompleteHolder(orderId);
                            log.info("订单处理完毕，你的订单号为："+orderId);
                            deferredResultHolder.getMap().get(orderId).setResult(mockQueue.getCompleteHolder());
                            mockQueue.setPlaceHolder(null);
                        }else{
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

    }
}
