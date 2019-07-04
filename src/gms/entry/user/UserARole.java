package gms.entry.user;


public class UserARole {
	private Integer UARID;
	private Integer UAR_UID;
	private Integer UAR_RID;
	
	private Role Role;


	public Role getRole() {
		return Role;
	}

	public void setRole(Role role) {
		Role = role;
	}

	public Integer getUARID() {
		return UARID;
	}

	public void setUARID(Integer uARID) {
		UARID = uARID;
	}

	public Integer getUAR_UID() {
		return UAR_UID;
	}

	public void setUAR_UID(Integer uAR_UID) {
		UAR_UID = uAR_UID;
	}

	public Integer getUAR_RID() {
		return UAR_RID;
	}

	public void setUAR_RID(Integer uAR_RID) {
		UAR_RID = uAR_RID;
	}


	public UserARole() {
		super();
	}

	public UserARole(Integer UARID, Integer UAR_UID, Integer UAR_RID) {
		super();
		this.UARID = UARID;
		this.UAR_UID = UAR_UID;
		this.UAR_RID = UAR_RID;
	}
	
	public UserARole(Integer UAR_UID, Integer UAR_RID) {
		super();
		this.UAR_UID = UAR_UID;
		this.UAR_RID = UAR_RID;
	}
	
	@Override
	public String toString() {
		return "UserARole [UARID=" + UARID + ", UAR_UID=" + UAR_UID + ", UAR_RID=" + UAR_RID + ", Role=" + Role + "]";
	}
	
}