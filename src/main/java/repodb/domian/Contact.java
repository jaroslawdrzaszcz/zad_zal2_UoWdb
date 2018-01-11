package repodb.domian;

public class Contact implements IHaveId{
	
	private int id;
	private int age;
	private String email;
	private String localization;
	private String name;
	private String phone;
	private String surname;

	public int getAge() {
		return age;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public String getLocalization() {
		return localization;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getSurname() {
		return surname;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLocalization(String localization) {
		this.localization = localization;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	
}
