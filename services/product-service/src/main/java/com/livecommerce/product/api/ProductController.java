package com.livecommerce.product.api;

import com.livecommerce.product.domain.Product;
import com.livecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ✅ Get All Products / Search / Filter
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search
    ) {
        if (category != null) {
            return ResponseEntity.ok(productService.getProductsByCategory(category));
        } else if (search != null) {
            return ResponseEntity.ok(productService.searchProducts(search));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ Create Product with Image (Form-Data)
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("category") String category,
            @RequestParam("live") boolean live,
            @RequestParam("stock") Integer stock,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {

        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .live(live)
                .stock(stock)
                .build();

        Product savedProduct = productService.createProduct(product, imageFile);
        return ResponseEntity.ok(savedProduct);
    }

    // ✅ Update Product with Image (Form-Data)
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("category") String category,
            @RequestParam("live") boolean live,
            @RequestParam("stock") Integer stock,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {

        Product updatedProductObj = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .live(live)
                .stock(stock)
                .build();

        Product updated = productService.updateProduct(id, updatedProductObj, imageFile);
        return ResponseEntity.ok(updated);
    }

    // ✅ Get Product by Id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ✅ Get Only Live Products
    @GetMapping("/live")
    public ResponseEntity<List<Product>> getLiveProducts() {
        return ResponseEntity.ok(productService.getLiveProducts());
    }

    // ✅ Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // ✅ Reduce Stock
    @PutMapping("/{productId}/reduce-stock")
    public ResponseEntity<Void> reduceStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        productService.reduceStock(productId, quantity);
        return ResponseEntity.ok().build();
    }
}
