package com.cafeManager.service;

import com.cafeManager.dao.TableDAO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.exception.EmptyArgumentsException;
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
    public TableDTO createTable(TableDTO table) {
        if(table == null) throw new EmptyArgumentsException();
        return tableDAO.addTable(table);
    }

    @Override
    public TableDTO getTable(Long tableId) {
        if(tableId == null || tableId == 0) throw new EmptyArgumentsException();
        return tableDAO.getTable(tableId);
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) {
        if(tableDTO == null) throw new EmptyArgumentsException();
        if(getTable(tableDTO.getId()) != null) return null;
        return tableDAO.updateTable(tableDTO);
    }

    @Override
    public List<TableDTO> getTablesByWaiter(Long userId) {
        return null;
    }
}
