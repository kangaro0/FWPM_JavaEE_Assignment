package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;

public class ItemsDAO {
	
	DBConnection _dbConnection;
	ArrayList<Item> _items;
	
	public ItemsDAO(){
		_dbConnection = new DBConnection();
	}
	
	public ArrayList<Item> GetAll(){
		
		connect();
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
		
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Item" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				Item current = new Item( result.getInt( 1 ), result.getString( 2 ), result.getFloat( 3 ), result.getInt( 4 ), result.getInt( 5 ), result.getInt( 6 ), result.getInt( 7 ) );
				items.add( current );
			}
			
			result.close();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
		return items;
	}
	
	public Item GetById( int id ){
		for( Item i : _items ){
			if( i.getId() == id )
				return i;
		}
		return null;
	}
	
	public void UpdateItem( Item item ){
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Item SET title='?', manufacturer=?, prize=?, image=?, description=?, category=? WHERE id=?" );
			stmt.setString( 1, item.getTitle() );
			stmt.setInt( 1, item.getManufacturerId() );
			stmt.setFloat( 2, item.getPrize() );
			stmt.setInt( 3, item.getImageId() );
			stmt.setInt( 4, item.getImageId() );
			stmt.setInt( 5, item.getDescriptionId() );
			stmt.setInt( 6, item.getCategoryId() );
			stmt.setInt( 7, item.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}
	
	public void CreateItem( Item item ){
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Item(title, manufacturer, prize, image, description, category) VALUES(?,?,?,?,?,?)" );
			stmt.setString( 1, item.getTitle() );
			stmt.setInt( 2, item.getManufacturerId() );
			stmt.setFloat( 3, item.getPrize() );
			stmt.setInt( 4, item.getImageId() );
			stmt.setInt( 5, item.getDescriptionId() );
			stmt.setInt( 6, item.getCategoryId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
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
		_items = GetAll();
	}
}
