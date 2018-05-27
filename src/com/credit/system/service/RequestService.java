package com.credit.system.service;

import com.credit.system.entity.Request;

public interface RequestService {
    int create(Request request);

    void updateStatus(Request request);
}
