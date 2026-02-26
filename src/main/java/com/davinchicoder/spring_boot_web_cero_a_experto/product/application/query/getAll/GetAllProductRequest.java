package com.davinchicoder.spring_boot_web_cero_a_experto.product.application.query.getAll;

import com.davinchicoder.spring_boot_web_cero_a_experto.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// GetAllProductRequest es una clase que implementa la interfaz Request con un tipo de respuesta GetAllProductResponse.
// Esta clase no tiene campos, lo que indica que no se necesitan parámetros para solicitar la lista de productos.
// Está enlazada a GetAllProductResponse, que es la clase que contiene la lista de productos que se devolverá como respuesta a esta solicitud.
public class GetAllProductRequest implements Request<GetAllProductResponse> {
    
}
