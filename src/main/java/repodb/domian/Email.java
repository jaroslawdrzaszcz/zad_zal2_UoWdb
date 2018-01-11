package repodb.domian;

public class Email implements IHaveId{
	
	private int id;
	private int contactId;
	private String description;
	private String email;
	private String mailDate;
	private String mailTime;
	
	public int getContactId() {
		return contactId;
	}
	public String getDescription() {
		return description;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public String getMailDate() {
		return mailDate;
	}
	public String getMailTime() {
		return mailTime;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}
	public void setMailTime(String mailTime) {
		this.mailTime = mailTime;
	}
	
	
}
