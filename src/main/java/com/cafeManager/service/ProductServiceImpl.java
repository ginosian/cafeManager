package com.cafeManager.service;

import com.cafeManager.dao.ProductDAO;
import com.cafeManager.dto.ProductDTO;
import com.cafeManager.exception.NoSuchProductException;
import com.cafeManager.exception.NullOrEmptyArgumentsException;
import com.cafeManager.exception.ProductExistException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Martha on 7/31/2016.
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public ProductDTO createProduct(String productName) throws ProductExistException, NullOrEmptyArgumentsException {
        if(productName == null || productName.isEmpty()) throw new NullOrEmptyArgumentsException();

        if(productDAO.getProductByName(productName) != null) throw new ProductExistException();

        return productDAO.addProduct(new ProductDTO(productName));
    }

    @Override
    public ProductDTO getProduct(String productId) throws NoSuchProductException, NullOrEmptyArgumentsException {
        if(productId == null || productId.isEmpty()) throw new NullOrEmptyArgumentsException();

        ProductDTO productDTO = productDAO.getProductById(Long.parseLong(productId));
        if(productDTO == null) throw new NoSuchProductException();

        return productDTO;
    }
}
