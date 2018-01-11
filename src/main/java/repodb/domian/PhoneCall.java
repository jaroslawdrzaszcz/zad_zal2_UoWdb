package repodb.domian;

public class PhoneCall implements IHaveId{
	
	private int id;
	private String callDate;
	private String callTimeStart;
	private String callTimeStop;
	private int contactId;
	private String description;
	private String phone;
	
	public String getCallDate() {
		return callDate;
	}
	public String getCallTimeStart() {
		return callTimeStart;
	}
	public String getCallTimeStop() {
		return callTimeStop;
	}
	public int getContactId() {
		return contactId;
	}

	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public String getPhone() {
		return phone;
	}
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	public void setCallTimeStart(String callTimeStart) {
		this.callTimeStart = callTimeStart;
	}
	public void setCallTimeStop(String callTimeStop) {
		this.callTimeStop = callTimeStop;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}

