package io.kkrathore.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.kkrathore.messenger.model.ErrorMessage;


public class GenericExceptionMapper  implements ExceptionMapper<Throwable>{

		@Override
		public Response toResponse(Throwable exception) {
			ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),500,"http://kkrathore.io");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build();
		}

	}
