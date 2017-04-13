package com.shu.dao;

import com.shu.entity.Admin;
import com.shu.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 2017/4/10.
 */
@Repository
public class AdminDao extends BaseDao{
    public List<Admin> selectAdmin(String username, String pwd){
        List<Admin> adminList = this.getSesstion().createQuery("from Admin where id=? and password=?" )
                .setParameter(0, username)
                .setParameter(1, pwd)
                .list();
        return adminList;
    }
}
