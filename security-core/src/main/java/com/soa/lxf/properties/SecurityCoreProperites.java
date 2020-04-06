package com.soa.lxf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lxf
 * @create: 2020-04-05 10:03
 * @description: 核心配置类
 */
@Configuration
@EnableConfigurationProperties(SecurityProperites.class)
public class SecurityCoreProperites {
}
