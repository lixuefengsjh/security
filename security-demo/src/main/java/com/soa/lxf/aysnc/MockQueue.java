package com.soa.lxf.aysnc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: lxf
 * @create: 2020-04-03 17:24
 * @description: 模拟虚拟队列
 */
@Component
@Slf4j
@Data
public class MockQueue {
    private String placeHolder;
    private String completeHolder;

}
