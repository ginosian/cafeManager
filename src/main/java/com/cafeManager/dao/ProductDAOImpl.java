package com.cafeManager.dao;

import com.cafeManager.dto.ProductDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Martha on 7/29/2016.
 */
@Resource
@Transactional
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        return null;
    }
}
