package com.davinchicoder.spring_boot_web_cero_a_experto.product.application.command.create;

import com.davinchicoder.spring_boot_web_cero_a_experto.common.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
// Datos de entrada para crear un nuevo producto, 
// incluyendo su id, nombre, descripción, precio y un archivo (posiblemente una imagen del producto).
public class CreateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
}
