package model;

public class Image {
	private int _id, _itemId;
	private byte[] _data;
	
	public Image( int id, int itemId, byte[] data ){
		_id = id;
		_itemId = itemId;
		_data = data;
	}
}
