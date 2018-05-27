package com.credit.system.dao;

import com.credit.system.entity.Request;

import java.util.List;

public interface RequestDao {
    int create(Request request);

    void update(Request request);

    List<Request> findCreatedRequests();

    List<Request> findAnalyzedRequests();

    List<Request> findStaffedRequests();

    List<Request> findConfirmedRequests();

    Request findRequestById(int id);

    void delete(int id);
}
