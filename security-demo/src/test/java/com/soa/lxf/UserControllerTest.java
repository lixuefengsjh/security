package com.soa.lxf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * @author: lxf
 * @create: 2020-03-31 17:48
 * @description: 测试用例
 */
@RunWith(value= SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext  context;

    private MockMvc mvc;
    @Before
    public void setUp(){
        mvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void whenQuerySuccess() throws Exception {
       String result= mvc.perform(MockMvcRequestBuilders.get("/user")
                .param("userName","hhhh")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenQueryUserInfo() throws Exception {
        String result= mvc.perform(MockMvcRequestBuilders.get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..*").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }
    @Test
    public void  insertUserInfo() throws Exception {
        Long date=new Date().getTime()+1000L;
        String  content="{\"userNum\":1,\"name\":\"lxf\",\"passWord\":\"1233\",\"birthDay\":"+date+"}";
        System.out.println(date);
        String result=mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userNum").value(1))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void  updateUserInfo() throws Exception {
        Long date=new Date().getTime();
        String  content="{\"userNum\":1,\"name\":\"lxf\",\"passWord\":\"1233\",\"birthDay\":"+date+"}";
        System.out.println(date);
        String result=mvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userNum").value(1))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void deleteUserInfo() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void uploadFile() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/user/upload")
                .file("file","hello upload".getBytes("UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void downLoadFile() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/user/upload")
                .file("file","hello upload".getBytes("UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
