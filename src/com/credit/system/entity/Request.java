package com.credit.system.entity;

import java.util.List;
import java.util.Objects;

public class Request {
    private int id;
    private String name;
    private UserType type;
    private int amount;
    private RequestType requestType;
    private List<String> attachments;

    public Request() {
    }

    public Request(int id, String name, UserType type, int amount, RequestType requestType, List<String> attachments) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.requestType = requestType;
        this.attachments = attachments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                amount == request.amount &&
                Objects.equals(name, request.name) &&
                type == request.type &&
                Objects.equals(requestType, request.requestType) &&
                Objects.equals(attachments, request.attachments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, amount, requestType, attachments);
    }
}
