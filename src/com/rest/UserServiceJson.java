package com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UserService/json")
@Produces(MediaType.APPLICATION_JSON)
public class UserServiceJson {
	
	UserDao userDao = new UserDao();
	
	@Path("/all")
	@GET
	
	public List<User> getUsersJson(){
		return userDao.getAllUsers();
	}
	
	@Path("/{name}")
	@GET
	
	public User getUser(@PathParam("name") String name) {
		return userDao.getUserByName(name);
	}
	
	@POST
	@Path("/add/users")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		return Response.status(201).entity(userDao.addUser(user)).build();
	}
	
	@PUT  
	@Path("/update/users")  
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		return Response.status(200).entity(userDao.updateUser(user)).build();
	}
	
	@DELETE
	@Path("delete/users/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userId") int id) {
		return Response.status(200).entity(userDao.deleteUser(id)).build();
	}
}
