package models;

import java.sql.Blob;

public class Image {
	private int _id;
	private Blob _data;
	
	public Image( int id, Blob blob ){
		_id = id;
		_data = blob;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public Blob getData() {
		return _data;
	}

	public void setData(Blob _data) {
		this._data = _data;
	}
}
