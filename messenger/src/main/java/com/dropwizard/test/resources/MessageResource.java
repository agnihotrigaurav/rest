package com.dropwizard.test.resources;

import com.codahale.metrics.annotation.Timed;
import com.dropwizard.test.model.Message;
import com.dropwizard.test.resources.bean.MessageFilterBean;
import com.dropwizard.test.service.MessageService;
import org.glassfish.jersey.server.Uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    MessageService messageService = new MessageService();

    public MessageResource() {

    }

//    @GET
//    @Timed
//    public List<Message> getMessages(@QueryParam("year") int year,
//                                     @QueryParam("start") int start,
//                                     @QueryParam("size") int size) {
//        if(year > 0) {
//            return messageService.getAllMessages();
//        }
//        if(start >= 0 && size >= 0) {
//            return messageService.getAllMessages();
//        }
//        return messageService.getAllMessages();
//    }


    @GET
    @Timed
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
        if(messageFilterBean.getYear() > 0) {
            return messageService.getAllMessages();
        }
        if(messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() >= 0) {
            return messageService.getAllMessages();
        }
        return messageService.getAllMessages();
    }
    @GET
    @Timed
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
        return Response.created(uri).entity(newMessage).build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{messageId}")
    public Message updateMessage(Message message, @PathParam("messageId") long messageId) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Timed
    @Path("/{messageId}")
    /**
     * void will return 204
     */
    public void removeMessage(@PathParam("messageId") long messageId) {
        messageService.removeMessage(messageId);
    }
}
