package com.cafeManager.dao;

import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.ProductInOrderDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Resource
@Transactional
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<ProductInOrderDTO> getOrderProducts(Long orderId) {
        return null;
    }

    @Override
    public ProductInOrderDTO addProductToOrder(ProductInOrderDTO productInOrderDTO) {
        return null;
    }

    @Override
    public ProductInOrderDTO updateProductToOrder(ProductInOrderDTO productInOrderDTO) {
        return null;
    }

    @Override
    public ProductInOrderDTO getProductInOrder(Long productInOrderId) {
        return null;
    }
}
