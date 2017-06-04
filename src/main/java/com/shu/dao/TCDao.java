package com.shu.dao;

import com.shu.entity.Course;
import com.shu.entity.TC;
import com.shu.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 2017/5/25.
 */
@Repository
public class TCDao extends BaseDao{
    public TC getTCByTIdAndCId(String t_id, String c_id){
        return  (TC) this.getSesstion().createQuery("from TC where t_id=? and c_id=?")
                .setParameter(0, t_id)
                .setParameter(1, c_id)
                .list().get(0);
    }

    public TC getTCById(int id){
        return this.getSesstion().get(TC.class, id);
    }

    public List<TC> getAll(){
        return this.getSesstion().createQuery("from TC").list();
    }
}
