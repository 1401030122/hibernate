package com.zhaojie;

import com.zhaojie.Entity.Score;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.List;

@SpringBootTest
@Slf4j
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
        System.out.println("---------score: "+score.toString());


        //插入数据
        Score score1 = new Score();
        score1.setName("赵杰");
        score1.setSubject("高等数学");
        score1.setScore(1000);
        score1.setLocationId(1);
//        score1.setId(100);

//        获取自增主键的数值
        Serializable save = session.save(score1);
        log.info("------自增主键的数值为: "+save.toString());
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testUpdate(){
//        加载hibernate核心配置文件
        Configuration configuration = new Configuration().configure();
//        新建一个SessionFactory 用来获取Session对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
//        开始事务
        Transaction transaction= session.beginTransaction();

//        创建实体对象
        Score score = new Score();

        score.setId(100);
        score.setName("线性代数");

        session.update(score);

//        提交事务
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testDelete(){
//        加载hibernate核心配置文件
        Configuration configuration = new Configuration().configure();
//        新建一个SessionFactory 用来获取Session对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
//        开始事务
        Transaction transaction= session.beginTransaction();

//        创建实体对象
        Score score = new Score();

        score.setId(100);


        session.delete(score);

        try {
            Score score1 = session.get(Score.class,100);
            log.info("---score ： "+score1.toString());
        }catch (Exception e){
            log.info("---错误信息为： "+e.toString());
        }
//        提交事务
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testHqlQuery() {
        //加载 Hibernate 核心配置文件
        Configuration configuration = new Configuration().configure();
        //创建一个 SessionFactory 用来获取 Session 连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取session 连接对象
        Session session = sessionFactory.openSession();
        //开始事务
        Transaction transaction = session.beginTransaction();
        //创建 HQL 语句，语法与 SQL 类似，但操作的是实体类及其属性

        Query query = session.createQuery("from Score where name like ?1");
        //查询所有使用 163 邮箱的用户
        query.setParameter(1, "%赵杰%");
        //获取结果集
        try {
            List<Score> resultList = query.getResultList();
            //遍历结果集
            for (Score user : resultList) {
                System.out.println(user);
            }
        }catch (Exception e){
            log.info("---错误信息为： "+e.toString());
        }
//        提交事务
        transaction.commit();
        //释放资源
        session.close();
        sessionFactory.close();
    }
}
