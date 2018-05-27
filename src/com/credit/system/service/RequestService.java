package com.credit.system.service;

import com.credit.system.entity.Request;

import java.util.List;

public interface RequestService {
    int create(Request request);

    void updateStatus(Request request);

    List<Request> getCreatedRequests();

    Request getRequestById(int id);
}
