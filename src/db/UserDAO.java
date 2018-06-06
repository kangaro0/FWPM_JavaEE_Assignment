package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

public class UserDAO extends BasicDAO implements DAOInterface<User> {

	private ArrayList<User> _users;
	
	public UserDAO(){
		initialize();
	}
	
	@Override
	public ArrayList<User> GetAll() {
		ArrayList<User> users = new ArrayList<User>();
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Usr" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				User current = new User( result.getInt( 1 ), result.getString( 2 ), result.getString( 3 ), result.getString( 4 ), result.getString( 5 ), result.getString( 6 ), result.getInt( 7 ), result.getString( 8 ) );
				users.add( current );
			}
			
			result.close();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
		return users;
	}

	@Override
	public User GetById( int id ) {
		for( User u : _users ){
			if( u.getId() == id )
				return u;
		}
		return null;
	}
	
	public User GetByUserName( String userName ){
		for( User u : _users ){
			if( u.getUserName() == userName )
				return u;
		}
		return null;
	}
	
	@Override
	public void Create( User model ) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Usr(username,firstname,lastname,address,city,postcode) VALUES(?,?,?,?,?,?)" );
			stmt.setString( 1, model.getUserName() );
			stmt.setString( 2, model.getFirstName() );
			stmt.setString( 3, model.getLastName() );
			stmt.setString( 4, model.getAddress() );
			stmt.setString( 5,  model.getCity() );
			stmt.setInt( 6, model.getPostCode() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}

	@Override
	public void Update( User model ) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Usr SET username='?', firstname='?', lastname='?', address='?', city='?', postCode=? WHERE id=?" );
			stmt.setString( 1, model.getUserName() );
			stmt.setString( 2, model.getFirstName() );
			stmt.setString( 3, model.getLastName() );
			stmt.setString( 4, model.getAddress() );
			stmt.setString( 5,  model.getCity() );
			stmt.setInt( 6, model.getPostCode() );
			stmt.setInt( 7, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Delete( User model ) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Usr WHERE id=?" );
			stmt.setInt( 1, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}

	private void initialize(){
		_users = GetAll();
	}
}
