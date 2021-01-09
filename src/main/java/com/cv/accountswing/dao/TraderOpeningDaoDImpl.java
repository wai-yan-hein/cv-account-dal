/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;


import com.cv.accountswing.entity.TraderOpeningD;
import com.cv.accountswing.entity.view.VTmpTraderBalance;
import com.cv.accountswing.entity.view.VTraderOpeningDetail;
import com.cv.accountswing.util.Util1;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class TraderOpeningDaoDImpl extends AbstractDao<Long, TraderOpeningD> implements TraderOpeningDaoD{
    
    @Override
    public TraderOpeningD save(TraderOpeningD tod){
        persist(tod);
        return tod;
    }
    
    @Override
    public TraderOpeningD findById(Long id){
        TraderOpeningD tod = getByKey(id);
        return tod;
    }
    
    @Override
    public List<VTraderOpeningDetail> search(String tranIdH){
        String strSql = "select o from VTraderOpeningDetail o where o.tranIdH = " + tranIdH;
        List<VTraderOpeningDetail> listVTAD = findHSQLList(strSql);
        return listVTAD;
    }
    
    @Override
    public int delete(String tranId){
        String strSql = "delete from TraderOpeningD o where o.tranId = " + tranId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
    
    private void insertOpBalaceFilter(String compCode, String traderId, String opDate, 
            String curr, String userCode) throws Exception{
        String strSql = "insert into tmp_trader_op_filter(comp_code, trader_id, curr_id, op_date, op_amt, user_code) " +
                        "select vto.comp_code, vto.trader_id, vto.curr_id, vto.op_date, vto.op_amt, '" + userCode + "' " +
                        "  from v_trader_opening vto, (select comp_code, trader_id, curr_id, max(op_date) op_date " +
                                                       " from v_trader_opening " +
                                                       "where op_date <= '" + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "' " +
                                                       "group by comp_code, trader_id, curr_id) vmto " +
                        " where vto.comp_code = vmto.comp_code and vto.trader_id = vmto.trader_id and " +
                        "       vto.curr_id = vmto.curr_id and vto.op_date = vmto.op_date and vto.op_date <= '" +
                        Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "' ";
        String strFilter = "";
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = " vto.comp_code = '" + compCode + "'";
            }else{
                strFilter = strFilter + " and vto.comp_code = '" + compCode + "'";
            }
        }
        
        if(!traderId.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = " vto.trader_id = " + traderId;
            }else{
                strFilter = strFilter + " and vto.trader_id = " + traderId;
            }
        }
        
        if(!curr.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = " vto.curr_id = '" + curr + "'";
            }else{
                strFilter = strFilter + " and vto.curr_id = '" + curr + "'";
            }
        }
        
        if(!strFilter.isEmpty()){
            strSql = strSql + " and " + strFilter;
        }
        
        execSQL(
            "delete from tmp_trader_op_filter where user_code = '" + userCode + "'",
            "delete from tmp_trader_balance where user_code = '" + userCode + "'",
            strSql
        );
    }
    
    @Override
    public List<VTmpTraderBalance> getTraderBalance(String compCode, String traderId, String opDate, 
            String curr, String userCode) throws Exception{
        insertOpBalaceFilter(compCode, traderId, opDate, curr, userCode);
        
        String strSql = "insert into tmp_trader_balance(comp_code, trader_id, curr_id, balance, user_code) " +
                        "select a.comp_code, a.trader_id, a.curr_id, sum(ifnull(balance, 0)), '" + userCode + "' " +
                        "from (" +
                        "select comp_code, trader_id, curr_id, ifnull(op_amt,0) balance " +
                        "from tmp_trader_op_filter " +
                        "where user_code = '" + userCode + "' " +
                        "group by comp_code, trader_id, curr_id " +
                        "union all " +
                        "select gl.comp_code, gl.trader_code, gl.from_cur_id, ifnull(gl.dr_amt,0) - ifnull(gl.cr_amt,0) balance " +
                        "from (select comp_code, trader_id, curr_id, op_date " +
                        "		from tmp_trader_op_filter where user_code = '" + userCode + "') opf, gl " +
                        "where opf.comp_code = gl.comp_code and opf.trader_id = gl.trader_code and " +
                        "	  opf.curr_id = gl.from_cur_id and gl.gl_date >= opf.op_date and " +
                        "gl.gl_date <= '" + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "') a " +
                        "group by a.comp_code, a.trader_id, a.curr_id";
        
        execSQL(strSql);
        
        String strHSql = "select o from VTmpTraderBalance o where o.userCode = '" + userCode + "'";
        List<VTmpTraderBalance> listVTTB = findHSQLList(strHSql);
        return listVTTB;
    }
}
