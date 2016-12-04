package project.DataBase;

import java.util.List;

public interface DataBase<T> {
	
	public void initData();
	public T find(T data1, T data2);
	public List<T> search(T data1);
	public void save(T data1, T data2, T data3);
	public void remove(T data1, T data2, T data3);
	public CominationType getCombination(T data1, T data2, T data3); //uso interno do banco

}
