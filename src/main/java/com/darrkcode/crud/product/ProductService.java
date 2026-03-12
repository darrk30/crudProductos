package com.darrkcode.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> ObtenerProductos(){
        return  this.productRepository.findAll();
    }

    public ResponseEntity<Object> RegistrarOrActualizarProducto(Product product) {
        Optional<Product> res = productRepository.findProductByNombre(product.getNombre());
        HashMap<String, Object> datos = new HashMap<>();
        if (res.isPresent() && product.getId() == null) {
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        Product savedProduct = productRepository.save(product);
        if (product.getId() != null){
            datos.put("message", "Producto actualizado");
        }else{
            datos.put("message", "Producto registrado");
        }
        datos.put("data", savedProduct);

        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> EliminarProducto(Long id){
        boolean exite = this.productRepository.existsById(id);
        HashMap<String, Object> datos = new HashMap<>();
        if (!exite){
            datos.put("error", true);
            datos.put("message", "El producto no exite");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }
        datos.put("message", "El producto eliminado");
        this.productRepository.deleteById(id);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}
