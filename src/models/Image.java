package models;

public class Image {
	private int _id;
	private byte[] _data;
	
	public Image( int id, byte[] data ){
		_id = id;
		_data = data;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public byte[] getData() {
		return _data;
	}

	public void setData(byte[] _data) {
		this._data = _data;
	}
}
