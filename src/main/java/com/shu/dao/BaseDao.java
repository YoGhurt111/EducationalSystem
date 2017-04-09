package com.shu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/4/9.
 */
public abstract class BaseDao {
    @Resource
    private SessionFactory sessionFactory;

    public Session getSesstion(){
        return sessionFactory.openSession();
    }

}
