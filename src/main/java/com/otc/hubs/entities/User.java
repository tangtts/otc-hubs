package com.otc.hubs.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otc.hubs.TypeHandler.ListTypeHandler;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@TableName(value = "users",autoResultMap = true)
@Getter
@Setter
public class User extends BaseEntity implements Serializable{
  @TableId()
  private String userId;

  private String userName;

  private String phoneNumber;
  private String email;

  @JsonIgnore
  private String password;

  @TableField(typeHandler = ListTypeHandler.class)
  private List<String> images;
}
