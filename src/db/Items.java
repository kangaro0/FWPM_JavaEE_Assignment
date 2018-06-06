package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Item;

public class Items {
	
	DBConnection _dbConnection;
	ArrayList<Item> _items;
	
	public Items(){
		_dbConnection = new DBConnection();
	}
	
	public ArrayList<Item> GetAll(){
		
		establish();
		
		PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM item" );
		ResultSet result = stmt.executeQuery();
		
		while( result.next() ){
			Item current = new Item( result.getInt( 1 ), result.getString( 2 ), result.getFloat( 3 ), result.getInt( 4 ), result.getInt( 5 ), result.getInt( 6 ), result.getInt( 7 ) );
			_items.add( current );
		}
	}
	
	private void establish(){
		if( _dbConnection.IsConnected() )
			return;
		
		_dbConnection.Connect();
	}
	
	private void 
}
