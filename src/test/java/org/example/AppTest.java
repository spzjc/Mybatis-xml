package org.example;

import static org.junit.Assert.assertTrue;


import com.alibaba.fastjson.JSON;
import dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class AppTest 
{
   private SqlSession session=null;
 @Before
public void init(){
String resource="mybatis-config.xml";
     try {
         InputStream inputStream= Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    session=sqlSessionFactory.openSession();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 @After
    public void close(){
if(session!=null){
session.close();
}
session=null;
 }
 @Test
public void test(){
     UserDao userMapper =session.getMapper(UserDao.class);
     User userList= userMapper.getUserbyId(4);
     System.out.println(JSON.toJSONString(userList));
 }
}
