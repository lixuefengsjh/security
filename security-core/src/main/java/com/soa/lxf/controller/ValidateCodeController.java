package com.soa.lxf.controller;

import com.soa.lxf.entity.ImageCode;
import com.soa.lxf.util.ImageCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


/**
 * @author: lxf
 * @create: 2020-04-05 21:57
 * @description: 图片验证码功能
 */
@RestController
@Slf4j
public class ValidateCodeController {
    @GetMapping("/image/code")
    public void createCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String,Object> map=ImageCodeUtil.generateCodeAndPic();
        ImageCode  imageCode=new ImageCode(map.get("code").toString(),(BufferedImage) map.get("codePic"),60L);
        log.info("这是图片验证码：================"+imageCode.getCode());
        HttpSession session= req.getSession();
        session.setAttribute("code",imageCode);
        res.setHeader("Pragma", "no-cache");
        res.setHeader("Cache-Control", "no-cache");
        res.setDateHeader("Expires", -1);
        res.setContentType("image/jpeg");
        ImageIO.write(imageCode.getImage(),"jpeg",res.getOutputStream());
    }


}
