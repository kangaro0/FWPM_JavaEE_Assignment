package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;

public class Items {
	
	DBConnection _dbConnection;
	ArrayList<Item> _items;
	
	public Items(){
		_dbConnection = new DBConnection();
	}
	
	public ArrayList<Item> GetAll(){
		
		connect();
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
		
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM item" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				Item current = new Item( result.getInt( 1 ), result.getString( 2 ), result.getFloat( 3 ), result.getInt( 4 ), result.getInt( 5 ), result.getInt( 6 ), result.getInt( 7 ) );
				items.add( current );
			}
			
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
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE" );
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
		_items = GetAll();
	}
}
