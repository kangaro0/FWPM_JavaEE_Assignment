package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;

public class ItemsDAO implements DAOInterface<Item> {
	
	DBConnection _dbConnection;
	ArrayList<Item> _items;
	
	public ItemsDAO(){
		_dbConnection = new DBConnection();
	}
	
	/*
	 *  CRUD
	 */
	
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
	
	public ArrayList<Item> GetByCategoryId( int categoryId ){
		ArrayList<Item> items = new ArrayList<Item>();
		
		for( Item i : _items ){
			if( i.getCategoryId() == categoryId )
				items.add( i );
		}
		
		return items;
	}
	
	public ArrayList<Item> GetByManufacturerId( int manufacturerId ){
		ArrayList<Item> items = new ArrayList<Item>();
		
		for( Item i : _items ){
			if( i.getManufacturerId() == manufacturerId )
				items.add( i );
		}
		
		return items;
	}
	
	public void Create( Item model ){
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Item(title, manufacturer, prize, image, description, category) VALUES(?,?,?,?,?,?)" );
			stmt.setString( 1, model.getTitle() );
			stmt.setInt( 2, model.getManufacturerId() );
			stmt.setFloat( 3, model.getPrize() );
			stmt.setInt( 4, model.getImageId() );
			stmt.setInt( 5, model.getDescriptionId() );
			stmt.setInt( 6, model.getCategoryId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}
	
	public void Update( Item model ){
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Item SET title='?', manufacturer=?, prize=?, image=?, description=?, category=? WHERE id=?" );
			stmt.setString( 1, model.getTitle() );
			stmt.setInt( 1, model.getManufacturerId() );
			stmt.setFloat( 2, model.getPrize() );
			stmt.setInt( 3, model.getImageId() );
			stmt.setInt( 4, model.getImageId() );
			stmt.setInt( 5, model.getDescriptionId() );
			stmt.setInt( 6, model.getCategoryId() );
			stmt.setInt( 7, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}
	
	public void Delete( Item model ){
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Item WHERE id=?" );
			stmt.setInt( 1, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
	}
	
	/*
	 * Helper functions for filter function
	 */
	
	public static ArrayList<Item> Merge( ArrayList<Item> one, ArrayList<Item> two ){
		ArrayList<Item> items = new ArrayList<Item>();
		items.addAll( one );
		
		int length = two.size();
		for( Item i : two ){
			if( !Has( items, i) )
				items.add( i );
		}
		
		return items;
	}
	
	public static boolean Has( ArrayList<Item> items, Item item ){
		for( Item i : items ){
			if( item.getId() == i.getId() )
				return true;
		}
		return false;
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
