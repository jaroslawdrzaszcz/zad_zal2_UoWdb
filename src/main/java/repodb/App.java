package repodb;

import java.util.List;


import repodb.db.JdbcCatalogFactory;
import repodb.db.RepositoryCatalog;
import repodb.domian.Contact;
import repodb.domian.Email;
import repodb.domian.PhoneCall;


public class App 
{
    public static void main( String[] args ){
    	
    	Contact stanek = new Contact();
    	stanek.setName("Roman");
    	stanek.setSurname("Stankiewicz");
    	stanek.setAge(35);
    	stanek.setEmail("stanek123456@gmail.com");
    	stanek.setPhone("159267483");
    	stanek.setLocalization("Zło");
    	
    	Contact janek = new Contact();
    	janek.setName("Jan");
    	janek.setSurname("Kowalski");
    	janek.setAge(30);
    	janek.setEmail("janek_kowalski@gmail.com");
    	janek.setPhone("123456789");
    	janek.setLocalization("Bąbelkowo");
    	
    	Contact zenek = new Contact();
    	zenek.setName("Zenek");
    	zenek.setSurname("Nowak");
    	zenek.setPhone("987654321");
    	zenek.setAge(30);
    	zenek.setEmail("zenon.nowak@gmail.com");
    	zenek.setLocalization("Ząbkowice");
    	
    	RepositoryCatalog workdb = new JdbcCatalogFactory().HsqlDbWorkDb();
    	workdb.contacts().add(stanek);
    	workdb.contacts().add(janek);
    	workdb.contacts().add(zenek);
    	workdb.contacts().delete(zenek);;
    	
    	//DODANE
    	//metoda saveChanges() została dodana, żeby zmiany zostały zapisane do bazy, bo add(Stanek) nie dodaje do bazy
    	//to powodowało NullPointer, ale po dodaniu ent.setRepository(this); w metodzie add w RepositoryBase działa, bo wcześinej repository było nullem
    	//Null rzucało w klasie Entity w poleceniu repository.persistAdd(this); w metodzie saveChanges
    	workdb.saveChanges();
    	
    	List<Contact> contacts = workdb.contacts().getAll();
    	for(Contact p : contacts){
    		System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getSurname()+"\t"+p.getPhone()+"\t"+p.getAge()+
    				"\t"+p.getEmail()+"\t"+p.getLocalization());
    	}
    	
    	PhoneCall tozenek = new PhoneCall();
    	tozenek.setContactId(10);
    	tozenek.setPhone("987654321");
    	tozenek.setCallDate("2018-01-05");
    	tozenek.setCallTimeStart("11:20:20");
    	tozenek.setCallTimeStop("11:20:50");
    	tozenek.setDescription("Call to Zenek");
    	
    	
    	RepositoryCatalog callrepo = new JdbcCatalogFactory().HsqlDbWorkDb();
    	callrepo.calls().add(tozenek);
    	callrepo.saveChanges();
    	
    	List<PhoneCall> calls = callrepo.calls().getAll();
    	for(PhoneCall p : calls){
    		System.out.println(p.getId()+"\t"+p.getContactId()+"\t"+p.getPhone()+"\t"+p.getCallDate()+"\t"+p.getCallTimeStart()+
    				"\t"+p.getCallTimeStop()+"\t"+p.getDescription());
    	}

    	
    	Email fromzenek = new Email();
    	fromzenek.setContactId(10);
    	fromzenek.setEmail("zenon.nowak@gmail.com");
    	fromzenek.setMailDate("2017-12-31");
    	fromzenek.setMailTime("23:59:59");
    	fromzenek.setDescription("Greetings from Zenek");
    	
    	RepositoryCatalog emailrepo = new JdbcCatalogFactory().HsqlDbWorkDb();
    	emailrepo.emails().add(fromzenek);
    	emailrepo.saveChanges();
    	
    	List<Email> emails = emailrepo.emails().getAll();
    	for(Email p : emails){
    		System.out.println(p.getId()+"\t"+p.getContactId()+"\t"+p.getEmail()+"\t"+p.getMailDate()+"\t"+p.getMailTime()
    		+"\t"+p.getDescription());
    	}
        System.out.println( "Koniec" );
    }
}
