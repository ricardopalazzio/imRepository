package com.pfood.imrepository.filter;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class LogFilter implements ContainerResponseFilter {

	private static Logger logger = Logger.getLogger("webservices-response");
	
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		
		if (response.getStatus() == 500 || response.getStatus() == 401) {			
			if (response.getStatus() == 500)
				logger.error(response.getStatusType().toString() +" - "+ response.getMappedThrowable().getMessage(), response.getMappedThrowable());
			else
				logger.error(response.getStatus() +": "+ response.getStatusType().toString() +" | Acesso negado para URI: " + request.getAbsolutePath());
		}
		
		//controle de cache
		response.getHttpHeaders().putSingle(HttpHeaders.CACHE_CONTROL, "no-cache");
		
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(0);
		cacheControl.setNoCache(true);
		
		response.getResponse().ok().cacheControl(cacheControl).build();
		
		return response;
	}
	
}
