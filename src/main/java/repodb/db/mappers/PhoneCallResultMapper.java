package repodb.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import repodb.domian.PhoneCall;

public class PhoneCallResultMapper implements ResultSetMapper<PhoneCall>{

	public PhoneCall map(ResultSet rs) throws SQLException {
		PhoneCall p = new PhoneCall();
		p.setId(rs.getInt("id"));
		p.setContactId(rs.getInt("contactId"));
		p.setPhone(rs.getString("phone"));
		p.setCallDate(rs.getString("callDate"));
		p.setCallTimeStart(rs.getString("callTimeStart"));
		p.setCallTimeStop(rs.getString("callTimeStop"));
		p.setDescription(rs.getString("description"));
		return p;
	}
}
