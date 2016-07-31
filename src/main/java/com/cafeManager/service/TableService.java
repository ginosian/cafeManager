package com.cafeManager.service;

import com.cafeManager.dto.TableDTO;
import com.cafeManager.exception.*;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface TableService {

    /**
     * Creates new empty (without waiter and order) Table.
     * @return {@link TableDTO}
     * */
    TableDTO createTable();

    /**
     * Finds specified Table.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO getTable(String tableId)throws NoSuchTableException, NullOrEmptyArgumentsException;

    /**
     * Updates specified Table. Checks if table doesn't exist returns null.
     * @param userId String representation of assignee waiter id.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO updateTableWIthWaiter(String userId, String tableId)throws NoSuchUserException, NoSuchTableException, NullOrEmptyArgumentsException;

    /**
     * Assignees specified Table with new order and activates order.
     * @param orderId String representation of order id.
     * @return {@link TableDTO}
     * @throws NoSuchOrderException if order doesn't exist. First an order should be created.
     * @throws NoSuchTableException if table doesn't exist.
     * @throws TableHasActiveOrderException Table already has active order so can not accept new one.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO updateTableWIthOrder(String orderId, String tableId)throws NoSuchOrderException, NoSuchTableException, TableHasActiveOrderException, NullOrEmptyArgumentsException;

    /**
     * Updates specified Table. Checks if table doesn't exist returns null.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if table doesn't exist.
     * @throws TableHasNoActiveOrderException Table has no active order so can nothing to deactivate.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO deactivateTableOrder(String tableId)throws NoSuchTableException, TableHasNoActiveOrderException, NullOrEmptyArgumentsException;

    /**
     * Gets those Tables which are assigned to specified waiter. Checks if id is empty or 0 throws EmptyArgumentException.
     * @param userId String representation of assignee waiter id.
     * @return {@link List<TableDTO>}
     * @throws NoSuchTableException if waiter doesnt have tables assigned.
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    List<TableDTO> getTablesByWaiter(String userId)throws NoSuchTableException, NoSuchUserException, NullOrEmptyArgumentsException;
}
