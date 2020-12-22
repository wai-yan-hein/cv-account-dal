package com.cv.account.inv.dao;


import com.cv.account.api.dao.AbstractDao;
import com.cv.account.inv.entity.FileSize;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class FileSizeDaoImpl extends AbstractDao<Integer, FileSize> implements FileSizeDao {

    @Override
    public FileSize save(FileSize fs) {
        persist(fs);
        return fs;
    }

    @Override
    public FileSize findById(Integer id) {
        FileSize fs = getByKey(id);
        return fs;
    }

    @Override
    public List<FileSize> search(String queue) {
        String strFilter = "";

        if (!queue.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.queue = '" + queue + "'";
            } else {
                strFilter = strFilter + " and o.queue = '" + queue + "'";
            }
        }

        

        if (strFilter.isEmpty()) {
            strFilter = "select o from FileSize o";
        } else {
            strFilter = "select o from FileSize o where " + strFilter;
        }

        List<FileSize> listFS = findHSQL(strFilter);
        return listFS;
    }

    @Override
    public int delete(String id) {
        String strSql = "delete from FileSize o where o.queue = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
