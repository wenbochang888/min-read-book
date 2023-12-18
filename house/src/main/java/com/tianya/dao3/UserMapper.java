package com.tianya.dao3;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianya.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author changwenbo
 * @date 2023/5/10 20:55
 */
@Repository("userMapper")
public interface UserMapper extends BaseMapper<User> {

}
