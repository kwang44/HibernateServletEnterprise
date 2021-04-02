package com.WangWick.dao;

import com.WangWick.model.Reimbursement;
import com.WangWick.model.User;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReimbursementDaoTest {

    @Test
    @Order(1)
    void createReimbustmentTest(){
        UserDao userDao = new UserDao();
        User user = new User(50000);
        userDao.insert(user);
        user = userDao.getByUsername(user.getUsername());

        ReimbursementDao reimbursementDao = new ReimbursementDao();
        for (int i = 0; i < 50; i++) {
            Reimbursement reimbursement = new Reimbursement(i);
            reimbursement.setAuthor(user);
            reimbursement.setResolver(user);
            reimbursementDao.insert(reimbursement);
        }
    }

    @Test
    @Order(2)
    void getAllTest() {
        UserDao userDao = new UserDao();
        User user = new User(50000);
        userDao.insert(user);
        user = userDao.getByUsername(user.getUsername());

        ReimbursementDao reimbursementDao = new ReimbursementDao();
        List<Reimbursement> reimbursementList = reimbursementDao.getList();
        List<Reimbursement> reimbursementList1 = reimbursementDao.getByUserId(user.getUser_id());

        for (int i = 0; i < 50; i++) {
            Reimbursement reimbursement = reimbursementList.get(i);
            Reimbursement reimbursement1 = reimbursementList1.get(i);
            Assertions.assertEquals(reimbursement.getId(), reimbursement1.getId());
        }
    }

    @Test
    @Order(6)
    void deleteAllTest(){
        ReimbursementDao reimbursementDao = new ReimbursementDao();
        List<Reimbursement> reimbursementList = reimbursementDao.getList();

        for (Reimbursement reimbursement : reimbursementList) {
            reimbursementDao.delete(reimbursement);
        }
    }
}
