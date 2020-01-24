package com.sharingo.app;

import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("collection")
public class CollectionResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public JsonObject saveCollection(JsonObject inputData){
		
		CollectionRepo collectionRepo = new CollectionRepo();
		try {
			collectionRepo.saveCollection(inputData);
			System.out.print(inputData);
		} catch (Exception e){
			e.printStackTrace();
		}
				
		return inputData ;
	
	}
}