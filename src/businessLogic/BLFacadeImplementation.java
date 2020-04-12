package businessLogic;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}	
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}
   
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}


	@Override
	public Admin tryAdmin(String pId, String pPass) {
		DataAccess dBManager=new DataAccess();
		return dBManager.getAdmin(pId, pPass);
	}


	@Override
	public Usuario tryUser(String pId, String pPass) {
		DataAccess dBManager=new DataAccess();
		return dBManager.getUser(pId, pPass);
	}

	@Override
	public boolean registrarUsuario(String pId, String pPass) {
		DataAccess dBManager=new DataAccess();
		if(!existeUsuario(pId, pPass)) return dBManager.registrarUsuario(pId, pPass);
		else return false;
	}
	
	@Override
	public boolean existeUsuario(String pId, String pPass) {
		DataAccess dBManager=new DataAccess();
		return dBManager.existeUsuario(pId, pPass);
	}
	
	@Override
	public Collection<Usuario> getAllUsers() {
		DataAccess dBManager=new DataAccess();
		return dBManager.getAllUsers();
	}
	
	@Override
	public void incrementarSaldo(String pId, Double pSaldo) {
		DataAccess dBManager=new DataAccess();
		dBManager.incrementarSaldo(pId, pSaldo);
	}
	
	@Override
	public void deleteUser(Usuario pUsuario) {
		DataAccess dBManager=new DataAccess();
		dBManager.deleteUser(pUsuario);
	}
	
	@Override
	public Collection<Apuesta> getApuestasUser(String pId) {
		DataAccess dBManager=new DataAccess();
		return dBManager.getApuestasUser(pId);
	}

	@Override
	public boolean generarApuesta(Question pQuestion, String eleccionApuesta, Double pDinero, Usuario pUsuario) {
		DataAccess dbManager=new DataAccess();
		return dbManager.generarApuesta(pQuestion,eleccionApuesta,pDinero,pUsuario);
	}

	@Override
	public Collection<Event> getAllEvents(){
		DataAccess dbManager=new DataAccess();
		return dbManager.getAllEvents();
	}

	@Override
	public void cambiarContrasena(Usuario usuario, String password) {
		DataAccess dBManager = new DataAccess();
		dBManager.cambiarContrasena(usuario,password);
	}
	
	@Override
	public boolean anadirEvento(String pDescripcion, Date pFecha) {
		DataAccess dBManager = new DataAccess();
		return dBManager.anadirEvento(pDescripcion, pFecha);
	}
	
	@Override
	public boolean anadirRespuesta(Respuesta pRespuesta) {
		DataAccess dBManager = new DataAccess();
		return dBManager.anadirRespuesta(pRespuesta);
	}
}