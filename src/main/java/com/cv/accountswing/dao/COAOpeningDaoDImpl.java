
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.util.Util1;
import com.cv.accountswing.entity.AccOpeningD;
import com.cv.accountswing.entity.temp.TmpOpeningClosing;
import com.cv.accountswing.entity.view.VAccOpeningD;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class COAOpeningDaoDImpl extends AbstractDao<Long, AccOpeningD> implements COAOpeningDaoD {
    
    private static final Logger logger = LoggerFactory.getLogger(COAOpeningDaoDImpl.class);
    
    @Override
    public AccOpeningD save(AccOpeningD aod) {
        persist(aod);
        return aod;
    }
    
    @Override
    public AccOpeningD findById(Long id) {
        AccOpeningD aod = getByKey(id);
        return aod;
    }
    
    @Override
    public List<VAccOpeningD> search(String tranIdH) {
        String strSql = "select o from VAccOpeningD o where o.tranIdH = " + tranIdH;
        List<VAccOpeningD> listVOAD = findHSQLList(strSql);
        return listVOAD;
    }
    
    @Override
    public int delete(String tranId) {
        String strSql = "delete from AccOpeningD o where o.tranId = " + tranId;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
    
    @Override
    public void insertFilter(String coaCode, int level, String opDate,
            String curr, String userId) throws Exception {
        String strFilter = "";
        
        if (!coaCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "coa.coa_code = '" + coaCode + "'";
            } else {
                strFilter = strFilter + " and coa.coa_code = '" + coaCode + "'";
            }
        }
        
        if (!opDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "ifnull(op.op_date, '1900-01-01') <= '"
                        + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "'";
            } else {
                strFilter = strFilter + " and ifnull(op.op_date, '1900-01-01') <= '"
                        + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "'";
            }
        }
        
        if (!curr.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "coa.cur_code = '" + curr + "'";
            } else {
                strFilter = strFilter + " and coa.cur_code = '" + curr + "'";
            }
        }
        
        String strSql = "insert into tmp_op_filter(comp_code, coa_code, op_tran_id_d, "
                + "curr_id, op_date, user_id) "
                + "select coa.comp_code, coa.coa_code, op.tran_id_d, coa.cur_code, "
                + "ifnull(op.op_date, '1900-01-01') op_date,'" + userId + "'"
                + " from (select comp_code, coa_code, cur_code, level\n"
                + "from chart_of_account, currency) coa left join (select m.tran_id_d, m.curr_id, m.op_date, m.coa_code, m.comp_code\n"
                + " from v_coa_opening_by_date m, ("
                + "select a.curr_id, a.coa_code, max(ifnull(a.op_date, '1900-01-01')) op_date "
                + "from v_coa_opening_by_date a group by a.curr_id, a.coa_code) f "
                + "where m.curr_id = f.curr_id and m.coa_code = f.coa_code and m.op_date = f.op_date) op on "
                + "coa.comp_code = op.comp_code and coa.coa_code = op.coa_code and coa.cur_code = op.curr_id "
                + "where coa.comp_code is not null and coa.level >= " + level;
        
        if (!strFilter.isEmpty()) {
            strSql = strSql + " and " + strFilter;
        }

        //strSql = strSql + " group by coa.comp_code, coa.coa_code, op.tran_id_d, op.curr_id";
        execSQL(strSql);
    }
    
    @Override
    public List<TmpOpeningClosing> getOpBalance(String coaCode, int level, String opDate,
            String curr, String userId) throws Exception {
        //deleteTmp(userId);
        /*String strDeleteSql1 = "delete from tmp_op_filter where user_id = '" +
         userId + "'";
         String strDeleteSql2 = "delete from tmp_op_cl where user_id = '" + userId + "'";
        
         execSQL(strDeleteSql1, strDeleteSql2);*/
        insertFilter(coaCode, level, opDate, curr, userId);
        
        String strSql = "insert into tmp_op_cl(coa_code, curr_id, user_id, opening, dr_amt, cr_amt) "
                + "select a.coa_code, a.curr_id, '" + userId + "', sum(ifnull(a.balance,0)), 0, 0 "
                + "from (select tof.comp_code, tof.coa_code, tof.curr_id, aod.ex_rate, aod.dr_amt, "
                + "aod.cr_amt,(ifnull(aod.dr_amt,0)-ifnull(aod.cr_amt,0)) balance,"
                + "ifnull(tof.op_date, date('1900-01-01')) tran_date, 'Opening' tran_option, tof.user_id "
                + "from tmp_op_filter tof, acc_opening_d aod "
                + "where tof.coa_code = aod.coa_code and tof.op_tran_id_d = aod.tran_id "
                + "and tof.curr_id = aod.curr_code and tof.user_id = '" + userId + "' "
                + "union all "
                + "select tof.comp_code, tof.coa_code, tof.curr_id, gl.ex_rate, "
                + "gl.dr_amt, gl.cr_amt, (ifnull(gl.dr_amt,0)-ifnull(gl.cr_amt,0)) balance, "
                + "gl.gl_date tran_date, 'GL' tran_option, tof.user_id "
                + "from tmp_op_filter tof, gl "
                + "where tof.coa_code = source_ac_id and tof.curr_id = from_cur_id "
                + "and gl_date >= tof.op_date and gl_date < '"
                + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd")
                + "' and tof.user_id = '" + userId + "') a group by a.coa_code, a.curr_id";

        //logger.info((strSql));
        execSQL(strSql);
        
        String strHSql = "select o from TmpOpeningClosing o where o.key.userId = '" + userId + "'";
        List<TmpOpeningClosing> listTOC = findHSQLList(strHSql);
        
        return listTOC;
    }
    
    @Override
    public void insertFilterGL(String coaCode, String opDate, int level,
            String curr, String userId) throws Exception {
        String strFilter = "";
        
        if (!coaCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "gl.source_ac_id = '" + coaCode + "'";
            } else {
                strFilter = strFilter + " and gl.source_ac_id = '" + coaCode + "'";
            }
        }
        
        if (!opDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "ifnull(gl_date, '1900-01-01') = '"
                        + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "'";
            } else {
                strFilter = strFilter + " and ifnull(gl_date, '1900-01-01') = '"
                        + Util1.toDateStr(opDate, "dd/MM/yyyy", "yyyy-MM-dd") + "'";
            }
        }
        
        if (!curr.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "from_cur_id = '" + curr + "'";
            } else {
                strFilter = strFilter + " and from_cur_id = '" + curr + "'";
            }
        }
        
        if (strFilter.isEmpty()) {
            strFilter = "tran_source = 'OPENING'";
        } else {
            strFilter = strFilter + " and tran_source = 'OPENING'";
        }
        
        String strSql = "insert into tmp_op_filter(comp_code, coa_code, op_tran_id_d, curr_id, op_date, user_id) "
                + "select gl.comp_code, source_ac_id, gl_id, from_cur_id, gl_date, '" + userId + "' "
                + "from gl, chart_of_account coa where gl.comp_code = coa.comp_code "
                + "and gl.source_ac_id = coa.coa_code and coa.level >= " + level;
        
        if (!strFilter.isEmpty()) {
            strSql = strSql + " and " + strFilter;
        }
        
        execSQL(strSql);
    }
    
    @Override
    public void genOpBalanceGL(String coaCode, String opDate,
            String clDate, int level, String curr, String userId, String dept) throws Exception {
    }
    
    @Override
    public List<TmpOpeningClosing> getOpBalanceGL(String userId, String coaCode) {
        String strHSql = "select o from TmpOpeningClosing o where o.key.userId = '" + userId + "' and o.key.coaId ='" + coaCode + "'";
        List<TmpOpeningClosing> listTOC = findHSQLList(strHSql);
        return listTOC;
    }
    
    @Override
    public void genTriBalance(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userId) throws Exception {
    }
    
    @Override
    public void genArAp(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userId) throws Exception {
    }
    
    @Override
    public void genArAp1(String compCode, String fromDate, String opDate,
            String tranDate, String coaCode, String currency, String dept, String cvId, String userId) throws Exception {
        logger.info("Ar / Ap Start.");
        String delSql = "delete from tmp_op_cl_apar where user_id = '" + userId + "'";
        execSQL(delSql);
        String strSql = "insert into tmp_op_cl_apar(coa_id, curr_id, user_id, cv_id, dept_id, dr_amt, cr_amt)\n"
                + "select vcc.account_code, vcc.cur_code,'1', vcc.id cv_id ,ifnull(a.dept_code, '-') dept_id,\n"
                + "       if(sum(a.dr_amt-a.cr_amt)<0, sum(a.dr_amt-a.cr_amt)*-1,0) dr,\n"
                + "       if(sum(a.dr_amt-a.cr_amt)>0, sum(a.dr_amt-a.cr_amt),0) cr\n"
                + "  from v_trader_curr_dept vcc \n"
                + "  left join (\n"
                + "  select coa.comp_code,coa.op_date,coa.source_acc_id, coa.cur_code, coa.cv_id, coa.dept_code, \n"
                + "			        sum(ifnull(coa.dr_amt,0)) dr_amt, sum(ifnull(coa.cr_amt,0)) cr_amt\n"
                + "			   from coa_opening coa, trader t\n"
                + "               where coa.cv_id = t.id \n"
                + "               group by coa.cv_id \n"
                + "			  union all\n"
                + "			 select g.comp_code,g.gl_date,t.account_code, g.from_cur_id, g.cv_id, g.dept_id,\n"
                + "					sum(get_dr_cr_amt(g.source_ac_id, g.account_id, t.account_code, \n"
                + "					ifnull(g.dr_amt,0), ifnull(g.cr_amt,0), 'DR')) dr_amt,\n"
                + "					sum(get_dr_cr_amt(g.source_ac_id, g.account_id, t.account_code, ifnull(g.dr_amt,0), \n"
                + "                      ifnull(g.cr_amt,0), 'CR')) cr_amt\n"
                + "			   from gl g, trader t\n"
                + "			  where g.cv_id = t.id and g.gl_date <= '" + tranDate + "'\n"
                + "				and (g.comp_code = " + compCode + " or '-' = '" + compCode + "') "
                + "and (g.from_cur_id = '" + currency + "' or '-' = '" + currency + "') and (g.dept_id = '" + dept + "' or '-' = '" + dept + "') \n"
                + "			  group by g.comp_code,g.gl_date,g.comp_code, g.source_ac_id, g.account_id, g.dept_id, g.from_cur_id, \n"
                + "              g.tran_source, g.cv_id, t.id, t.account_code) a\n"
                + "  on vcc.comp_code = a.comp_code and vcc.account_code = a.source_acc_id and vcc.cur_code = a.cur_code\n"
                + "and vcc.id = a.cv_id  and vcc.dept_code = a.dept_code\n"
                + "where (vcc.account_code = '-' or '-' = '-' or vcc.coa_parent = '-') \n"
                + "and (vcc.cur_code = '" + currency + "' or '-' = '" + currency + "') and (a.cr_amt > 0 or a.dr_amt >0) \n"
                + "and (vcc.dept_code = '" + dept + "' or '-' = '" + dept + "')\n"
                + "group by vcc.account_code,vcc.cur_code,vcc.comp_code,vcc.id;";
        execSQL(strSql);
        logger.info("Ar / Ap Finished.");
    }
    
    @Override
    public void genTriBalance1(String compCode, String fromDate, String opDate,
            String tranDate, String coaCode, String currency, String dept,
            String cvId, String userId, String macId) throws Exception {
        deleteTmp(Integer.parseInt(macId));
        String strSqlFilter = "insert into tmp_op_filter(comp_code, coa_code, dept_id, curr_id, tran_source, op_date, user_id,machine_id)"
                + "select vcc.comp_code, vcc.coa_code, ifnull(a.dept_id, '-') dept_id, vcc.cur_code, 'OPENING' as tran_source, ifnull(a.op_date, '1900-01-01') op_date,\n"
                + "'" + userId + "','" + macId + "'  from v_coa_curr vcc left join (\n"
                + "select comp_code, source_acc_id, ifnull(dept_code, '-') as dept_id, cur_code, \n"
                + "ifnull(coa.tran_source, '-') tran_source, max(op_date) as op_date\n"
                + "from coa_opening coa\n"
                + "where coa.op_date = '" + opDate + "' and ifnull(coa.tran_source, '-') = 'OPENING'\n"
                + "and (coa.comp_code = " + compCode + " or '-' = '" + compCode + "') and "
                + "(coa.cur_code = '" + currency + "' or '-' = '" + currency + "') "
                + "and (coa.dept_code = '" + dept + "' or '-' = '" + dept + "') \n"
                + "and (ifnull(coa.cv_id, " + cvId + ")  = -1 or -1 = " + cvId + ")\n"
                + "group by coa.comp_code, coa.source_acc_id, coa.dept_code, coa.cur_code, coa.tran_source) a\n"
                + "on vcc.comp_code = a.comp_code and vcc.coa_code = a.source_acc_id and vcc.cur_code = a.cur_code\n"
                + "where vcc.level >= 3 and (vcc.coa_code = '" + coaCode + "' or '-' = '" + coaCode + "' or vcc.coa_parent = '-') "
                + "and (vcc.cur_code = '" + currency + "' or '-' = '" + currency + "')";
        execSQL(strSqlFilter);
        
        String strSql = "insert into tmp_op_cl(coa_code, curr_id, user_id, dr_amt, cr_amt,machine_id) \n"
                + "select coa_code, curr_id, '" + userId + "', if(sum(dr_amt-cr_amt)>0, sum(dr_amt-cr_amt),0), if(sum(dr_amt-cr_amt)<0, sum(dr_amt-cr_amt)*-1,0), '" + macId + "'\n"
                + "from (\n"
                + "	select tof.coa_code, tof.curr_id,\n"
                + "		   sum(ifnull(op.dr_amt,0)) dr_amt, sum(ifnull(op.cr_amt,0)) cr_amt\n"
                + "	  from tmp_op_filter tof, coa_opening op\n"
                + "	 where tof.comp_code = op.comp_code and tof.curr_id = op.cur_code and \n"
                + "		  op.op_date = '" + opDate + "' and \n"
                + "          tof.coa_code = op.source_acc_id and tof.op_date = op.op_date and "
                + "tof.user_id = '" + userId + "' and (op.dept_code = '" + dept + "' or '-' = '" + dept + "')\n"
                + " and (op.cv_id = " + cvId + " or -1 = " + cvId + ")  \n"
                + " group by tof.coa_code, tof.curr_id "
                + "	 union all\n"
                + "	select tof.coa_code, tof.curr_id, "
                + "	       sum(get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'DR')) dr_amt, "
                + "      sum(get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'CR')) cr_amt\n"
                + "	  from tmp_op_filter tof, gl "
                + "	 where ifnull(gl.tran_source,'-') <> 'OPENING' and gl.gl_date between '" + opDate + "' \n"
                + "      and '" + tranDate + "' and (gl.dept_id = '" + dept + "' or '-' = '" + dept + "')"
                + "        and (gl.cv_id = " + cvId + " or -1 = " + cvId + ") and tof.comp_code = gl.comp_code and (tof.coa_code = gl.source_ac_id or tof.coa_code = gl.account_id) \n"
                + "        and tof.curr_id = gl.from_cur_id and tof.user_id = '" + userId + "'"
                + " group by tof.coa_code, tof.curr_id, gl.source_ac_id, gl.account_id) a \n"
                + "group by coa_code, curr_id";
        execSQL(strSql);
        updatePreviousClosing(opDate, tranDate, userId, dept);
    }
    
    @Override
    public void genOpBalanceGL1(String coaCode, String opDate, String clDate,
            int level, String curr, String userId, String dept, String macId) throws Exception {
        deleteTmp(Integer.parseInt(macId));
        String insertSql = "insert into tmp_op_filter(comp_code, coa_code, op_tran_id_d, curr_id, op_date, user_id,machine_id) \n"
                + "select coa.comp_code,coa.coa_code,op.coa_op_id,ifnull(op.cur_code,'" + curr + "'),op.op_date,'" + userId + "'," + macId + "\n"
                + "from chart_of_account coa left join coa_opening op \n"
                + "on coa.coa_code = op.source_acc_id and coa.comp_code = op.comp_code\n"
                + "where coa.coa_code = '" + coaCode + "'"
                + "group by coa.coa_code";
        execSQL(insertSql);
        String opSql = "insert into tmp_op_cl(coa_code, curr_id, user_id, opening, dr_amt, cr_amt,machine_id) \n"
                + "select coa_code, curr_id, '" + userId + "', sum(balance), 0, 0," + macId + "\n"
                + "from (\n"
                + "select tof.coa_code, tof.curr_id, ifnull(coa.dr_amt,0)-ifnull(coa.cr_amt,0) balance,\n"
                + "		ifnull(coa.dr_amt,0) dr_amt, ifnull(coa.cr_amt,0) cr_amt,tof.cv_id\n"
                + "	from tmp_op_filter tof, coa_opening coa\n"
                + "	where  tof.comp_code = coa.comp_code and tof.curr_id = coa.cur_code \n"
                + "		and tof.coa_code = coa.source_acc_id \n"
                + "		and tof.user_id = '" + userId + "' and tof.coa_code = '" + coaCode + "'"
                + "             and (coa.dept_code = '" + dept + "' or '-' = '" + dept + "')\n"
                + "union all\n"
                + "select tof.coa_code, tof.curr_id, get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, \n"
                + "		ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'DR')-get_dr_cr_amt(gl.source_ac_id, \n"
                + "             gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'CR') balance, \n"
                + "		ifnull(gl.dr_amt,0) dr_amt, ifnull(gl.cr_amt,0),tof.cv_id \n"
                + "     from tmp_op_filter tof, gl\n"
                + "	where tof.comp_code = gl.comp_code and (tof.coa_code = gl.source_ac_id or \n"
                + "             tof.coa_code = gl.account_id) and \n"
                + "		tof.curr_id = gl.from_cur_id and gl.gl_date >= '" + opDate + "' "
                + "             and gl.gl_date < '" + Util1.toDateStrMYSQL(clDate, "dd/MM/yyyy") + "' \n"
                + "		and tof.user_id = '" + userId + "' and (gl.dept_id = '" + dept + "' or '-' = '" + dept + "')"
                + "             and tof.coa_code = '" + coaCode + "') a \n"
                + "group by a.coa_code, a.curr_id\n";
        execSQL(opSql);
    }
    
    private void updatePreviousClosing(String opDate, String clDate, String userId, String dept) throws Exception {
        String updateSql = "update tmp_op_cl cl, (\n"
                + "select coa_code, curr_id, '" + userId + "', sum(balance) balance, 0, 0 as opening \n"
                + "from (\n"
                + "select tof.coa_code, tof.curr_id, ifnull(coa.dr_amt,0)-ifnull(coa.cr_amt,0) balance,\n"
                + "		ifnull(coa.dr_amt,0) dr_amt, ifnull(coa.cr_amt,0) cr_amt,tof.cv_id\n"
                + "	from tmp_op_filter tof, coa_opening coa\n"
                + "	where  tof.comp_code = coa.comp_code and tof.curr_id = coa.cur_code \n"
                + "		and tof.coa_code = coa.source_acc_id \n"
                + "		and tof.user_id = '" + userId + "'\n"
                + "        and (coa.dept_code = '" + dept + "' or '-' = '" + dept + "')\n"
                + "union all\n"
                + "select tof.coa_code, tof.curr_id, get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, \n"
                + "		ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'DR')-get_dr_cr_amt(gl.source_ac_id, \n"
                + "             gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'CR') balance, \n"
                + "		ifnull(gl.dr_amt,0) dr_amt, ifnull(gl.cr_amt,0),tof.cv_id \n"
                + "     from tmp_op_filter tof, gl\n"
                + "	where tof.comp_code = gl.comp_code and (tof.coa_code = gl.source_ac_id or \n"
                + "             tof.coa_code = gl.account_id) and \n"
                + "		tof.curr_id = gl.from_cur_id and gl.gl_date >= '" + opDate + "'\n"
                + "        and gl.gl_date < '" + clDate + "' \n"
                + "		and tof.user_id = '" + userId + "' and (gl.dept_id = '" + dept + "' or '-' = '" + dept + "')) a \n"
                + "group by a.coa_code, a.curr_id\n"
                + ") op\n"
                + "set cl.closing = op.balance\n"
                + "where cl.coa_code = op.coa_code;";
        execSQL(updateSql);
    }
    
    @Override
    public void getOpBalanceByTrader(String coaCode, String opDate, String clDate,
            int level, String curr, String userId, String dept, String cvId, String macId) throws Exception {
        deleteTmp(Integer.parseInt(macId));
        String insertSql = "insert into tmp_op_filter(comp_code, coa_code, op_tran_id_d, curr_id, op_date, user_id,cv_id,machine_id) \n"
                + "select coa.comp_code,coa.coa_code,op.coa_op_id,ifnull(op.cur_code,'" + curr + "'),op.op_date,'" + userId + "',op.cv_id," + macId + "\n"
                + "from chart_of_account coa left join coa_opening op \n"
                + "on coa.coa_code = op.source_acc_id\n"
                + "where coa.coa_code = '" + coaCode + "'";
        execSQL(insertSql);
        String strSql = "insert into tmp_op_cl(coa_code, curr_id, user_id, opening, dr_amt, cr_amt,machine_id) \n"
                + "select coa_code, curr_id, '" + userId + "', sum(balance), 0, 0," + macId + " \n"
                + "from (\n"
                + "select tof.coa_code, tof.curr_id, ifnull(coa.dr_amt,0)-ifnull(coa.cr_amt,0) balance,\n"
                + "ifnull(coa.dr_amt,0) dr_amt, ifnull(coa.cr_amt,0) cr_amt,tof.cv_id\n"
                + "from tmp_op_filter tof, coa_opening coa\n"
                + "where tof.op_tran_id_d = coa.coa_op_id and tof.comp_code = coa.comp_code and tof.curr_id = coa.cur_code and \n"
                + "tof.coa_code = coa.source_acc_id and tof.op_date = coa.op_date\n"
                + " and tof.user_id = '" + userId + "' and (coa.dept_code = '" + dept + "' or '-' = '" + dept + "'"
                + "and tof.coa_code = '" + coaCode + "') and coa.cv_id = " + cvId + "\n"
                + "union all\n"
                + "select tof.coa_code, tof.curr_id, get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'DR')-\n"
                + "get_dr_cr_amt(gl.source_ac_id, gl.account_id, tof.coa_code, ifnull(gl.dr_amt,0), ifnull(gl.cr_amt,0), 'CR') balance, \n"
                + "ifnull(gl.dr_amt,0) dr_amt, ifnull(gl.cr_amt,0),tof.cv_id \n"
                + "from tmp_op_filter tof, gl\n"
                + "where tof.comp_code = gl.comp_code and tof.cv_id = gl.cv_id\n"
                + "and (tof.coa_code = gl.source_ac_id or tof.coa_code = gl.account_id) "
                + "and ifnull(gl.tran_source,'-') <> 'OPENING' and \n"
                + "tof.curr_id = gl.from_cur_id and gl.gl_date >= '" + opDate + "' and gl.gl_date < '"
                + Util1.toDateStrMYSQL(clDate, "dd/MM/yyyy")
                + "' and tof.user_id = '" + userId + "' and (gl.dept_id = '" + dept + "' or '-' = '" + dept + "')"
                + "and tof.coa_code = '" + coaCode + "' and tof.cv_id ='" + cvId + "') a \n"
                + "group by coa_code, curr_id";
        execSQL(strSql);
    }
    
    private void deleteTmp(Integer machineId) throws Exception {
        String delSql = "delete from tmp_op_filter where machine_id = " + machineId + "";
        String delSql1 = "delete from tmp_op_cl where machine_id =" + machineId + "";
        execSQL(delSql, delSql1);
    }
}
