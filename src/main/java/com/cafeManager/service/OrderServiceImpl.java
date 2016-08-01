package com.cafeManager.service;

import com.cafeManager.dao.OrderDAO;
import com.cafeManager.dto.*;
import com.cafeManager.exception.*;
import com.cafeManager.util.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Martha on 7/31/2016.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ProductService productService;

    @Autowired
    TableService tableService;

    @Autowired
    UserService userService;

    @Override
    public OrderDTO createOrder() {
        return orderDAO.addOrder(new OrderDTO());
    }

    @Override
    public OrderDTO getOrderById(String orderId) throws NoSuchOrderException, NullOrEmptyArgumentsException {
        if(!isValid(orderId)) throw new NullOrEmptyArgumentsException();

        OrderDTO orderDTO = orderDAO.getOrder(Long.parseLong(orderId));
        if(orderDTO == null) throw new NoSuchOrderException();

        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws NoSuchOrderException, NullOrEmptyArgumentsException {
        if(orderDTO == null) throw new NullOrEmptyArgumentsException();

        OrderDTO order = orderDAO.getOrder(orderDTO.getId());
        if(order == null) throw new NoSuchOrderException();

        return orderDAO.updateOrder(orderDTO);
    }

    @Override
    public ConcurrentHashMap<TableDTO, OrderDTO> getOrdersByWaiter(String waiterId) throws NoSuchUserException, NoSuchTableException, NullOrEmptyArgumentsException {
        if(!isValid(waiterId)) throw new NullOrEmptyArgumentsException();

        List<TableDTO> assignedTables = tableService.getTablesByWaiter(waiterId);
        ConcurrentHashMap<TableDTO, OrderDTO> tablesMap = new ConcurrentHashMap<TableDTO, OrderDTO>();
        for (int i = 0; i < assignedTables.size(); i++) {
            TableDTO tableDTO = assignedTables.get(i);
            OrderDTO orderDTO = tableDTO.getOrder();
            if(orderDTO != null && orderDTO.isActive()){
                tablesMap.put(tableDTO, orderDTO);
            } else {
                Nullable<OrderDTO> nullOrder = new Nullable<OrderDTO>(new OrderDTO());
                nullOrder.setMessage("No orders for this table");
                tablesMap.put(tableDTO, nullOrder.get());
            }
        }
        return tablesMap;
    }

    @Override
    public ProductInOrderDTO addProductInOrder(String orderId, String productId, int productAmount) throws NoSuchOrderException, NoSuchProductException, NullOrEmptyArgumentsException {
        if(!isValid(orderId) || !isValid(productId) || productAmount <= 0) throw new NullOrEmptyArgumentsException();

        OrderDTO orderDTO = getOrderById(orderId);
        ProductDTO productDTO = productService.getProduct(productId);

        return orderDAO.addProductToOrder(new ProductInOrderDTO(productDTO, orderDTO, productAmount, true));
    }

    @Override
    public ProductInOrderDTO changeProductAmountInOrder(String productId, int updatedAmount) throws NoSuchProductException, NullOrEmptyArgumentsException {
        if(!isValid(productId) || updatedAmount <= 0)throw new NullOrEmptyArgumentsException();

        ProductInOrderDTO productInOrderDTO = orderDAO.getProductInOrder(Long.parseLong(productId));
        if(productInOrderDTO == null) throw new NoSuchProductException();

        productInOrderDTO.setAmount(updatedAmount);
        return orderDAO.updateProductToOrder(productInOrderDTO);
    }

    @Override
    public void deleteProductInOrder(String productId) throws NoSuchOrderException, NoSuchProductException, NullOrEmptyArgumentsException {
        if(!isValid(productId)) throw new NullOrEmptyArgumentsException();

        Long id = Long.parseLong(productId);
        ProductInOrderDTO productInOrderDTO = orderDAO.getProductInOrder(id);
        if(productInOrderDTO == null) throw new NoSuchProductException();

        orderDAO.deleteProductToOrder(productInOrderDTO);
    }

    private boolean isValid(String argument){
        return !(argument == null || argument.isEmpty());
    }
}
