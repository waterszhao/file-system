package bean;

public class UserInfoBean {
  private String userName;
  private String psw;
  private int request;
  private int id;
  public String getUserName() {
    return userName;
  }
  public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getRequest() {
	return request;
}
public void setRequest(int request) {
	this.request = request;
}
public String getPsw() {
    return psw;
  }
  public void setPsw(String psw) {
    this.psw = psw;
  }
}