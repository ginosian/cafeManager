package com.cafeManager.service;

import com.cafeManager.dao.TableDAO;
import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.*;
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

    @Autowired
    UserDTO userDTO;

    @Autowired
    OrderDTO orderDTO;

    @Override
    public TableDTO createTable() {
        return null;
    }

    @Override
    public TableDTO getTable(String tableId) throws NoSuchTableException, NullOrEmptyArgumentsException {
        return null;
    }

    @Override
    public TableDTO updateTableWIthWaiter(String userId, String tableId) throws NoSuchUserException, TableAlreadyHasWaiter, NullOrEmptyArgumentsException {
        return null;
    }

    @Override
    public TableDTO updateTableWIthOrder(String orderId) throws NoSuchOrderException, TableHasActiveOrderException, NullOrEmptyArgumentsException {
        return null;
    }

    @Override
    public TableDTO deactivateTableOrder() throws TableHasNoActiveOrderException {
        return null;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(String userId) throws NoSuchTableException, NoSuchUserException, NullOrEmptyArgumentsException {
        return null;
    }
}
