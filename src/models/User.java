package models;

public class User {
	private int _id, _postCode;
	private String _userName, _firstName, _lastName, _address, _city;
	
	public User( int id, String userName, String firstName, String lastName, String address, String city, int postCode ){
		_id = id;
		_userName = userName;
		_firstName = firstName;
		_lastName = lastName;
		_address = address;
		_city = city;
		_postCode = postCode;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public int getPostCode() {
		return _postCode;
	}

	public void setPostCode(int _postCode) {
		this._postCode = _postCode;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String _userName) {
		this._userName = _userName;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String _address) {
		this._address = _address;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String _city) {
		this._city = _city;
	}
	
	
}