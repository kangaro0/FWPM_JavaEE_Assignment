package db;

import java.util.ArrayList;

public interface DAOInterface<T> {
	
	public ArrayList<T> GetAll();
	
	public T GetById(int id);
	
	public void Create(T model);
	
	public void Update(T model);
	
	public void Delete(T model);
}
