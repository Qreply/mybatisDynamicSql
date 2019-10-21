package com.wd.test;

import com.wd.entity.QueryVo;
import com.wd.entity.User;
import com.wd.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis的入门案例
 */

public class MybatisTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;



    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成读取字节流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession(true);
        //4.使用SqlSession创建Mapper代理的接口对象
        userMapper = session.getMapper(UserMapper.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After //用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
       // session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询所有
     * @throws Exception
     */
    @Test
    public void testFindAll() throws Exception{
        //5.执行查询所有方法
        List<User> users = userMapper.findAll();
        for (User user:users){
            System.out.println(user);
        }
    }


    /**
     * 测试根据id查询用户信息
     */
    @Test
    public void testFindById(){
        User user = userMapper.findById(50);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        //5.执行查询一个方法
        List<User> users = userMapper.findByName("m");
        for(User user : users){
            System. out.println(user);
        }
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%my%");
        vo.setUser(user);
        List<User> users = userMapper.findUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }
    }

    /**
     * 根据情况查询
     */
    @Test
    public void testfindByCondition(){
        User u = new User();
        u.setUsername("aotocommit");
        u.setSex("m");
        List<User>  users = userMapper.findUserByCondition(u);
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试foreach标签的使用
     */
    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(46);
        vo.setIds(list);

        //5.执行查询所有方法
        List<User> users = userMapper.findUserInIds(vo);
        for(User user : users){
            System.out.println(user);
        }

    }



}


