package com.cafeManager.service;

import com.cafeManager.dto.TableDTO;
import com.cafeManager.exception.NullOrEmptyArgumentsException;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface TableService {

    /**
     * Creates new Table.
     */
    TableDTO createTable(TableDTO table) throws NullOrEmptyArgumentsException;

    /**
     * Finds specified Table. Checks if table id is 0 or null throws EmptyArgumentException
     */
    TableDTO getTable(Long tableId)throws NullOrEmptyArgumentsException;

    /**
     * Updates specified Table. Checks if table doesn't exist returns null.
     */
    TableDTO updateTable(TableDTO tableDTO)throws NullOrEmptyArgumentsException;

    /**
     * Gets those Tables which are assigned to specified waiter. Checks if id is empty or 0 throws EmptyArgumentException.
     */
    List<TableDTO> getTablesByWaiter(Long userId)throws NullOrEmptyArgumentsException;
}
