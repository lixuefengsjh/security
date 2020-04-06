package com.soa.lxf.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.soa.lxf.valid.MyValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author: lxf
 * @create: 2020-03-31 18:28
 * @description: 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    public interface SimpleView{}
    public interface DetailView extends SimpleView{}

    @JsonView(SimpleView.class)
    private Long userNum;

    @JsonView(SimpleView.class)
    @MyValid(message="名字不能为lxf")
    private String name;

    @JsonView(DetailView.class)
    @NotBlank
    private String passWord;

    @JsonView(DetailView.class)
    @Past(message="必须是一个过去的时间")
    private Date birthDay;
}
