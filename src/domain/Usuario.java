package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	private String id;
	private double money;
	private String password;
	
	public Usuario(String pId, String pPass){
		id=pId;
		password=pPass;
		money=0.0;
	}
	
	public double getMoney() {
		return money;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPass() {
		return password;
	}
	
	public String toString() { return id + " " + password + " " + money;}

	public void aumentarSaldo(Double pSaldo) {
		money = money + pSaldo;
	}
}
