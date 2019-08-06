package com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UserService")
public class UserService {

	UserDao userDao = new UserDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers(){
		return userDao.getAllUsers();
	}

	@Path("/json/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserJson(@PathParam("name") String name){
		return Response.status(200).entity(userDao.getUserByName(name)).build();
	}

	@POST
	@Path("/add/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public String createUser(User user) {
		userDao.addUser(user);
		return SUCCESS_RESULT;
	}

	@PUT
	@Path("/update/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public String updateUser(User user) {
		int result = userDao.updateUser(user);
		return (result == 1) ? SUCCESS_RESULT : FAILURE_RESULT;
	}

	@DELETE
	@Path("/delete/users/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteUser(@PathParam("userId") int id) {
		int result = userDao.deleteUser(id);
		return (result == 1) ? SUCCESS_RESULT : FAILURE_RESULT;
	}

	@OPTIONS
	@Path("/option/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
