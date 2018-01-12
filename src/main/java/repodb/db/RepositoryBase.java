package repodb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repodb.db.mappers.ResultSetMapper;
import repodb.db.uow.Entity;
import repodb.db.uow.UnitOfWork;
import repodb.db.uow.UnitOfWorkRepository;
import repodb.domian.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> implements Repository<TEntity>, UnitOfWorkRepository{
	
	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement delete;
	protected PreparedStatement insert;
	protected PreparedStatement selectAll;
	protected PreparedStatement update;
	protected PreparedStatement selectById;
	protected String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	ResultSetMapper<TEntity> mapper;
	UnitOfWork uow;
	
	protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper, UnitOfWork uow) throws SQLException {
		this.mapper = mapper;
		this.connection = connection;
		this.uow = uow;
		connection = DriverManager.getConnection(url);
		createTable = connection.createStatement();
		createTable();
		insert = connection.prepareStatement(insertSql());
		update = connection.prepareStatement(updateSql());
		delete = connection.prepareStatement(deleteSql());
		selectAll = connection.prepareStatement(selectAllSql());
		selectById = connection.prepareStatement(selectByIdSql());
	}
	
	protected abstract String insertSql();
	protected abstract String updateSql();
	protected abstract String deleteSql();
	protected abstract String selectAllSql();
	protected abstract String selectByIdSql();
	
	public void createTable(){
		try {
		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		boolean tableExists = false;
		while(rs.next()){ if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())){
		tableExists=true;
		break;
		}
		}
		if(!tableExists) createTable.executeUpdate(createTableSql());
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	protected abstract String tableName();
	protected abstract String createTableSql();
	protected abstract void setupUpdate(TEntity entity) throws SQLException;
	protected abstract void setupInsert(TEntity entity) throws SQLException;
	//protected abstract void setupDelete(TEntity entity) throws SQLException;
	
	public void persistAdd(Entity entity){
		try {
			setupInsert((TEntity)entity.getEntity());
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(TEntity entity) {
		Entity ent = new Entity();
		ent.setEntity(entity);
		//DODANE
		ent.setRepository(this);
		uow.markAsNew(ent);
	}
	
	public void persistUpdate(Entity entity){
		try {
			setupUpdate((TEntity)entity.getEntity());
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public void update(TEntity entity) {
		Entity ent = new Entity();
		ent.setEntity(entity);
		ent.setRepository(this);
		uow.markAsChanged(ent);
	}
	
	public void persistDelete(Entity entity) {

		try{
			delete.setInt(1, entity.getEntity().getId());//czy napewno to jest ok do spr.
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public void delete(TEntity entity) {
		Entity ent = new Entity();
		ent.setEntity(entity);
		ent.setRepository(this);
		uow.markAsDeleted(ent);
	}
	
	public List<TEntity> getAll(){
		List<TEntity> result = new ArrayList<TEntity>();
		try {
		ResultSet rs = selectAll.executeQuery();
		while(rs.next()){ result.add(mapper.map(rs));
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
		}
	
	public List<TEntity> getById(){
		List<TEntity> result = new ArrayList<TEntity>();
		try {
		ResultSet rs = selectById.executeQuery();
		while(rs.next()){ result.add(mapper.map(rs));
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
		}
	
}
