package com.zhaojie;

import com.zhaojie.Entity.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testHibernate(){
//        加载核心配置文件
        Configuration configuration = new Configuration().configure();
//        创建一个sessionFactory来获取session连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        获取session连接对象
        Session session = sessionFactory.openSession();
//        开始事务
        Transaction transaction = session.beginTransaction();
//        更具主键查询数据

        // 查找数据
        Score score = session.get(Score.class,1);
        System.out.println("---------------------"+score+"-----------");


        //插入数据
        Score score1 = new Score();
        score1.setName("赵杰");
        score1.setSubject("高等数学");
        score1.setScore(1000);
        score1.setLocationId(1);
        score1.setId(100);
        session.save(score1);

        transaction.commit();
        session.close();
        sessionFactory.close();


    }

}
