package pl.harpi.prodigy.runtime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.harpi.prodigy.runtime.service.StructureService;

import java.util.Map;

@RestController
@RequestMapping("/repository/v1/products")
public class ProductController {
    @Autowired
    StructureService structureService;

    @GetMapping("{productId}/schema")
    public ResponseEntity<Map<String, Object>> getSchema(@PathVariable Long productId) {
        Map<String, Object> structure = structureService.getStructure(1L);
        return ResponseEntity.ok(structure);
    }
}
