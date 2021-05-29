/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.RoleStatus;
import com.cv.accountswing.entity.RoleStatusKey;
import com.cv.accountswing.util.Util1;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class RoleStatusDaoImpl extends AbstractDao<RoleStatusKey, RoleStatus> implements RoleStatusDao {

    @Override
    public boolean findById(RoleStatusKey key) {
        RoleStatus status = getByKey(key);
        return status == null ? false : Util1.getBoolean(status.getStatus());
    }

    @Override
    public RoleStatus save(RoleStatus status) {
        persist(status);
        return status;
    }
}
