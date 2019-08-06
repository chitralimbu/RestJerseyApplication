package com.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
	List<User> users = setup();
	
	public static final String RESOURCE = "/home/dharanboi/Documents/RestData/users.csv";
	
	private List<User> setup() {
		
		try {
			List<User> allUsers = Files.readAllLines(Paths.get(RESOURCE))
					.stream()
					.map(user -> user.split(","))
					.map(user -> new User(Integer.parseInt(user[0]), user[1], user[2]))
					.collect(Collectors.toList());
			return allUsers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User getUserByName(String name) {
		return users.stream()
					.filter(s -> s.getName().equalsIgnoreCase(name))
					.findAny()
					.orElse(new User(0, "No Name Found", "Job Not Found"));
	}
	
	public int addUser(User pUser) {
		List<User> allUsers = setup();
		allUsers.add(pUser);
		replace(allUsers);
		return 1;
	}
	
	public int updateUser(User pUser) {
		List<User> currentUsers = setup();
		for(User user: currentUsers) {
			if(user.getId() == pUser.getId()) {
				System.out.println("Id is the same");
				int index = currentUsers.indexOf(user);
				currentUsers.set(index, pUser);
				replace(currentUsers);
				return 1;
			}
		}
		return 0;
	}
	
	public int deleteUser(int id) {
		System.out.println("ID passed through: " + id);
		List<User> allUsers = setup();
		for(User user: allUsers) {
			System.out.println(user.getId());
			if(user.getId() == id) {
				int index = allUsers.indexOf(user);
				allUsers.remove(index);
				replace(allUsers);
				return 1;
			}
		}
		return 0;
	}
	
	public void replace(List<User> users) {
		String stringUsers = users.stream()
				.map(user -> String.format("%s,%s,%s", user.getId(), user.getName(), user.getProfession()))
				.collect(Collectors.joining("\n"));
		try {
			Files.write(Paths.get(RESOURCE), stringUsers.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		//userDao.addUser(new User(4, "Bob", "Singer"));
		//userDao.deleteUser(3);
		userDao.updateUser(new User(1, "Nikhil", "Writer"));
		userDao.setup().stream()
					.forEach(System.out::println);
	}
	
}
