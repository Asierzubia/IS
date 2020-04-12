package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Respuesta implements Serializable{
	
    private Question question;
    private Event event;
    private String textoRespuesta;
    
    public Respuesta() {};
  
    public Respuesta(Question pQuestion, Event pEvent, String pTextoRespuesta){
    	super();
    	this.question = pQuestion;
        this.event = pEvent;
        this.textoRespuesta = pTextoRespuesta;
    }
    
    public String getTextoRespuesta(){ 
    	return this.textoRespuesta;
    }
    
    public int getQuestionNumber(){ 
    	return this.question.getQuestionNumber();
    }
    
    public int getEventNumber(){ 
    	return this.event.getEventNumber();
    }
    
    public String toString(){
		return question.getQuestionNumber() + " ; " + this.textoRespuesta;
	}
    
    public void setTextoRespuesta(String pTexto){ this.textoRespuesta = pTexto; }
}
