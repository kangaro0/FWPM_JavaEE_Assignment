package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Description;

public class DescriptionDAO extends BasicDAO implements DAOInterface<Description> {

	private ArrayList<Description> _descriptions;
	
	public DescriptionDAO(){
		initialize();
	}
	
	@Override
	public ArrayList<Description> GetAll() {

		ArrayList<Description> descriptions = new ArrayList<Description>();
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Description" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				Description current = new Description( result.getInt( 1 ), result.getString( 2 ), result.getString( 3 ), result.getString( 4 ), result.getString( 5 ), result.getString( 6 ) );
				descriptions.add( current );
			}
			
			result.close();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
		return descriptions;
	}

	@Override
	public Description GetById(int id) {
		for( Description d : _descriptions ){
			if( d.getId() == id )
				return d;
		}
		return null;
	}

	@Override
	public void Create(Description model) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Description(os,color,size,dimensions,cpu) VALUES('?','?','?','?','?'" );
			stmt.setString( 1, model.getOs() );
			stmt.setString( 2, model.getColor() );
			stmt.setString( 3, model.getSize() );
			stmt.setString( 4, model.getDimensions() );
			stmt.setString( 5, model.getCpu() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}

	@Override
	public void Update(Description model) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Usr SET os='?', color='?', size='?', dimensions='?', cpu='?' WHERE id='?'" );
			stmt.setString( 1, model.getOs() );
			stmt.setString( 2, model.getColor() );
			stmt.setString( 3, model.getSize() );
			stmt.setString( 4, model.getDimensions() );
			stmt.setString( 5, model.getCpu() );
			stmt.setString( 6, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Delete(Description model) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Description WHERE id=?" );
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
		_descriptions = GetAll();
	}

}
