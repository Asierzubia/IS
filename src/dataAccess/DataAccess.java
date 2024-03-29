package dataAccess;


import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {
		
		c=ConfigXML.getInstance();
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
	}

	public DataAccess()  {
		Locale.setDefault(new Locale("es"));
		new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {
	
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			Carrera c1 = new Carrera(1,"prueba",UtilDate.newDate(year,month,1));
			Carrera c2 = new Carrera(2,"100 metros",UtilDate.newDate(year,month,1));
			
			Galgo g1 = new Galgo(1,"pepe",3,c1);
			Galgo g2 = new Galgo(2,"juan",4,c1);
			
			c1.anadirGalgo(g1);
			c1.anadirGalgo(g2);

			
			db.persist(new Admin("admin", "1234"));
			db.persist(new Usuario("prueba", "1234"));
			db.persist(new Usuario("prueba2", "1234"));
			
			db.persist(g1);
			db.persist(g2);
			
			db.persist(c1);
			db.persist(c2);
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	   
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.doesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestionConId(getNumeroQuestions() + 1, question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves an Usuario from the database
	 * 
	 * @param pId the identifier of the Usuario
	 * @param pPass the pass of the Usuario
	 * @return the Admin with that pId and pPass, or null
	 */
	public Usuario getUser(String pId, String pPass) {
		System.out.println(">> DataAccess: getUser: " + pId);
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.id='" + pId + "' and us.password='" + pPass + "'", Usuario.class);
		List<Usuario> user = query.getResultList();
		if (!user.isEmpty()){
			return user.get(0);
		}
		return null;
	}
	
	/**
	 * This method retrieves an Admin from the database 
	 * 
	 * @param pId the identifier of the Admin
	 * @param pPass the pass of the Admin
	 * @return the Admin with that pId and pPass, or null
	 */
	public Admin getAdmin(String pId, String pPass) {
		System.out.println(">> DataAccess: getAdmin: " + pId);
		TypedQuery<Admin> query = db.createQuery("SELECT ad FROM Admin ad WHERE ad.id='" + pId + "' and ad.password='" + pPass + "'", Admin.class);
		List<Admin> admin = query.getResultList();
		if (!admin.isEmpty()){
			return admin.get(0);
		}
		return null;
	}
		
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	/**
	 * This method return True if an Usuario exist in the database, and false if not 
	 * 
	 * @param pId of an Usuario
	 * @param pPass of an Usuario
	 * @return an boolean
	 */
	public boolean existeUsuario(String pId, String pPass) {
		System.out.println(">> DataAccess: existeUsuario: " + pId);
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.id='" + pId + "'", Usuario.class);
		List<Usuario> user = query.getResultList();
		if(!user.isEmpty()) return true;
		else return false;
	}

	/**
	 * This method add an Usuario to the database. Return true if it was possible, false if not
	 * 
	 * @param pId of an Usuario
	 * @param pPass of an Usuario
	 * @return an boolean
	 */
	public boolean registrarUsuario(String pId, String pPass) {
		System.out.println(">> DataAccess: registrarUsuario: " + pId + " con contraseña: " + pPass);
		db.getTransaction().begin();
		try {
			db.persist(new Usuario(pId, pPass));
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method return all the Usuario from the database
	 * 
	 * @param 
	 * @return a Collection of Usuario
	 */
	public Collection<Usuario> getAllUsers() {
		System.out.println(">> DataAccess: getAllUsers");
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}

	public Collection<Carrera> getAllCarreras() {
		System.out.println(">> DataAccess: getAllCarrera");
		TypedQuery<Carrera> query = db.createQuery("SELECT ca FROM Carrera ca", Carrera.class);
		List<Carrera> carreras = query.getResultList();
		return carreras;
	}
	
	public Collection<Galgo> getAllGalgos() {
		System.out.println(">> DataAccess: getAllGalgos");
		TypedQuery<Galgo> query = db.createQuery("SELECT gal FROM Galgo gal", Galgo.class);
		List<Galgo> galgos = query.getResultList();
		return galgos;
	}
	/**
	 * This method increments the money of an Usuario 
	 * 
	 * @param pId the identifier of Usuario
	 * @param pSaldo the money as Double
	 * @return 
	 */
	public void incrementarSaldo(String pId, Double pSaldo) {
		System.out.println(">> DataAccess: incrementarSaldo de " + pId + " con " + pSaldo);
		Usuario user = getUsuarioPorId(pId);
		db.getTransaction().begin();
		user.aumentarSaldo(pSaldo);
		db.getTransaction().commit();
	}
	
	/**
	 * This method get an Usuario from the database, just searching by the id 
	 * 
	 * @param an identifier 
	 * @return an Usuario or null
	 */
	private Usuario getUsuarioPorId(String pId) {
		System.out.println(">> DataAccess: getUsuarioPorId: " + pId);
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.id='" + pId + "'", Usuario.class);
		List<Usuario> user = query.getResultList();
		if (!user.isEmpty()){
			return user.get(0);
		}
		return null;
	}

	/**
	 * This method delete an Usuario from the database
	 * 
	 * @param an Usuario 
	 * @return
	 */
	public void deleteUser(Usuario pUsuario) {
		System.out.println(">> DataAccess: deleteUser: " + pUsuario.getId());
		db.getTransaction().begin();
		db.remove(pUsuario);
		db.getTransaction().commit();
	}

	/**
	 * This method get all the Apuesta of an Usuario
	 * 
	 * @param an Usuario identifier 
	 * @return a Collection of Apuesta
	 */
	public Collection<Apuesta> getApuestasUser(String pId) {
		System.out.println(">> DataAccess: getApuestasUser de " + pId);
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.usuario.id='" + pId + "'", Apuesta.class);
		List<Apuesta> apuestas = query.getResultList();
		return apuestas;
	}
	
	public Collection<ApuestaGalgo> getApuestasGalgosUser(String pId) {
		System.out.println(">> DataAccess: getApuestasGalgosUser de " + pId);
		TypedQuery<ApuestaGalgo> query = db.createQuery("SELECT apg FROM ApuestaGalgo apg WHERE apg.usuario.id='" + pId + "'", ApuestaGalgo.class);
		List<ApuestaGalgo> apuestasGalgo = query.getResultList();
		return apuestasGalgo;
	}
	
	/**
	 * This method return all the Events of the database
	 * 
	 * @param 
	 * @return a Vector of Event
	 */
	public Vector<Event> getAllEvents() {
		System.out.println(">> DataAccess: getAllEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev ",Event.class); 
		List<Event> events = query.getResultList();
	 	 for (Event ev:events) res.add(ev);
	 	return res;
	}
	
	/**
	 * This method creates an Apuesta for an Usuario
	 * 
	 * @param a question, the answer, the money of the bet and the id of the Usuario
	 * @return
	 */
	public boolean generarApuesta(Question pQuestion, String eleccionApuesta, Double pDinero, Usuario pUsuario, Double pGanancia) {
		System.out.println(">> DataAccess: registrarApuesta");
		db.getTransaction().begin();
		try {
			db.persist(new Apuesta(pQuestion, eleccionApuesta,pDinero,pUsuario,pGanancia));
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public boolean generarApuestaGalgo(Galgo pGalgo, Double pDinero, Usuario pUsuario, Double pGanancia) {
		System.out.println(">> DataAccess: registrarApuesta");
		db.getTransaction().begin();
		try {
			db.persist(new ApuestaGalgo(pGalgo,pDinero,pUsuario,pGanancia));
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	/**
	 * This method changes the password of an Usuario
	 * 
	 * @param usuario and the new password
	 * @return
	 */
	public void cambiarContrasena(Usuario usuario, String password) {
		System.out.println(">> DataAccess: cambiarContraseña de " + usuario.getId());
		TypedQuery<Usuario> query = db.createQuery("SELECT us FROM Usuario us WHERE us.id='" + usuario.getId() + "'", Usuario.class);
		List<Usuario> user = query.getResultList();
		Usuario usu = user.get(0);
		db.getTransaction().begin();
		usu.setPassword(password);
		db.getTransaction().commit();
	}

	/**
	 * This method add a new Event to the database, return true if the Event is created, and false if not
	 * 
	 * @param a description and a date
	 * @return a boolean
	 */
	public boolean anadirEvento(String pDescripcion, Date pFecha) {
		db.getTransaction().begin();
		try {
			int numEvento = getNumeroEventos() + 1;
			db.persist(new Event(numEvento, pDescripcion, pFecha));
			db.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}		
	}
	
	public boolean anadirCarrera(String pDescripcion, Date pFecha) {
		db.getTransaction().begin();
		try {
			int numCarrera = getNumeroCarreras() + 1;
			db.persist(new Carrera(numCarrera, pDescripcion, pFecha));
			db.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}		
	}
	
	public boolean anadirGalgo(String pNombreGalgo, float betMin) {
		db.getTransaction().begin();
		try {
			int numGalgo = getNumeroGalgos() + 1;
			db.persist(new Galgo(numGalgo,pNombreGalgo,betMin));
			db.getTransaction().commit();
			return true;
		}catch(Exception e){
			return false;
		}		
	}
	/**
	 *  
	 * 
	 * @param 
	 * @return
	 */
	public int getNumeroEventos() {
		System.out.println(">> DataAccess: getNumeroEventos");
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev", Event.class);
		List<Event> e = query.getResultList();
		return e.size();
	}
	
	public int getNumeroCarreras() {
		System.out.println(">> DataAccess: getNumeroCarreras");
		TypedQuery<Carrera> query = db.createQuery("SELECT ca FROM Carrera ca", Carrera.class);
		List<Carrera> e = query.getResultList();
		return e.size();
	}
	
	public int getNumeroGalgos() {
		System.out.println(">> DataAccess: getNumeroGalgos");
		TypedQuery<Galgo> query = db.createQuery("SELECT ga FROM Galgo ga", Galgo.class);
		List<Galgo> e = query.getResultList();
		return e.size();
	}
	/**
	 * 
	 * @return
	 */
	public int getNumeroQuestions() {
		System.out.println(">> DataAccess: getNumeroQuestions");
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q", Question.class);
		List<Question> q = query.getResultList();
		return q.size();
	}
	
	public int getNumeroRespuestas() {
		System.out.println(">> DataAccess: getNumeroRespuestas");
		TypedQuery<Respuesta> query = db.createQuery("SELECT q FROM Respuesta q", Respuesta.class);
		List<Respuesta> q = query.getResultList();
		return q.size();
	}

	public boolean anadirRespuesta(Respuesta pRespuesta) {
		System.out.println(">> DataAccess: anadirRespuesta " + pRespuesta + " a la question  " + pRespuesta.getQuestionNumber() + " con un bono de ganancia de " + pRespuesta.getBonificacion().toString());
		Question q = getQuestion(pRespuesta.getQuestionNumber());
		db.getTransaction().begin();
		try {
			db.persist(pRespuesta);
			q.anadirRespuesta(pRespuesta);
			db.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public Question getQuestion(int pQuestionNumber) {
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q where q.questionNumber=" + pQuestionNumber, Question.class);
		List<Question> questions = query.getResultList();
		if(!questions.isEmpty()) {
			return questions.get(0);
		}else {
			return null;
		}
	}
	
	public boolean actualizarCarreraGalgo(Galgo galgo, Carrera carrera) {
		
		Carrera car = db.find(Carrera.class, carrera.getcarreraNumber());
		Galgo gal = db.find(Galgo.class, galgo.getGalgoNumber());
		if(gal != null){
			db.getTransaction().begin();
			try {
				gal.setCarrera(car);
				car.anadirGalgo(gal);
			    db.getTransaction().commit();
			    return true;
			}catch (Exception j) {
				System.out.println("El error es " + j.getMessage());
				j.printStackTrace();
				return false;
			}
			
		}else {
			return false;
		}
	}
	public Event getEvent(int pEventNumber) {
		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e where e.eventNumber=" + pEventNumber, Event.class);
		List<Event> events = query.getResultList();
		if(!events.isEmpty()) {
			return events.get(0);
		}else {
			return null;
		}
	}
	
	public Respuesta ResponderApuesta(Question pQuestion, Respuesta pRespuesta) {
		System.out.println(">> DataAccess: Responer Apuesta=> question= "+pQuestion+" Respuesta "+pRespuesta);
		
		Question q = db.find(Question.class, pQuestion.getQuestionNumber());	
		db.getTransaction().begin();
		Respuesta r = q.setRespuestaCorrecta(pRespuesta);
		//db.persist(q);
		db.persist(q);
		db.persist(r);
						
		db.getTransaction().commit();
		return r;
	}
	
	
	public Vector<Event> getEventsBeforeDate(Date pDate){
		System.out.println(">> DataAccess: Eventos Fecha=> Fecha= "+pDate);
		Vector<Event> res = new Vector<Event>();	 
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate<?1",Event.class);   
		query.setParameter(1,pDate);
		List<Event> events = query.getResultList();
	 	for (Event ev:events) res.add(ev);
	 	return res;
	}
	
	public Vector<Apuesta> getBetsWithQuest(Question pQuestion){
		System.out.println(">> DataAccess: Apuestas Question=> Question= "+pQuestion);
		Vector<Apuesta> apu = new Vector<Apuesta>();	
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.question.questionNumber=" +pQuestion.getQuestionNumber(),Apuesta.class); 
		List<Apuesta> apuestas = query.getResultList();
	 	for (Apuesta ap : apuestas) apu.add(ap);
	 	return apu;
	}
	
	public void setCobradaApuesta(Apuesta pApuesta) {
		System.out.println(">> DataAccess:  Cobrar Apuesta " + pApuesta);
		Apuesta ap = getApuestaPorId(pApuesta.getId());
		db.getTransaction().begin();
		ap.setCobrada();
		db.getTransaction().commit();
	}
	
	public void setCobradaApuestaGalgos(ApuestaGalgo pApuesta) {
		System.out.println(">> DataAccess:  Cobrar Apuesta " + pApuesta);
		ApuestaGalgo ap = getApuestaGalgosPorId(pApuesta.getId());
		db.getTransaction().begin();
		ap.setCobrada();
		db.getTransaction().commit();
	}
	
	private Apuesta getApuestaPorId(int pId) {
		System.out.println(">> DataAccess: getApuestaPorId: " + pId);
		TypedQuery<Apuesta> query = db.createQuery("SELECT ap FROM Apuesta ap WHERE ap.id=?1", Apuesta.class);
		query.setParameter(1,pId);
		List<Apuesta> user = query.getResultList();
		if (!user.isEmpty()){
			return user.get(0);
		}
		else {
			return null;
		}
	}
	
	private ApuestaGalgo getApuestaGalgosPorId(int pId) {
		System.out.println(">> DataAccess: getApuestaGalgoPorId: " + pId);
		TypedQuery<ApuestaGalgo> query = db.createQuery("SELECT ap FROM ApuestaGalgo ap WHERE ap.id=?1", ApuestaGalgo.class);
		query.setParameter(1,pId);
		List<ApuestaGalgo> user = query.getResultList();
		if (!user.isEmpty()){
			return user.get(0);
		}
		else {
			return null;
		}
	}
	
	public Galgo ResponderApuestaGalgos(Carrera pCarrera, Galgo pGalgo) {
		System.out.println(">> DataAccess: Responder Apuesta Galgo=> carrera= "+pCarrera+" Galgo "+pGalgo);
		
		Carrera c = db.find(Carrera.class, pCarrera.getcarreraNumber());	
		db.getTransaction().begin();
		Galgo g = c.setRespuestaCorrecta(pGalgo);
		//db.persist(q);
		db.persist(c);
						
		db.getTransaction().commit();
		return g;
	}
	
	public Vector<ApuestaGalgo> apuestasCarrera(Carrera pCarrera){
		System.out.println(">> DataAccess: Apuestas Carrera=> Carrera= "+pCarrera);
		Vector<ApuestaGalgo> apu = new Vector<ApuestaGalgo>();	
		TypedQuery<ApuestaGalgo> query = db.createQuery("SELECT ap FROM ApuestaGalgo ap",ApuestaGalgo.class); 
		List<ApuestaGalgo> apuestas = query.getResultList();
	 	for (ApuestaGalgo ap : apuestas) apu.add(ap);
	 	return apu;
	}
	
}