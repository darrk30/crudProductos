package com.darrkcode.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductos(){
        return this.productService.ObtenerProductos();
    }

    @PostMapping
    public ResponseEntity<Object> RegistrarProducto(@RequestBody Product product){
        return this.productService.RegistrarOrActualizarProducto(product);
    }

    @PutMapping
    public ResponseEntity<Object> ActualizarProducto(@RequestBody Product product){
        return this.productService.RegistrarOrActualizarProducto(product);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> EliminarProducto(@PathVariable("id") Long id){
        return this.productService.EliminarProducto(id);
    }


}
