package auth;

import javax.ejb.Remote;

@Remote
public interface MyNameBeanRemote {
	public String getName();
	public void setName(String name); 
}
