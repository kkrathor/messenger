package io.kkrathore.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import io.kkrathore.messenger.model.Message;
import io.kkrathore.messenger.resources.beans.MessageFilterBean;
import io.kkrathore.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize() >0 ) {
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessages(@Context UriInfo uriInfo,Message message) {
		Message newMessage = messageService.addMessage(message);
		
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,  Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long id) {
		return messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id,
							 @Context UriInfo urlInfo) {
		Message message = messageService.getMessage(id);
		
		message.addLink(getUriFromSelf(urlInfo, message), "self");
		message.addLink(getUriFromProfile(urlInfo, message), "profile");
		message.addLink(getUriFromComments(urlInfo,message), "comments");
		return message;
	}

	private String getUriFromComments(UriInfo urlInfo, Message message) {
		URI uri= urlInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build();
					
		return uri.toString();
	}

	private String getUriFromProfile(UriInfo urlInfo, Message message) {
		URI uri = urlInfo.getBaseUriBuilder()
				 .path(ProfileResource.class)
				 .path(Long.toString(message.getId()))
				 .build();
				 
		return uri.toString();
		
	}

	private String getUriFromSelf(UriInfo urlInfo, Message message) {
		String uri = urlInfo.getBaseUriBuilder()
						 .path(MessageResource.class)
						 .path(Long.toString(message.getId()))
						 .build()
						 .toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
