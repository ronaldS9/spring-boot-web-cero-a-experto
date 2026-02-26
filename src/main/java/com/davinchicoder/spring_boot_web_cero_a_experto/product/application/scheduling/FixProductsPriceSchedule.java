package com.davinchicoder.spring_boot_web_cero_a_experto.product.application.scheduling;

import com.davinchicoder.spring_boot_web_cero_a_experto.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

// Esta clase es un ejemplo de cómo usar el scheduling de Spring para ejecutar una tarea periódica que arregle los precios de los productos cada minuto. 
// En este caso, se multiplica el precio de cada producto por 1.1 para simular una subida de precios.
// Pero no es correcto usando la aquitectura hexagonal, ya que el scheduling es una preocupación de infraestructura 
// y no debería estar en la capa de aplicación. 
// La anotacion @Scheduled es proporcionada por Spring y pertenece a la capa de infraestructura 
// ya que es una implementación concreta de un mecanismo de scheduling y es externo a la lógica de negocio.
// depende del reloj del sistema.
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 60000)
    public void fixProductsPrice() {
        log.info("Fixing products price");

        productRepository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * 1.1);
            productRepository.upsert(product);
        });

        log.info("Finished fixing products price");
    }

}
