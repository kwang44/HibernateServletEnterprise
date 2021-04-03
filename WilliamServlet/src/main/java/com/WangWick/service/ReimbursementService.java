package com.WangWick.service;

import com.WangWick.dao.ReimbursementDao;
import com.WangWick.model.Reimbursement;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public class ReimbursementService {
    private ReimbursementDao reimbursementDao;

    public ReimbursementService() {
        reimbursementDao = new ReimbursementDao();
    }

    public List<Reimbursement> fetchAllReimbursement() {
        return reimbursementDao.getList();
    }

    /**
     * Take in GSON of of shape:
     * {
     *     "id": 0
     *     "amount": 0.0
     *     "submitted": --Timestamp--
     *     "resolved": --TimeStamp--
     *     "description": ""
     *     "author": --id--
     *     "resolver": --id--
     *     "status_id": 0
     *     "type_id": 0
     * }
     */
//    public Reimbursement generateReimbursement(JsonObject jsonObject) {
//
//        Reimbursement reimbursement = new Reimbursement(
//                jsonObject.get("id").getAsInt(),
//                jsonObject.get("amount").getAsFloat(),
//                jsonObject.get("submitted").getAsString());
//    }

    public List<Reimbursement> getReimbursementsById(int id) {
        return reimbursementDao.getByUserId(id);
    }

    public void updateReimbursement(Reimbursement reimbursement) {
        reimbursementDao.update(reimbursement);
    }
}












//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.WangWick.dao.ReimbursementDao;
//import com.WangWick.model.Reimbursement;
//
//public class ReimbursementService {
//	private ReimbursementDao rd;
//	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);
//
//	public ReimbursementService() {
//		rd = new ReimbursementDao();
//	}

//	public void createReimbursement(String json) {
//		try {
//			Reimbursement r = new ObjectMapper().readValue(json, Reimbursement.class);
//			LOGGER.debug("JSON from the client was successfully parsed.");
//			rd.insert(r);
//		} catch (Exception e) {
//			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
//			e.printStackTrace();
//		}
//	}
//
//	public List<Reimbursement> fetchAllReimbursements() {
//		return rd.getList();
//	}
//
//	public List<Reimbursement> getReimbursementsByUserID(int id) {
//		return rd.getByUserId(id);
//	}
//
//	public void updateReimbursements(int[][] i, int r) {
//		rd.updateList(i, r);
//	}
//}
//