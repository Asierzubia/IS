package domain;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Apuesta {

	private static final AtomicInteger count = new AtomicInteger(0);
	
	@Id
	private int id;
	private Question question;
	private String apostada;
	private Double dinero;
	private Usuario usuario;
	private Double ganancia;
	private boolean cobrada;
	
	public Apuesta(Question pQuestion, String eleccionApuesta, Double pDinero, Usuario pUsuario, Double pGanancia) {
		this.id = count.incrementAndGet();
		this.question = pQuestion;
		this.apostada = eleccionApuesta;
		this.dinero=pDinero;
		this.usuario = pUsuario;
		this.ganancia=pGanancia;
		this.cobrada = false;
		
	}

	public Usuario getIdUsuario() {
		return this.usuario;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString(){
		return id+";"+question+";"+apostada+";"+dinero+";"+cobrada;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public String getApostado() {
		return this.apostada;
	}

	public Double getDineroApostado() {
		return this.dinero;
	}	
	
	public Double getGanancia() {
		return this.ganancia;
	}
	
	public void setCobrada() {
		this.cobrada = true;
	}
	
	public boolean getCobrada() {
		return this.cobrada;
	}
	
}