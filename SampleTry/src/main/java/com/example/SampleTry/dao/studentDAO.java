package com.example.SampleTry.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



import com.example.SampleTry.models.studentVO;

import java.util.List;

public class studentDAO {
    public int authenticate(studentVO boom) {
        System.out.println("Auth");
        int ans=0;
        try{
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.studentVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();
            List<studentVO> temp =
                    s.createQuery("from studentVO where firstName = \'"+boom.getFirstName()+
                                    "\'").list();
            System.out.println(temp.size());
            ans = temp.size();
            s.close();

        } catch(Exception e) {
            System.out.println("ERROR");
            System.out.println(e);
        }

        return ans;
    }

//    public int updateTry() {
//        SessionFactory sf = new Configuration().
//                configure().
//                addAnnotatedClass(com.example.SampleTry.models.studentVO.class).
//                buildSessionFactory();
//        String hql = "UPDATE Employee set salary = :salary "  +
//                "WHERE id = :employee_id";
//        Query query = session.createQuery(hql);
//        query.setParameter("salary", 1000);
//        query.setParameter("employee_id", 10);
//        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);
//
//        return 1;
//
//    }
    // generate rom req


}
