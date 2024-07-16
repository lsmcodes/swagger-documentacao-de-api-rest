package io.github.lsmcodes.swagger.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class Response<T> {

        private T data;
        private Object errors;

        public void addErrorToMessage(int status, String error) {
                ResponseError responseError = new ResponseError()
                                .setTimestamp(LocalDateTime.now())
                                .setStatus(status)
                                .setError(error);
                setErrors(responseError);
        }

}