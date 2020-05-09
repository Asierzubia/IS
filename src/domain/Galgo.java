package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Vector;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Galgo implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer galgoNumber;
	private String nombreGalgo; 
	private float betMinimum;
	private Carrera carrera;

	public Galgo(){
		super();
	}
	
	public Galgo(Integer pGalgoNumber, String pNombreGalgo, float pBetMinimum, Carrera carrera) {
		super();
		this.galgoNumber = pGalgoNumber;
		this.nombreGalgo = pNombreGalgo;
		this.betMinimum=pBetMinimum;
		this.carrera = carrera;
	}
	public Galgo(String pNombreGalgo, float betMinimum,Carrera carrera) {
		super();
		this.nombreGalgo = pNombreGalgo;
		this.betMinimum=betMinimum;
		this.carrera = carrera;
	}
	
	public Galgo(Integer pGalgoNumber,String pNombreGalgo, float betMinimum) {
		super();
		this.galgoNumber = pGalgoNumber;
		this.nombreGalgo = pNombreGalgo;
		this.betMinimum=betMinimum;
	}
	
	public void setCarrera(Carrera car) {
		this.carrera = car;
	}
	
	public Carrera getCarrera() {
		return this.carrera;
	}
	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getGalgoNumber() {
		return galgoNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setGalgoNumber(Integer galgoNumber) {
		this.galgoNumber = galgoNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getNombreGalgo() {
		return nombreGalgo;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setNombreGalgo(String nombre) {
		this.nombreGalgo = nombre;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	public String toString(){
		return galgoNumber+";"+nombreGalgo+";"+Float.toString(betMinimum);
	}
}