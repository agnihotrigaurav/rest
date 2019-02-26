package com.dropwizard.test.resources;


import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.awt.*;

@Path("/injectdemo")
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

    @GET
    @Path("/annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("authSessionID") String header,
                                            @CookieParam("name") String cookie) {
        return "Matrix param: " + matrixParam + " Header param: " + header + " Cookie param: " + cookie;
    }

    @GET
    @Timed
    @Path("/context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = headers.getCookies().toString();
        return "Path : " + path + " Cookies : = " + cookies;
    }

}
