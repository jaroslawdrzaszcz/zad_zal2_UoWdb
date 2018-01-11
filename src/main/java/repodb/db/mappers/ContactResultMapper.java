package repodb.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import repodb.domian.Contact;

public class ContactResultMapper implements ResultSetMapper<Contact> {

	public Contact map(ResultSet rs) throws SQLException {
		Contact c = new Contact();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setSurname(rs.getString("surname"));
		c.setPhone(rs.getString("phone"));
		c.setAge(rs.getInt("age"));
		c.setEmail(rs.getString("email"));
		c.setLocalization(rs.getString("localization"));
		return c;
	}

}
