package com.cafeManager.service;

import com.cafeManager.dao.TableDAO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.exception.NullOrEmptyArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Service
public class TableServiceImpl implements TableService{

    @Autowired
    TableDAO tableDAO;

    @Override
    public TableDTO createTable(TableDTO table) throws NullOrEmptyArgumentsException{
        if(table == null) throw new NullOrEmptyArgumentsException();
        return tableDAO.addTable(table);
    }

    @Override
    public TableDTO getTable(Long tableId) throws NullOrEmptyArgumentsException{
        if(tableId == null || tableId == 0) throw new NullOrEmptyArgumentsException();
        return tableDAO.getTable(tableId);
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) throws NullOrEmptyArgumentsException{
        if(tableDTO == null) throw new NullOrEmptyArgumentsException();
        if(getTable(tableDTO.getId()) != null) return null;
        return tableDAO.updateTable(tableDTO);
    }

    @Override
    public List<TableDTO> getTablesByWaiter(Long userId) throws NullOrEmptyArgumentsException{
        return null;
    }
}
