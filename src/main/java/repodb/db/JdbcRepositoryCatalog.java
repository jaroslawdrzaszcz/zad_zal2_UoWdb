package repodb.db;

import java.sql.Connection;
import java.sql.SQLException;

import repodb.db.mappers.ContactResultMapper;
import repodb.db.mappers.EmailResultMapper;
import repodb.db.mappers.PhoneCallResultMapper;
import repodb.db.uow.UnitOfWork;
import repodb.domian.Contact;
import repodb.domian.Email;
import repodb.domian.PhoneCall;

public class JdbcRepositoryCatalog implements RepositoryCatalog {
	
	Connection connection;
	UnitOfWork uow;
	
	public JdbcRepositoryCatalog(Connection connection, UnitOfWork uow) {
		this.connection = connection;
		this.uow = uow;
	}
	
	/* (non-Javadoc)
	 * @see repodb.db.RepositoryCatalog#contacts()
	 */
	public Repository<Contact> contacts(){
		try {
			return new ContactRepository(connection, new ContactResultMapper(), uow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	
	/* (non-Javadoc)
	 * @see repodb.db.RepositoryCatalog#calls()
	 */
	public Repository<PhoneCall> calls(){
		try {
			return new PhoneCallRepository(connection, new PhoneCallResultMapper(), uow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	
	/* (non-Javadoc)
	 * @see repodb.db.RepositoryCatalog#emails()
	 */
	public Repository<Email> emails(){
		try {
			return new EmailRepository(connection, new EmailResultMapper(), uow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	/* (non-Javadoc)
	 * @see repodb.db.RepositoryCatalog#saveChanges()
	 */
	public void saveChanges() {
		uow.saveChanges();
		}
}
