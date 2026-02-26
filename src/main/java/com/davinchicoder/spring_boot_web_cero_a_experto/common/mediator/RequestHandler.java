package com.davinchicoder.spring_boot_web_cero_a_experto.common.mediator;

// Todos los subtipos de Requet, Request tiene asociado un tipo de respuesta R
public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request);

    Class<T> getRequestType();

}
