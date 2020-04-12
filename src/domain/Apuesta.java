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
	
	public Apuesta(Question pQuestion, String eleccionApuesta, Double pDinero, Usuario pUsuario) {
		this.id = count.incrementAndGet();
		this.question = pQuestion;
		this.apostada = eleccionApuesta;
		this.dinero=pDinero;
		this.usuario = pUsuario;
	}

	public Usuario getIdUsuario() {
		return usuario;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public String getApostado() {
		return apostada;
	}

	public Double getDineroApostado() {
		return dinero;
	}	
}