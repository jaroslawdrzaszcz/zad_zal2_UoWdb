package repodb.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import repodb.domian.Email;

public class EmailResultMapper implements ResultSetMapper<Email>{

	public Email map(ResultSet rs) throws SQLException {
		Email e = new Email();
		e.setId(rs.getInt("id"));
		e.setContactId(rs.getInt("contactId"));
		e.setEmail(rs.getString("email"));
		e.setMailDate(rs.getString("mailDate"));
		e.setMailTime(rs.getString("mailTime"));
		e.setDescription(rs.getString("description"));
		return e;
	}

}
