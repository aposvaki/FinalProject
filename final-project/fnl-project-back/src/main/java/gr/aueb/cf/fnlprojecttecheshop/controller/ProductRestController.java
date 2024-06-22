package gr.aueb.cf.fnlprojecttecheshop.controller;

import gr.aueb.cf.fnlprojecttecheshop.dto.ProductInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductReadOnlyDTO;
import gr.aueb.cf.fnlprojecttecheshop.dto.ProductUpdateDTO;
import gr.aueb.cf.fnlprojecttecheshop.mapper.ProductMapper;
import gr.aueb.cf.fnlprojecttecheshop.model.Product;
import gr.aueb.cf.fnlprojecttecheshop.service.IProductService;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.fnlprojecttecheshop.validator.ProductInsertValidator;
import gr.aueb.cf.fnlprojecttecheshop.validator.ProductUpdateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {

    private final IProductService productService;
    private final ProductInsertValidator insertValidator;
    private final ProductUpdateValidator updateValidator;

    Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Operation(summary = "Add a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Inserted",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)
    })
    @PostMapping("/products/add")
    public ResponseEntity<ProductReadOnlyDTO> addProduct(@Valid @RequestBody ProductInsertDTO dto,
                                                         BindingResult bindingResult) {
        insertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            Product product = productService.insertProduct(dto);
            ProductReadOnlyDTO productReadOnlyDTO = ProductMapper.mapToReadOnlyDTO(product);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(productReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(productReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @Operation(summary = "Get products by their product name containing characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Founds",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid product name supplied", content = @Content)
    })
    @GetMapping("/products/")
    public ResponseEntity<List<ProductReadOnlyDTO>> getProductByProductName(@RequestParam("productName") String productName) {
        List<Product> products;
        try {
            products = productService.getProductByProductName(productName);
            List<ProductReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for(Product product : products) {
                readOnlyDTOS.add(ProductMapper.mapToReadOnlyDTO(product));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Founds",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid product name supplied", content = @Content)
    })
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductReadOnlyDTO> getProduct(@PathVariable("id") Long id){
        Product product;

        try {
            product = productService.getProductById(id);
            ProductReadOnlyDTO dto = ProductMapper.mapToReadOnlyDTO(product);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a product")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Product Updated",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied", content = @Content)
    })
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductReadOnlyDTO> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDTO dto, BindingResult bindingResult){
        if (!Objects.equals(id, dto.getId())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        updateValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Product product = productService.updateProduct(dto);
            ProductReadOnlyDTO readOnlyDTO = ProductMapper.mapToReadOnlyDTO(product);
            return new ResponseEntity<>(readOnlyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prodcut deleted",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @DeleteMapping("products/{id}")
    public ResponseEntity<ProductReadOnlyDTO> deleteProduct(@PathVariable("id") Long id) {
        try {
            Product product = productService.deleteProduct(id);
            ProductReadOnlyDTO readOnlyDTO = ProductMapper.mapToReadOnlyDTO(product);
            return ResponseEntity.ok(readOnlyDTO);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
