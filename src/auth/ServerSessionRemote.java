package auth;

import javax.ejb.Remote;

@Remote
public interface ServerSessionRemote {
	public void setSessionId(int id);
	public int getId();
	public void setUsername(String name);
	public String getUsername();
}
