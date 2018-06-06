package db;

public class BasicDAO {
	
	protected DBConnection _dbConnection;
	
	public BasicDAO(){
		_dbConnection = new DBConnection();
	}
	
	protected void connect(){
		if( _dbConnection.IsConnected() )
			return;
		
		_dbConnection.Connect();
	}
	
	protected void disconnect(){
		if( _dbConnection.IsConnected() )
			_dbConnection.Disconnect();
	}
}
