package auth;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Platform;

public class SessSing {
	private static SessSing instance = null;
	
	private static ServerSessionRemote bean;
	//NOTE: InitialContext MUST have same scope as the bean variable
	private InitialContext context;
	
	private SessSing() {
		Properties props = new Properties();
		//use the jboss factory for context to lookup the EJB remote methods 
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		//URL is the jboss server; port 8080 is jboss default for remote corba access 
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		//below statement triggers the creation of a EJBClientContext containing a EJBReceiver capable of handling the EJB invocations 		
		props.put("jboss.naming.client.ejb.context", "true");
		try {
			//create and save context as instance var
			context = new InitialContext(props);
			//grab ref to bean’s remote interface
			bean = (ServerSessionRemote) context.lookup("MyEJB/ServerSession!auth.ServerSessionRemote");
		} catch (NamingException e) {
			e.printStackTrace();
			Platform.exit();
		}	
	}
	
	public static SessSing getInstance() {
		if(instance == null) {
			instance = new SessSing();
		}
		
		return instance;
	}
	
	public static void setId(int id) {
		bean.setSessionId(id);
	}
	
	public static int getId() {
		return bean.getId();
	}
	
	public static void setUsername(String name) {
		bean.setUsername(name);
	}
	
	public static String getUsername() {
		return bean.getUsername();
	}

}
