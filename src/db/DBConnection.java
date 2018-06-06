package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configuration.DBConfig;

/*
 * Singleton Class for DB-Connection
 */
public class DBConnection {
	
	private Connection _connection;
	
	public DBConnection( ){
		
	}
	
	public boolean IsConnected(){
		try {
			if( _connection != null && !_connection.isClosed() )
				return true;
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
		return false;
	}
	
	public void Connect(){
		
		if( IsConnected() )
			return;
		
		try {
			
			Class.forName( "com.mysql.cj.jdbc.Driver" );
			_connection = DriverManager.getConnection( DBConfig.URL, DBConfig.User, DBConfig.Password );
			
		} catch( ClassNotFoundException cnfe ){
			cnfe.printStackTrace();
		} catch( SQLException sqle ){
			sqle.printStackTrace();
		}
	}
	
	public void Disconnect(){
		
		if( IsConnected() ){
			try {
				_connection.close();
				_connection = null;
			} catch( SQLException sqle ){
				sqle.printStackTrace();
			}
		}
	}
	
	public Connection GetConnection(){
		return _connection;
	}
	
	
}
