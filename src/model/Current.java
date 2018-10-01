package model;

public class Current {

	private static Current session;
	public String user;
	public String sender;
	public String recipient;
	public String subject;
	public String message;
	public Long stamp; 
	public String compose_type;
	public int is_draft;
	public String formatted_mail;
	
	
	private Current(){
		
	}
	
	public void clearSession(){
		session.user = "";
		session.sender = "";
		session.recipient = "";
		session.subject = "";
		session.message = "";
		session.stamp = 0L;
		session.compose_type = "";
		session.is_draft = 0;
		session.formatted_mail = "";
	}
	
	public static synchronized Current getSession() {
	      if (session == null) {
	         session = new Current();
	      }
	      return session;
	   }
}
