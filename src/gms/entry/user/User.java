package gms.entry.user;

import java.util.List;


//��װuser��
public class User {
	private Integer UID;
	private String UName;
	private String UAccountID;
	private String UPassword;
	private String UEmail;
	private String UPhone;
	
	private List<UserARole> UserARole;

	public List<UserARole> getUserARole() {
		return UserARole;
	}

	public void setUserARole(List<UserARole> userARole) {
		UserARole = userARole;
	}

	public User() {
		super();
	}

	public User(Integer UID, String UName, String UAccountID, String UPassword,
			String UEmail,String UPhone) {
		super();
		this.UID = UID;
		this.UName = UName;
		this.UAccountID = UAccountID;
		this.UPassword = UPassword;
		this.UEmail = UEmail;
		this.UPhone = UPhone;
	}
	
	public User( String UName, String UAccountID, String UPassword,
			String UEmail,String UPhone) {
		super();
		this.UName = UName;
		this.UAccountID = UAccountID;
		this.UPassword = UPassword;
		this.UEmail = UEmail;
		this.UPhone = UPhone;
	}
	
	public User(String UAccountID,String UEmail,String UPhone) {
		super();
		this.UAccountID = UAccountID;
		this.UEmail = UEmail;
		this.UPhone = UPhone;
	}
	
	public User( String UAccountID, String UPassword) {
		super();
		this.UAccountID = UAccountID;
		this.UPassword = UPassword;
	}
	
	public User( Integer UID) {
		super();
		this.UID = UID;
	}
	
	public Integer getUID() {
		return UID;
	}

	public void setUID(Integer uID) {
		UID = uID;
	}

	public String getUName() {
		return UName;
	}

	public void setUName(String uName) {
		UName = uName;
	}

	public String getUAccountID() {
		return UAccountID;
	}

	public void setUAccountID(String uAccountID) {
		UAccountID = uAccountID;
	}

	public String getUPassword() {
		return UPassword;
	}

	public void setUPassword(String uPassword) {
		UPassword = uPassword;
	}

	public String getUEmail() {
		return UEmail;
	}

	public void setUEmail(String uEmail) {
		UEmail = uEmail;
	}

	public String getUPhone() {
		return UPhone;
	}

	public void setUPhone(String uPhone) {
		UPhone = uPhone;
	}
	

	@Override
	public String toString() {
		return "User [UID=" + UID + ", UName=" + UName + ", UAccountID=" + UAccountID + 
				", UPassword=" + UPassword + ", UEmail=" + UEmail + ", UPhone=" + UPhone + ", UserAROle=" + UserARole + "]";
	}
	
}