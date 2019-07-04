package gms.entry.user;


public class RoleAPermission {
	private Integer RAPID;
	private Integer RID;
	private Integer PID;
	
	private Permission Permission;
	

	public Permission getPermission() {
		return Permission;
	}

	public void setPermission(Permission permission) {
		Permission = permission;
	}

	public Integer getRAPID() {
		return RAPID;
	}

	public void setRAPID(Integer rAPID) {
		RAPID = rAPID;
	}

	public Integer getRID() {
		return RID;
	}

	public void setRID(Integer rID) {
		RID = rID;
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public RoleAPermission() {
		super();
	}

	public RoleAPermission(Integer RAPID, Integer RID, Integer PID) {
		super();
		this.RAPID = RAPID;
		this.PID = PID;
		this.RID = RID;
	}
	
	public RoleAPermission(Integer RID, Integer PID) {
		super();
		this.PID = PID;
		this.RID = RID;
	}
	
	@Override
	public String toString() {
		return "RoleAPermission [RAPID=" + RAPID + ", PID=" + PID + ", RID=" + RID + ", Permission=" + Permission + "]";
	}
	
}