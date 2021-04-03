package com.WangWick.model;

public class ReimbursementUpdateModel {
    int id;
    boolean approved;

    public ReimbursementUpdateModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApprove(boolean approve) {
        this.approved = approve;
    }
}
