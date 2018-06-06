package models;

public class Manufacturer {
	private int _id;
	private String _title;
	
	public Manufacturer( int id, String title ){
		_id = id;
		_title = title;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String _title) {
		this._title = _title;
	}
}
