package com.chrosciu.shop.products;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
}
