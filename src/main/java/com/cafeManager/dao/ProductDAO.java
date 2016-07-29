package com.cafeManager.dao;

import com.cafeManager.dto.ProductDTO;

/**
 * Created by Martha on 7/29/2016.
 */
public interface ProductDAO {

    /** Creates new product */
    ProductDTO addProduct(ProductDTO productDTO);

    /** Finds product with specified Id */
    ProductDTO getProduct(Long productId);
}
