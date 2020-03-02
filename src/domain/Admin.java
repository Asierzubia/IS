package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	private String id;
	private String password;
	
	public Admin(String pId, String pPass) {
		id=pId;
		password=pPass;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPass() {
		return password;
	}
}
