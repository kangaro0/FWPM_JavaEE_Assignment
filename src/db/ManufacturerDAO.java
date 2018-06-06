package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Manufacturer;


public class ManufacturerDAO implements DAOInterface<Manufacturer>{
	
	
	DBConnection _dbConnection;
	ArrayList<Manufacturer> _manufacturers;
	
	public ManufacturerDAO() {
		_dbConnection = new DBConnection();
	}
	
public ArrayList<Manufacturer> GetAll(){
		
		connect();
		
		ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
		
		try {
		
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Category" );
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Manufacturer model) {
		// TODO Auto-generated method stub
		
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
		
		disconnect();
		
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
		_manufacturers = GetAll();
	}
	
	




}
