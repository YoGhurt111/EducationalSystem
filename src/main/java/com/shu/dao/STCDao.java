package com.shu.dao;

import com.shu.entity.STC;
import com.shu.entity.Student;
import com.shu.entity.TC;
import com.shu.entity.Teacher;
import net.sf.ehcache.transaction.xa.EhcacheXAException;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shu.util.TermTools.getCurrentTerm;
import static com.shu.util.TermTools.getCurrentYear;

/**
 * Created by Dell on 2017/5/25.
 */
@Repository
public class STCDao extends BaseDao {

    public void update(STC stc){
        this.getSesstion().update(stc);
    }

    public void insert(STC stc){
        this.getSesstion().save(stc);
    }

    public String delete(Student student, TC tc){
       try {
           int i = this.getSesstion().createQuery("delete from STC where student=? and tc=?")
                   .setParameter(0, student)
                   .setParameter(1, tc)
                   .executeUpdate();
           if (i >= 1){
               return new JSONObject().put("status","success").toString();
           }
           else {
               return new JSONObject().put("status","fail").toString();
           }
       }catch (Exception e){
           return new JSONObject().put("status","fail").toString();
       }
    }


}
