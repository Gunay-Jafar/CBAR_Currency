package isb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import isb.dao.CurrencyCbarDAO;

@Path("/currencies")
public class IsbRestWS {

	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCurrency(@PathParam("code") String code) {
		
		CurrencyCbarDAO currencyDAO = new CurrencyCbarDAO();
		
		return Response.status(200).entity(currencyDAO.getCurrencyByCode(code)).build();
	}
}