package com.cafeManager.service;

import com.cafeManager.dao.TableDAO;
import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Service
@Transactional
public class TableServiceImpl implements TableService{

    @Autowired
    TableDAO tableDAO;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Override
    public TableDTO createTable() {
        return tableDAO.addTable(new TableDTO());
    }

    @Override
    public TableDTO getTableById(String tableId) throws NoSuchTableException, NullOrEmptyArgumentsException {
        if(tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(Long.parseLong(tableId));
        if(tableDTO == null)throw new NoSuchTableException();

        return tableDTO;
    }

    @Override
    public List<TableDTO> getAllTables() throws NoSuchTableException {
        List<TableDTO> tables = tableDAO.getAllTables();
        if(tables.size() < 1) throw new NoSuchTableException();

        return tables;
    }

    @Override
    public TableDTO updateTableWIthWaiter(String userId, String tableId) throws NoSuchUserException, NoSuchTableException, NullOrEmptyArgumentsException {
        if(userId == null || userId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTableById(tableId);
        UserDTO userDTO = userService.getUserById(userId);

        tableDTO.setUser(userDTO);
        tableDAO.updateTable(tableDTO);

        return tableDTO;
    }

    @Override
    public TableDTO updateTableWIthOrder(String orderId, String tableId) throws NoSuchOrderException, NoSuchTableException,
            TableHasActiveOrderException, NullOrEmptyArgumentsException {
        if(orderId == null || orderId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTableById(tableId);
        if(tableDTO.getOrder().isActive()) throw new TableHasActiveOrderException();

        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if(orderDTO.isActive()) throw new NoSuchOrderException();

        tableDTO.setOrder(orderDTO);
        tableDAO.updateTable(tableDTO);

        orderDTO.setIsActive(true);
        orderService.updateOrder(orderDTO);

        return tableDTO;
    }

    @Override
    public TableDTO deactivateTableOrder(String tableId) throws NoSuchTableException, NoSuchOrderException, TableHasNoActiveOrderException, NullOrEmptyArgumentsException {
        if(tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTableById(tableId);

        OrderDTO orderDTO = tableDTO.getOrder();
        if(orderDTO == null || !orderDTO.isActive())throw new TableHasNoActiveOrderException();

        orderDTO.setIsActive(false);
        orderService.updateOrder(orderDTO);

        return tableDTO;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(String userId) throws NoSuchTableException, NoSuchUserException, NullOrEmptyArgumentsException {
        if(userId == null || userId.isEmpty()) throw new NullOrEmptyArgumentsException();

        UserDTO userDTO = userService.getUserById(userId);

        List<TableDTO> tables = tableDAO.getTablesByWaiter(userDTO.getId());
        if(tables.size() == 0)throw new NoSuchTableException();

        return tables;
    }
}
