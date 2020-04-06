package com.soa.lxf.properties;

import com.soa.lxf.entity.LoginResponseType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lxf
 * @create: 2020-04-05 09:50
 * @description: 统一的配置管理项目
 */
@ConfigurationProperties(prefix="lxf.security")
@Data
public class SecurityProperites {
    private BrowserProperties browser = new BrowserProperties();
    private LoginResponseType type=LoginResponseType.JSON;
}
