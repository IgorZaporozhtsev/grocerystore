package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Product;
import com.bymdev.grocerystore.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    public com.bymdev.grocerystore.domain.Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("There is no such Entity"));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Integer id, Product product) {
        Product retrivedProduct = productRepository.getReferenceById(id);
        retrivedProduct.setProductName(product.getProductName());
        retrivedProduct.setPrice(product.getPrice());
        retrivedProduct.setSku(product.getSku());
        return productRepository.save(retrivedProduct);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
