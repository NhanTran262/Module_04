package com.winzfast.controller.product;

import com.winzfast.dto.payload.request.product.NewsRequest;
import com.winzfast.dto.payload.request.product.ProductRequest;
import com.winzfast.dto.payload.request.product.SpecificationRequest;
import com.winzfast.dto.payload.response.Response;
import com.winzfast.dto.payload.response.product.*;
import com.winzfast.entity.Product;
import com.winzfast.entity.Specification;
import com.winzfast.service.NewsService;
import com.winzfast.service.ProductService;
import com.winzfast.service.SpecificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/news")
@CrossOrigin(origins = "*")

public class NewsController {

    private final NewsService newsService;
    private final SpecificationService specificationService;
    private final ProductService productService;

    @Autowired
    public NewsController(NewsService newsService, SpecificationService specificationService, ProductService productService) {
        this.newsService = newsService;
        this.specificationService = specificationService;
        this.productService = productService;
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllNews() {
        List<Product> products = newsService.getAllNews();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest) {
        NewsResponse newsResponse = newsService.createNews(newsRequest);
        return new ResponseEntity<>(newsResponse, HttpStatus.CREATED);
    }

    @PutMapping("/specification/update/{id}")
    public ResponseEntity<SpecificationResponse> updateSpecification(@PathVariable Long id, @RequestBody SpecificationRequest specificationRequest) {
        SpecificationResponse specificationResponseDTO = specificationService.updateSpecification(id, specificationRequest);
        if (specificationRequest != null) {
            return new ResponseEntity<>(specificationResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequestDTO) {
        ProductResponse productResponseDTO = productService.updateProduct(id, productRequestDTO);
        if (productRequestDTO != null) {
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteNews(@PathVariable Long id) {
        return newsService.delete(id);
    }


    @PostMapping("/{id}/increase-views")
    public Response increaseProductViews(@PathVariable Long id) {
        return productService.increaseViews(id);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Specification>> getSorted(@RequestParam(required = false) String sortBy,
                                                         @RequestParam(required = false, name = "brand") String brand,
                                                         @RequestParam(required = false, name = "carModel") String carModel) {
        List<Specification> sorted = specificationService.sort(sortBy, brand, carModel);
        return new ResponseEntity<>(sorted, HttpStatus.OK);
    }
}
