package models;

public class CartItem {
	private int _id, _itemId, _userId, _quantity;
	private Item _item;
	
	public CartItem( int id, int itemId, int userId, int quantity ){
		_id = id;
		_itemId = itemId;
		_userId = userId;
		_quantity = quantity;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public int getItemId() {
		return _itemId;
	}

	public void setItemId(int _itemId) {
		this._itemId = _itemId;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int _userId) {
		this._userId = _userId;
	}
	
	public int getQuantity(){
		return _quantity;
	}
	
	public void setQuantity( int quantity ){
		_quantity = quantity;
	}
	
	public Item getItem(){
		return _item;
	}
	
	public void setItem( Item item ){
		_item = item;
	}
}
