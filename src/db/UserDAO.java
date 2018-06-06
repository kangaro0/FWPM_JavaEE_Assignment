package db;

import java.util.ArrayList;
import models.User;

public class UserDAO implements DAOInterface<User> {

	private DBConnection _dbConnection;
	private ArrayList<User> _users;
	
	@Override
	public ArrayList<User> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void Create(User model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(User model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(User model) {
		// TODO Auto-generated method stub
		
	}

}
