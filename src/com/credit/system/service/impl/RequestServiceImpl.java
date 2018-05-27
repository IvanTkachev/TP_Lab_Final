package com.credit.system.service.impl;

import com.credit.system.dao.RequestDao;
import com.credit.system.dao.impl.RequestDaoImpl;
import com.credit.system.entity.Request;
import com.credit.system.service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private RequestDao requestDao = new RequestDaoImpl();
    @Override
    public int create(Request request) {
        return requestDao.create(request);
    }

    @Override
    public void update(Request request) {
        requestDao.update(request);
    }

    @Override
    public List<Request> getCreatedRequests() { return requestDao.findCreatedRequests(); }

    @Override
    public Request getRequestById(int id) { return requestDao.findRequestById(id); }

    @Override
    public List<Request> getAnalyzedRequests() { return requestDao.findAnalyzedRequests(); }

    @Override
    public List<Request> getStaffedRequests() { return requestDao.findStaffedRequests(); }

    @Override
    public List<Request> getConfirmedRequests() { return requestDao.findConfirmedRequests(); }

    @Override
    public void delete(int id) { requestDao.delete(id); }
}
