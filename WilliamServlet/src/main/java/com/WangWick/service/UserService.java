package com.WangWick.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import com.WangWick.dao.UserDao;
import com.WangWick.model.User;

public class UserService {
	private UserDao ud;
	private static final Logger LOGGER = Logger.getLogger(UserService.class);

	public UserService() {
		ud = new UserDao();
	}

	public List<User> fetchAllUsers() {
		return ud.getList();
	}

	public User getUserById(int id) {
		return ud.getById(id);
	}

	public User getUserByUsername(String username) {
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(""); //Remove the hashed password for security reasons.
			LOGGER.trace("Password info removed from username " + username + ".");
			return u;
		}
		return null;
	}
	public static String HashPassword(String username, String password) {
		String full = username + password + "salt";
		try {
//				Let MessageDigest know that we want to hash using MD5
			MessageDigest m = MessageDigest.getInstance("md5");
//				Convert our full string to a byte array.
			byte[] messageDigest = m.digest(full.getBytes());
//				Convert our byte array into a signum representation of its former self.
			BigInteger n = new BigInteger(1, messageDigest);

//				Convert the whole array into a hexadecimal string.
			String hash = n.toString(16);
			while (hash.length() < 32) {
				hash = "0" + hash;
			}
			full = hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return full;
	}

	public User getUserByLogin(String user, String pass) {
		User u = ud.getByUsername(user);

		if(u != null) {
			String hash = HashPassword(user,pass);
						if(u.getPassword().equals(hash)) {
					System.out.println("Hash matched!");
					return u;
				}

		}

		return null;
	}

    public void register(User newUser) {
		ud.insert(newUser);
    }
}
