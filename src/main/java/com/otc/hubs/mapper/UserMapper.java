package com.otc.hubs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.otc.hubs.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
