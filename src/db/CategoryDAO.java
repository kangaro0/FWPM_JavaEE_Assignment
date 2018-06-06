package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;

public class CategoryDAO implements DAOInterface<Category>{
	
	DBConnection _dbConnection;
	ArrayList<Category> _categories;
	
	public CategoryDAO() {
		_dbConnection = new DBConnection();
	}
	
	public ArrayList<Category> GetAll(){
		
	connect();
		
	ArrayList<Category> categories = new ArrayList<Category>();
		
	try {
		
		PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Category" );
		ResultSet result = stmt.executeQuery();
			
		while( result.next() ){
			Category current = new Category(result.getInt(1), result.getString(2));
			_categories.add( current );
		}
			
	} catch( SQLException sqle ){
			sqle.printStackTrace();
	}
		
	disconnect();
		
		return categories;
	}
	
	public Category GetById( int id ){
		for( Category i : _categories ){
			if( i.getId() == id )
				return i;
		}
		return null;
	}
	
	@Override
	public void Update(Category model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Category SET title='?' WHERE id=?" );
			stmt.setString( 1, model.getTitle() );
			stmt.setInt(2, model.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
	}

	@Override
	public void Create(Category model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Category model) {
		// TODO Auto-generated method stub
		
	}

	
	private void connect(){
		if( _dbConnection.IsConnected() )
			return;
		
		_dbConnection.Connect();
	}
	
	private void disconnect(){
		if( _dbConnection.IsConnected() )
			_dbConnection.Disconnect();
	}
	
	private void initialize(){
		_categories = GetAll();
	}


}
