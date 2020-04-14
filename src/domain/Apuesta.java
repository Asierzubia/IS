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
	
	public Apuesta(Question pQuestion, String eleccionApuesta, Double pDinero, Usuario pUsuario, Double pGanancia) {
		this.id = count.incrementAndGet();
		this.question = pQuestion;
		this.apostada = eleccionApuesta;
		this.dinero=pDinero;
		this.usuario = pUsuario;
		this.ganancia=pGanancia;
	}

	public Usuario getIdUsuario() {
		return this.usuario;
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
}