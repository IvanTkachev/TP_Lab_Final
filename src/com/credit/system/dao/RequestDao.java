package com.credit.system.dao;

import com.credit.system.entity.Request;

import java.util.List;

public interface RequestDao {
    int create(Request request);

    void update(Request request);

    List<Request> findCreatedRequests();

    Request findRequestById(int id);
}
