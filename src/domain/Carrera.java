package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import exceptions.QuestionAlreadyExist;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Carrera implements Serializable {
	
	/**
	 * 
	 */
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer carreraNumber;
	private String description; 
	private Date carreraDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Galgo> galgos=new Vector<Galgo>();

	public Vector<Galgo> getGalgos() {
		return galgos;
	}

	public void setQuestions(Vector<Galgo> pGalgos) {
		this.galgos = pGalgos;
	}

	public Carrera() {
		super();
	}

	public Carrera(Integer carreraNumber, String description,Date carreraDate) {
		this.carreraNumber = carreraNumber;
		this.description = description;
		this.carreraDate=carreraDate;
	}
	
	public Carrera( String description,Date carreraDate) {
		this.description = description;
		this.carreraDate=carreraDate;
	}

	public Integer getcarreraNumber() {
		return carreraNumber;
	}

	public void setCarreraNumber(Integer carreraNumber) {
		this.carreraNumber = carreraNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getCarreraDate() {
		return carreraDate;
	}

	public void setCarreraDate(Date carreraDate) {
		this.carreraDate = carreraDate;
	}
	
	
	public String toString(){
		return carreraNumber+";"+description;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Galgo addGalgo(String galgo, float betMinimum)  {
        Galgo g=new Galgo(galgo,betMinimum, this);
        galgos.add(g);
        return g;
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean doesGalgoExists(String galgo)  {
		for (Galgo g : this.getGalgos()){
			if (g.getNombreGalgo().compareTo(galgo)==0)
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carreraNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		if (carreraNumber != other.carreraNumber)
			return false;
		return true;
	}
	public boolean anadirGalgo(Galgo galgo) {
		try {
			galgos.add(galgo);
			return true;
		}catch (Exception h) {
			return false;
		}
		
	}
	public Galgo addGalgoConId(int pId, String nombreGalgo, float betMinimum) {
		Galgo g=new Galgo(pId, nombreGalgo,betMinimum, this);
        galgos.add(g);
        return g;
	}

}