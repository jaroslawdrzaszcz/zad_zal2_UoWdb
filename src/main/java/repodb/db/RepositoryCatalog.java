package repodb.db;

import repodb.domian.Contact;
import repodb.domian.Email;
import repodb.domian.PhoneCall;

public interface RepositoryCatalog {

	Repository<Contact> contacts();

	Repository<PhoneCall> calls();

	Repository<Email> emails();

	void saveChanges();

}