package businessLogic;

import java.util.Vector;
import java.util.Collection;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Usuario;
import domain.Admin;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import gui.Apuesta;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	/**
	 * This method retrieves from the database an Admin 
	 * 
	 * @param an identifier and a pass 
	 * @return an Admin or null
	 */
	@WebMethod public Admin tryAdmin(String string, String pPass);
	
	/**
	 * This method retrieves from the database an Usuario 
	 * 
	 * @param an identifier and a pass 
	 * @return an Usuario or null
	 */
	@WebMethod public Usuario tryUser(String string, String pPass);
	
	@WebMethod public boolean registrarUsuario(String string, String pPass);

	@WebMethod boolean existeUsuario(String pId, String pPass);

	@WebMethod Collection<Usuario> getAllUsers();

	@WebMethod void incrementarSaldo(String pId, Double pSaldo);

	@WebMethod void deleteUser(Usuario pUsuario);

	@WebMethod Collection<Apuesta> getApuestasUser(String pId);
}