package domain;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApuestaGalgo {

	private static final AtomicInteger count = new AtomicInteger(0);

	@Id
	private int id;
	private Galgo galgo;
	private Double dinero;
	private Usuario usuario;
	private Double ganancia;
	private Boolean cobrada;

	
	public ApuestaGalgo(Galgo pGalgo, Double pDinero, Usuario pUsuario, Double pGanancia) {
		this.id = count.incrementAndGet();
		this.galgo = pGalgo;
		this.dinero=pDinero;
		this.usuario = pUsuario;
		this.ganancia=pGanancia;
		this.cobrada = false;
		
	}

	public Usuario getIdUsuario() {
		return this.usuario;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Galgo getGalgo() {
		return this.galgo;
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