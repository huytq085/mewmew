package com.culicode.dating.mewmew.practice;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.domain.UserRole;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class.getName());
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // Tất cả các lệnh hành động với DB thông qua Hibernate
            // đều phải nằm trong 1 giao dịch (Transaction)
            // Bắt đầu giao dịch
            session.getTransaction().begin();

            // Tạo một câu lệnh HQL query object.
            // Tương đương với Native SQL:
            // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO

//            String sql = "select ur.appRole.roleName from UserRole ur where ur.user.id = :userId";
            String sql = "select ur.appRole.roleName from UserRole ur where ur.user.username = :username";
            System.out.println(sql);
            Query<String> query = session.createQuery(sql);
            query.setParameter("username", "admin2");
            List<String> roles = query.getResultList();

            roles.forEach(System.out::println);

            // Commit dữ liệu
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback trong trường hợp có lỗi xẩy ra.
            session.getTransaction().rollback();
        }
    }
}
