package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CartItem;

public class CartDAO extends BasicDAO implements DAOInterface<CartItem> {

	private ArrayList<CartItem> _cartItems;
	
	public CartDAO(){
		initialize();
	}
	
	/*
	 * CRUD
	 */
	@Override
	public ArrayList<CartItem> GetAll() {
		
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "SELECT * FROM Cart" );
			ResultSet result = stmt.executeQuery();
			
			while( result.next() ){
				CartItem current = new CartItem( result.getInt( 1 ), result.getInt( 2 ), result.getInt( 3 ), result.getInt( 4 ) );
				cartItems.add( current );
			}
			
			result.close();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		disconnect();
		
		return cartItems;
	}

	@Override
	public CartItem GetById(int id) {
		for( CartItem c : _cartItems )
			if( c.getId() == id )
				return c;
		return null;
	}
	
	public ArrayList<CartItem> GetByItemId( int itemId ){
		ArrayList<CartItem> items = new ArrayList<CartItem>();
		for( CartItem c : _cartItems )
			if( c.getItemId() == itemId )
				items.add( c );
		return items;
	}
	
	public ArrayList<CartItem> GetByUserId( int userId ){
		ArrayList<CartItem> items = new ArrayList<CartItem>();
		for( CartItem c : _cartItems )
			if( c.getUserId() == userId )
				items.add( c );
		return items;
	}

	@Override
	public void Create(CartItem model) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "INSERT INTO Cart(itemid,userid,quantity) VALUES(?,?,?)" );
			stmt.setInt( 1, model.getItemId() );
			stmt.setInt( 2, model.getUserId() );
			stmt.setInt( 3, model.getQuantity() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Update(CartItem model) {
		
		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "UPDATE Cart SET itemid=?, userid=?, quantity=? WHERE id=?" );
			stmt.setInt( 1, model.getItemId() );
			stmt.setInt( 2, model.getUserId() );
			stmt.setInt( 3, model.getQuantity() );
			stmt.setInt( 4, model.getId() );
			
			stmt.execute();
			stmt.close();
			
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		
		initialize();
		disconnect();
		
	}

	@Override
	public void Delete(CartItem model) {

		connect();
		
		try {
			
			PreparedStatement stmt = _dbConnection.GetConnection().prepareStatement( "DELETE FROM Cart WHERE id=?" );
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
		_cartItems = GetAll();
	}
	
}
