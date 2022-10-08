package kr.lamgda.product.service;

import kr.lamgda.product.entity.Product;
import kr.lamgda.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void write(Product product) {
        repository.save(product);
    }

    public List<Product> getList() {
        return repository.findAll();
    }

    public Product getData(int id) {
        return repository.findById(id).get();
    }
}
