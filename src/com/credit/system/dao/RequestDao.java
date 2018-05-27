package com.credit.system.dao;

import com.credit.system.entity.Request;

import java.util.List;

public interface RequestDao {
    int create(Request request);

    void updateStatus(Request request);

    List<Request> findCreatedRequests();
}
