package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Image;


public class ImageDAO extends BasicDAO implements DAOInterface<Image> {
	
	ArrayList<Image> _images;
	
	public ImageDAO() {
		super();
		initialize();
	}

	@Override
	public ArrayList<Image> GetAll() {
		connect();
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		try {
		
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Image" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				Image current = new Image(result.getInt(1), result.getBlob(2));
				images.add( current );
			}
			
			result.close();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		return images;
	}

	@Override
	public Image GetById(int id) {
		for( Image i : _images ){
			if( i.getId() == id )
				return i;
		}
		return null;
	}

	@Override
	public void Update(Image model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Image SET dat='?' WHERE id=?" );
			stmt.setBlob( 1, model.getData() );
			stmt.setInt(2, model.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}

	@Override
	public void Create(Image model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Image(dat) VALUES(?)" );
			stmt.setBlob(1, model.getData());
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}

	@Override
	public void Delete(Image model) {
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Image WHERE id=?" );
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
		_images = GetAll();
	}

}
