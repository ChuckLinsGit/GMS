package gms.entry.user;

import java.util.List;

//��װpermission��
public class Permission {
	private Integer PID;
	private Integer PLevelD;
	private String PName;
	
	private List<PAPFunction> PAPFunction;


	public List<PAPFunction> getPAPFunction() {
		return PAPFunction;
	}

	public void setPAPFunction(List<PAPFunction> pAPFunction) {
		PAPFunction = pAPFunction;
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public Integer getPLevelD() {
		return PLevelD;
	}

	public void setPLevelD(Integer pLevelD) {
		PLevelD = pLevelD;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}

	public Permission() {
		super();
	}

	public Permission(Integer PID, Integer PLevelD, String PName) {
		super();
		this.PID = PID;
		this.PLevelD = PLevelD;
		this.PName = PName;
	}
	
	public Permission(Integer PLevelD, String PName) {
		super();
		this.PLevelD = PLevelD;
		this.PName = PName;
	}
	
	@Override
	public String toString() {
		return "Permission [PID=" + PID + ", PLevelD=" + PLevelD + ", PName=" + PName + ", PAPFunction=" + PAPFunction +"]";
	}
	
}