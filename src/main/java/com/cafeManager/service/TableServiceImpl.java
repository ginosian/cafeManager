package com.cafeManager.service;

import com.cafeManager.dao.OrderDAO;
import com.cafeManager.dao.TableDAO;
import com.cafeManager.dao.UserDAO;
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
    UserDAO userDAO;

    @Autowired
    OrderDAO orderDAO;

    @Override
    public TableDTO createTable() {
        return tableDAO.addTable(new TableDTO());
    }

    @Override
    public TableDTO getTable(String tableId) throws NoSuchTableException, NullOrEmptyArgumentsException {
        if(tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(Long.parseLong(tableId));
        if(tableDTO == null)throw new NoSuchTableException();

        return tableDTO;
    }

    @Override
    public TableDTO updateTableWIthWaiter(String userId, String tableId) throws NoSuchUserException, NoSuchTableException, NullOrEmptyArgumentsException {
        if(userId == null || userId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(Long.parseLong(tableId));
        if(tableDTO == null) throw new NoSuchTableException();

        UserDTO userDTO = userDAO.getUserById(Long.parseLong(userId));
        if(userDTO == null)throw new NoSuchUserException();

        tableDTO.setUser(userDTO);
        tableDAO.updateTable(tableDTO);

        return tableDTO;
    }

    @Override
    public TableDTO updateTableWIthOrder(String orderId, String tableId) throws NoSuchOrderException, NoSuchTableException,
            TableHasActiveOrderException, NullOrEmptyArgumentsException {
        if(orderId == null || orderId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        OrderDTO orderDTO = orderDAO.getOrder(Long.parseLong(orderId));
        if(orderDTO == null || orderDTO.isActive()) throw new NoSuchOrderException();

        TableDTO tableDTO = tableDAO.getTable(Long.parseLong(tableId));
        if(tableDTO == null) throw new NoSuchTableException();

        if(tableDTO.getOrder().isActive()) throw new TableHasActiveOrderException();

        tableDTO.setOrder(orderDTO);
        orderDTO.setIsActive(true);
        orderDAO.updateOrder(orderDTO);

        return tableDTO;
    }

    @Override
    public TableDTO deactivateTableOrder(String tableId) throws NoSuchTableException, TableHasNoActiveOrderException, NullOrEmptyArgumentsException {
        if(tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(Long.parseLong(tableId));
        if(tableDTO == null) throw new NoSuchTableException();

        if(tableDTO.getOrder() == null || !tableDTO.getOrder().isActive())throw new TableHasNoActiveOrderException();

        tableDTO.getOrder().setIsActive(false);
        return tableDTO;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(String userId) throws NoSuchTableException, NoSuchUserException, NullOrEmptyArgumentsException {
        if(userId == null || userId.isEmpty()) throw new NullOrEmptyArgumentsException();

        UserDTO userDTO = userDAO.getUserById(Long.parseLong(userId));
        if(userDTO == null)throw new NoSuchUserException();

        List<TableDTO> tables = tableDAO.getTablesByWaiter(userDTO.getId());
        if(tables.size() == 0)throw new NoSuchTableException();

        return tables;
    }
}
