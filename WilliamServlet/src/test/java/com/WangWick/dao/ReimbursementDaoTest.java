package com.WangWick.dao;

import com.WangWick.model.Reimbursement;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReimbursementDaoTest {


    @Test
    void createReimbustmentTest(){
        ReimbursementDao reimbursementDao = new ReimbursementDao();
        reimbursementDao.insert(new Reimbursement());

    }
}
