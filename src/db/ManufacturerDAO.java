package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Manufacturer;


public class ManufacturerDAO extends BasicDAO implements DAOInterface<Manufacturer>{
	
	ArrayList<Manufacturer> _manufacturers;
	
	public ManufacturerDAO() {
		super();
		
		_manufacturers = new ArrayList<Manufacturer>();
	}
	
	public ArrayList<Manufacturer> GetAll(){
		
		// if we already pulled from db
		if( _manufacturers.size() != 0 )
			return _manufacturers;
		
		connect();
		
		ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
		
		try {
		
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Manufacturer" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				Manufacturer current = new Manufacturer(result.getInt(1), result.getString(2));
				_manufacturers.add( current );
			}
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
		return manufacturers;
	}
	
	public Manufacturer GetById( int id ){
		for( Manufacturer i : _manufacturers ){
			if( i.getId() == id )
				return i;
		}
		return null;
	}
	
	@Override
	public void Create(Manufacturer model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Manufacturer(title) VALUES(?)" );
			stmt.setString( 1, model.getTitle() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Delete(Manufacturer model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Manufacturer WHERE id=?" );
			stmt.setInt( 1, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Update(Manufacturer model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Manufacturer SET title='?' WHERE id=?" );
			stmt.setString( 1, model.getTitle() );
			stmt.setInt(2, model.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		initialize();
		disconnect();
		
	}
	
	private void initialize(){
		_manufacturers = GetAll();
	}
	
	




}
