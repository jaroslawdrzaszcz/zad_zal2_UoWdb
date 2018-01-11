package repodb.db;

import java.util.List;

import repodb.domian.IHaveId;

public interface Repository<TEntity extends IHaveId> {
	
	public void createTable();
	public void delete(TEntity entity);
	public void add(TEntity entity);
	public void update(TEntity entity);
	public List<TEntity> getAll();
	public List<TEntity> getById();
}
