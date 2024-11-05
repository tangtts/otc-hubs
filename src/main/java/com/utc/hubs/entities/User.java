package com.utc.hubs.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
@TableName(value = "users")
@Getter
@Setter
public class User implements Serializable {
  @TableId(type = IdType.AUTO)
  private String userId;

  private String userName;

  private String phoneNumber;
  private String email;
}
