package com.cafeManager.service;

import com.cafeManager.dto.ProductDTO;
import com.cafeManager.exception.NoSuchProductException;
import com.cafeManager.exception.NullOrEmptyArgumentsException;
import com.cafeManager.exception.ProductExistException;

import java.util.List;

/**
 * Created by Martha on 7/31/2016.
 */
public interface ProductService {

    /**
     * Creates product.
     * @param productName Name of product.
     * @return {@link ProductDTO}
     * @throws ProductExistException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductDTO createProduct(String productName) throws ProductExistException, NullOrEmptyArgumentsException;

    /**
     * Finds product by specified Id.
     * @param productId String representation of product id.
     * @return {@link ProductDTO}
     * @throws NoSuchProductException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductDTO getProduct(String productId) throws NoSuchProductException, NullOrEmptyArgumentsException;

    /**
     * Finds all existing products.
     * @return {@link List <ProductDTO>}
     * @throws NoSuchProductException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    List<ProductDTO> getAllProducts() throws NoSuchProductException, NullOrEmptyArgumentsException;


}
