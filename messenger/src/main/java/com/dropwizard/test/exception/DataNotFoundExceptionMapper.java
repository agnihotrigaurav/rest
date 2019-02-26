package com.dropwizard.test.exception;

import com.dropwizard.test.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException dataNotFoundException) {
        ErrorMessage errorMessage = new ErrorMessage(dataNotFoundException.getMessage(), 404, "");
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
