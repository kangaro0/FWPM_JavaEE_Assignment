package models;

public class Description {
	private int _id;
	private String _os, _color, _size, _dimensions, _cpu;
	
	public Description( int id, String os, String color, String size, String dimensions, String cpu ){
		_id = id;
		_os = os;
		_color = color;
		_size = size;
		_dimensions = dimensions;
		_cpu = cpu;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getOs() {
		return _os;
	}

	public void setOs(String _os) {
		this._os = _os;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String _color) {
		this._color = _color;
	}

	public String getSize() {
		return _size;
	}

	public void setSize(String _size) {
		this._size = _size;
	}

	public String getDimensions() {
		return _dimensions;
	}

	public void setDimensions(String _dimensions) {
		this._dimensions = _dimensions;
	}

	public String getCpu() {
		return _cpu;
	}

	public void setCpu(String _cpu) {
		this._cpu = _cpu;
	}
}
