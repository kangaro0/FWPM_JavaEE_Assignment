package models;

public class Item {
	
	private int _id, _imageId, _descriptionId, _categoryId, _manufacturerId;
	private String _title;
	private float _prize;
	
	public Item( int id, String title, float prize, int imageId, int descriptionId, int categoryId, int manufacturerId ){
		_id = id;
		_title = title;
		_prize = prize;
		_imageId = imageId;
		_descriptionId = descriptionId;
		_categoryId = categoryId;
		_manufacturerId = manufacturerId;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public int getImageId() {
		return _imageId;
	}

	public void setImageId(int _imageId) {
		this._imageId = _imageId;
	}

	public int getDescriptionId() {
		return _descriptionId;
	}

	public void setDescriptionId(int _descriptionId) {
		this._descriptionId = _descriptionId;
	}

	public int getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(int _categoryId) {
		this._categoryId = _categoryId;
	}

	public int getManufacturerId() {
		return _manufacturerId;
	}

	public void setManufacturerId(int _manufacturerId) {
		this._manufacturerId = _manufacturerId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String _title) {
		this._title = _title;
	}

	public float getPrize() {
		return _prize;
	}

	public void setPrize(float _prize) {
		this._prize = _prize;
	}
}
