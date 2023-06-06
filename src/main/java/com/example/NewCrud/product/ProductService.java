package com.example.NewCrud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    HashMap<String, Object> datos;
    private final ProductRepository productRepository;

    @Autowired
    public  ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {

        Optional<Product> res = productRepository.findProductByNombre(product.getNombre());
        datos = new HashMap<>();

        if(res.isPresent() && product.getId()==null){
            datos.put("Error",true);
            datos.put("Message","Este producto ya existe..");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("Message","Producto guardado con éxito");
        if (product.getId()!=null){
            datos.put("Message","Producto actualizado con éxito");
        }
        productRepository.save(product);
        datos.put("data", product);

        return new ResponseEntity<>(
             datos,
             HttpStatus.CREATED
        );
    }
    public ResponseEntity<Object> deleteProduct(Long id){
        datos = new HashMap<>();
        boolean existe= this.productRepository.existsById(id);
        if(!existe){
            datos.put("Error",true);
            datos.put("Message","No existe un producto con ese id..");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productRepository.deleteById(id);
        datos.put("Message","Producto eliminado con exito..");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

}
