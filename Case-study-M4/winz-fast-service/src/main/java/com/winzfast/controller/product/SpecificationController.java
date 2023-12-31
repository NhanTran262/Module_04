package com.winzfast.controller.product;

import com.winzfast.dto.SpecificationDTO;
import com.winzfast.service.SpecificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/specifications")
public class    SpecificationController {  private final SpecificationService specificationService;
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping("/show")
    public ResponseEntity<Iterable<SpecificationDTO>> getSpecifications() {
        return new ResponseEntity<>(specificationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<SpecificationDTO> getSpecification(@PathVariable Long id) {
        return new ResponseEntity<>(specificationService.getSpecificationById(id), HttpStatus.OK);
    }
}
