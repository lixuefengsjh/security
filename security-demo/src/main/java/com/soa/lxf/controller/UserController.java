package com.soa.lxf.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.soa.lxf.entity.UserInfo;
import com.soa.lxf.exception.UserNotEixstException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: lxf
 * @create: 2020-03-31 18:05
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    @JsonView(UserInfo.SimpleView.class)
    public List<UserInfo> getUserInfos(@RequestParam(value = "name",required = false,defaultValue = "lxf") String userName){
        UserInfo info1=new UserInfo(1L,"lxf","123456",new Date());
        UserInfo info2=new UserInfo(2L,"lxf1","123456",new Date());
        UserInfo info3=new UserInfo(3L,"lxf2","123456",new Date());
        List  list =new ArrayList(Arrays.asList("info1","info2","info3"));
        return list;
    }
    @GetMapping(  consumes = {"text/html"})
    public String getInfo(){
        return  "hello.world";
    }
    /**
     * 这里可以用正则表达式优化
     * @param userNum
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(UserInfo.DetailView.class)
    public UserInfo getUserInfo(@PathVariable("id") Long userNum){
        UserInfo info1=new UserInfo(1L,"lxf","123456",new Date());
        System.out.println("id-------------------"+userNum);
        if(userNum==0){
            throw new UserNotEixstException(userNum);
        }
        return info1;
    }
    /**
     * 关注
     * @param userInfo
     * @return
     */
    @PostMapping
    public UserInfo insertUserInfo(@Valid  @RequestBody  UserInfo userInfo, BindingResult error){
        if(error.hasErrors()){
            error.getAllErrors().stream().forEach(e-> System.out.println(e.getDefaultMessage()));
        }
        System.out.println(userInfo);
        userInfo.setUserNum(1L);
        return userInfo;
    }

    /**
     * 测试
     * @param userInfo
     * @param error
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public UserInfo updateUserInfo(@Valid  @RequestBody  UserInfo userInfo, BindingResult error){
        if(error.hasErrors()){
            error.getAllErrors().stream().forEach(e-> {
                FieldError er=(FieldError)e;
                String message=er.getField()+":"+e.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(userInfo);
        userInfo.setUserNum(1L);
        return userInfo;
    }
    @DeleteMapping("/{id}")
    public void  delete( @PathVariable("id") Long userNum){
        System.out.println(userNum+":  删除数据成功");
    }

    @PostMapping("/upload")
    public void  uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getName()+"============"+file.getOriginalFilename());
        File myFile=new File("D:\\work\\allproject\\security\\security-demo\\src\\main\\java\\com\\soa\\lxf\\controller\\", LocalDateTime.now().getDayOfMonth()+".txt");
        file.transferTo(myFile);
    }
    @GetMapping("/downLoad/{name}")
    public void downLoadFile(HttpServletRequest req, HttpServletResponse res,@PathVariable("name") String name){
        File myFile=new File("D:\\work\\allproject\\security\\security-demo\\src\\main\\java\\com\\soa\\lxf\\controller\\", name+".txt");
        try {
            res.setContentType("application/x-download");
            res.addHeader("Content-Disposition", "attachment;filename=test.txt");
            InputStream  in =new FileInputStream(myFile);
            OutputStream out =res.getOutputStream();
            IOUtils.copy(in,out);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
