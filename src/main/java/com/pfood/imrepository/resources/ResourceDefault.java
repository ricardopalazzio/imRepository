/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfood.imrepository.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Default")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/json; charset=ISO-8859-1")
public class ResourceDefault {

	
    
    
	@GET
	public Response setDefault(@QueryParam("authtoken") String authToken) {
		
	String str = null;
		
		try {
	
                    str = "OK! valido: " +authToken;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}	
		
		return Response.ok(str).build();
	}
}