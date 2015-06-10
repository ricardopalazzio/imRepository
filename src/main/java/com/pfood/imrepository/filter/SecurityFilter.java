package com.pfood.imrepository.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;



import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	private static Logger logger = Logger.getLogger("webservices-request");
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		logger.info("HEADER-HTTP: " + request.getRequestHeaders().toString() + 
					" | URI: " + request.getAbsolutePath() +
					" | QUERY PARAMETROS: " + request.getQueryParameters().toString() +
					" | USUARIO: " + request.getFormParameters().getFirst("usuario"));
		
		if (!request.getRequestUri().getPath().endsWith("/Login")) {
			//SessionWS sessao = null;
			
			//if (request.getRequestHeaders().containsKey("authtoken"))
			//	sessao = WSUtil.ChecarPermissao(request.getRequestHeaders().get("authtoken").get(0));
			
			//if (request.getQueryParameters().containsKey("authtoken"))
				//sessao = WSUtil.ChecarPermissao(request.getQueryParameters().get("authtoken").get(0));
			
//			if(sessao != null) {
//				return request;
//			} else
//				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
//		} else {
//			return request;
//		}
	}
            return null;
        }
}
