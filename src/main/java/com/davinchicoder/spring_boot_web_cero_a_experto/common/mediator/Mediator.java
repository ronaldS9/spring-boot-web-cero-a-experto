package com.davinchicoder.spring_boot_web_cero_a_experto.common.mediator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Mediator {

    // <? extends Class<?> Significa que el mapa puede contener cualquier clase que extienda de Class<?>, 
    // lo que permite almacenar diferentes tipos de requests y sus handlers correspondientes, por ejemplo Class<CreateProductRequest>, Class<GetAllProductRequest>, etc.
    // Esto es necesario porque los handlers pueden manejar diferentes tipos de requests, y cada request es representada por una clase específica.
    //Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    // Se ha mejorado la declaración del mapa para que sea más específica y permita una mejor gestión de los tipos de requests y handlers,
    // ahora se declara como Map<Class<?>, RequestHandler<? extends Request<?>, ?>>, lo que indica que el mapa puede contener cualquier clase como clave (representando el tipo de request)
    // y cualquier handler que implemente la interfaz RequestHandler con un tipo de request que extienda de Request<?> como valor. Esto proporciona una mayor flexibilidad y claridad en la gestión de los
    Map<Class<?>, RequestHandler<? extends Request<?>, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        // Crea un mapa de todos los handlers disponibles, mapeando el tipo de request que manejan a la instancia del handler
        // El mapa contendrá entradas como: 
        // {CreateProductRequest.class -> CreateProductHandler}, {GetAllProductRequest.class -> GetAllProductHandler}, etc.
        // Una clase es donde se encuentra la lógica para manejar un tipo específico de request, y el mapa permite buscar rápidamente el handler adecuado para cada request que se envíe al mediador.
        // y la otra clase es la request que se envía al mediador, que contiene los datos necesarios para ejecutar la acción deseada.
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());
        if (handler == null) {
            log.error("No handler found for request type: {}", request.getClass());
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }
        return handler.handle(request);
    }

    @Async
    public <R, T extends Request<R>> void dispatchAsync(T request) {
        this.dispatch(request);
    }


}
