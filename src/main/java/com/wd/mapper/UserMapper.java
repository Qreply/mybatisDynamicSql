package com.wd.mapper;

import com.wd.entity.QueryVo;
import com.wd.entity.User;

import java.util.List;


/**
 * 用户的持久层接口
 */
public interface UserMapper {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数调节
     * @param user 查询的条件：有可能是地址，有可能姓名，有可能都有。。。
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);


}
