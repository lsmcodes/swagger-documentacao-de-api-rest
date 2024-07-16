package io.github.lsmcodes.swagger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerErrorException;

import io.github.lsmcodes.swagger.dto.response.Response;

@ControllerAdvice
public class SwaggerExceptionHandler<T> {

        @ExceptionHandler(value = { ProductNotFoundException.class })
        public ResponseEntity<Response<T>> handleProductNotFoundException(ProductNotFoundException exception) {
                Response<T> response = new Response<>();
                response.addErrorToMessage(404, exception.getLocalizedMessage());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        @ExceptionHandler(value = { HttpMessageNotReadableException.class })
        public ResponseEntity<Response<T>> handleHttpMessageNotReadableException(
                        HttpMessageNotReadableException exception) {
                Response<T> response = new Response<>();
                response.addErrorToMessage(400, exception.getLocalizedMessage());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(value = { ServerErrorException.class })
        public ResponseEntity<Response<T>> handleProductServerException(ServerErrorException exception) {
                Response<T> response = new Response<>();
                response.addErrorToMessage(500, exception.getLocalizedMessage());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

}