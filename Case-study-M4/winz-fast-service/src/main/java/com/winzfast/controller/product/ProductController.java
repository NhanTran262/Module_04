package com.winzfast.controller.product;

import com.winzfast.dto.ProductDTO;
import com.winzfast.dto.payload.request.product.SearchRequest;
import com.winzfast.dto.payload.response.product.ProductSearchResponse;
import com.winzfast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/show")
    public ResponseEntity<Iterable<ProductDTO>> getProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/allProduct")
    public Page<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getAllProducts(pageRequest);
    }
    @GetMapping("/search")
    public ResponseEntity<ProductSearchResponse> searchProducts(@RequestParam(value = "keyword") String keyword,
                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        SearchRequest searchRequest = new SearchRequest(keyword);

        Pageable pageable = PageRequest.of(page, size);
        ProductSearchResponse response = productService.search(searchRequest, pageable);

        if (response.getProducts().isEmpty()) {
            String message = "No products found for the given keyword";
            int status = 404;
            return ResponseEntity.status(status).body(new ProductSearchResponse(message, new ArrayList<>(), status));
        }

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/info")
    public ResponseEntity<ProductDTO> getProductInfo(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductInfoById(id);
        if (productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



