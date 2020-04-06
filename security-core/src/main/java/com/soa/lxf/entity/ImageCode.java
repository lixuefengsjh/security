package com.soa.lxf.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author: lxf
 * @create: 2020-04-05 21:59
 * @description: 图片基本信息
 */
@Setter
@Getter
public class ImageCode {
private String code;
private BufferedImage image;
private LocalDateTime expireTime;
public ImageCode(String code,BufferedImage image,Long  expire){
    this.code=code;
    this.image=image;
    this.expireTime=LocalDateTime.now().plusSeconds(expire);
}
}
