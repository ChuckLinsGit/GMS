package gms.entry.user;

import java.util.List;


//��װrole��
public class Role {
	private Integer RID;
	private String RType;
	private String RName;
	
	private List<RoleAPermission> RoleAPermission;
	
	public List<RoleAPermission> getRoleAPermission() {
		return RoleAPermission;
	}

	public void setRoleAPermission(List<RoleAPermission> roleAPermission) {
		RoleAPermission = roleAPermission;
	}

	public Integer getRID() {
		return RID;
	}

	public void setRID(Integer rID) {
		RID = rID;
	}

	public String getRType() {
		return RType;
	}

	public void setRType(String rType) {
		RType = rType;
	}

	public String getRName() {
		return RName;
	}

	public void setRName(String rName) {
		RName = rName;
	}

	public Role() {
		super();
	}

	public Role(Integer RID, String RType, String RName) {
		super();
		this.RID = RID;
		this.RName = RName;
		this.RType = RType;
	}
	
	public Role(String RType, String RName) {
		super();
		this.RName = RName;
		this.RType = RType;
	}
	
	@Override
	public String toString() {
		return "Role [RID=" + RID + ", RName=" + RName + ", RType=" + RType + ", RoleAPermission=" + RoleAPermission + "]";
	}
	
}