package com.cafeManager.dao;

import com.cafeManager.dto.TableDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Resource
@Transactional
public class TableDAOImpl implements TableDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TableDTO addTable(TableDTO table) {
        return null;
    }

    @Override
    public TableDTO getTable(Long tableId) {
        return null;
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) {
        return null;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(Long userId) {
        return null;
    }
}
