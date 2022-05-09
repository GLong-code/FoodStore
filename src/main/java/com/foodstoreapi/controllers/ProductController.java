package com.foodstoreapi.controllers;

import com.foodstoreapi.models.Product;
import com.foodstoreapi.models.ResponseObject;
import com.foodstoreapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    //this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
       return repository.findAll();
    }

//    Find by food class
    @GetMapping("/FoodClass/{foodClass}")
    List<Product> findByFoodClass(@PathVariable int foodClass){
        List<Product> foundClass = repository.findByFoodClass(foodClass);
        return foundClass;

    }

    @GetMapping("/name/{name}")
//    This request is: http://localhost:8080/api/v1/Products/name/Lobster
    List<Product> findByName(@PathVariable String name){
        List<Product> foundName = repository.findByName(name);
        return foundName;
    }


    @GetMapping("/expiry")
    @ResponseBody
//   This request is:  http://localhost:8080/api/v1/Products/expiry?expiry=10/12/2022
    List<Product> findByExpiry(@RequestParam String expiry) {
        List<Product> foundFood = repository.findByExpiry(expiry);
        return foundFood;
    }

    //Get detail product
    @GetMapping("/{id}")
    //Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", foundProduct)
                        //you can replace "ok" with your defined "error code"
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = "+id, "")
                );
    }

    //insert new Product with POST method
    //Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //2 products must not have the same name !
        System.out.println(newProduct.toString());
        List<Product> foundProducts = repository.findByName(newProduct.getName().trim());
        if(foundProducts.size() > 0) {
            System.out.println("run here");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "Product name already taken", "")
            );
        }
        System.out.println("end");
        return ResponseEntity.status(HttpStatus.OK).body(
           new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
        );
    }


    //update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setAttribute(newProduct.getAttribute());
                    product.setFoodClass(newProduct.getFoodClass());
                    product.setQuantity(newProduct.getQuantity());
                    product.setAttribute(newProduct.getAttribute());
                    product.setExpiry(newProduct.getExpiry());
                    product.setImportDay(newProduct.getImportDay());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedProduct)
        );
    }
    
    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
