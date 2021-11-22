package com.example.SampleTry.dao;


import com.example.SampleTry.models.exchgreqstVO;
import com.example.SampleTry.models.hostelRoomVO;
import com.example.SampleTry.models.studentDetailsVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class hostelRoomDAO {

    public List<hostelRoomVO> availableRooms (String currSID, int currRno) {
        List<hostelRoomVO> answer = new ArrayList<>();

        try {
            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.hostelRoomVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

            answer =
                    s.createQuery("from hostelRoomVO hrv where hrv.rno <>"+currRno).list();

//            for(hostelRoomVO i : answer) {
//                System.out.println(i.getRno());
//            }

            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return answer;
    }
    public hostelRoomVO idDetails(String sid) {
        hostelRoomVO lrv = new hostelRoomVO();

        try {

            SessionFactory sf = new Configuration().
                    configure().
                    addAnnotatedClass(com.example.SampleTry.models.hostelRoomVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.exchgreqstVO.class).
                    addAnnotatedClass(com.example.SampleTry.models.studentDetailsVO.class).
                    buildSessionFactory();
            Session s = sf.openSession();

            List<studentDetailsVO> temp =
                    s.createQuery("from studentDetailsVO where sid = \'"+sid+"\'").list();
            lrv.setRoom(temp.get(0).getHost_room().getRoom());
            lrv.setRno(temp.get(0).getHost_room().getRno());
            lrv.setHostelNo(temp.get(0).getHost_room().getHostelNo());
            s.close();

        }catch (Exception e) {
            System.out.println("idDetails :: "+ e);
        }

        return lrv;
    }
}
