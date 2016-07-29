package com.cafeManager.dao;

import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.ProductInOrderDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface OrderDAO {
    /** Creates new order */
    OrderDTO addOrder(OrderDTO orderDTO);

    /** Finds order with specified Id */
    OrderDTO getOrder(Long orderId);

    /** Update order details */
    OrderDTO updateOrder(OrderDTO orderDTO);

    /** Gets all products list within specified order */
    List<ProductInOrderDTO> getOrderProducts(Long orderId);

    /** Creates order-adapted ProductInOrder */
    ProductInOrderDTO addProductToOrder(ProductInOrderDTO productInOrderDTO);

    /** Updates order-adapted ProductInOrder */
    ProductInOrderDTO updateProductToOrder(ProductInOrderDTO productInOrderDTO);

    /** Finds specified order-adapted ProductInOrder from ProductInOrder table*/
    ProductInOrderDTO getProductInOrder(Long productInOrderId);
}