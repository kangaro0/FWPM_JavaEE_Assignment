package model;

public class Item {
	
	private int _id, _imageId;
	private String _name, _description;
	
	public Item( int id, String name, String description, int imageId ){
		_id = id;
		_name = name;
		_description = description;
		_imageId = imageId;
	}
}
