package com.cafeManager.dao;

import com.cafeManager.dto.TableDTO;
import com.cafeManager.dto.UserDTO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Repository
@Transactional
public class TableDAOImpl implements TableDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TableDTO addTable(TableDTO table) {
        Session session = getSession();
        try{
            session.save(table);
            return table;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TableDTO getTable(Long tableId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from TableDTO tableDTO where tableDTO.id = :id");
            query.setParameter("id", tableId);
            if (query.list().size() < 1) return null;
            TableDTO tableDTO = (TableDTO)query.list().get(0);
            return tableDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) {
        Session session = getSession();
        try{
            session.update(tableDTO);
            return tableDTO;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(Long userId) {
        return null;
    }
}
