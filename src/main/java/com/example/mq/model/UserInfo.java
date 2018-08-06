package com.example.mq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 描述
 */
public class UserInfo implements Serializable {

    private String code;

    private String name;

    private List<String> jobs;

    private Date createDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", jobs=" + jobs +
                ", createDate=" + createDate +
                '}';
    }
}
