package gms.entry.user;

//��װpfunction��
public class PFunction {
	private Integer PFID;
	private String PFName;
	private String PFDetails;
	
	public Integer getPFID() {
		return PFID;
	}

	public void setPFID(Integer pFID) {
		PFID = pFID;
	}

	public String getPFName() {
		return PFName;
	}

	public void setPFName(String pFName) {
		PFName = pFName;
	}

	public String getPFDetails() {
		return PFDetails;
	}

	public void setPFDetails(String pFDetails) {
		PFDetails = pFDetails;
	}

	public PFunction() {
		super();
	}

	public PFunction(Integer PFID, String PFName, String PFDetails) {
		super();
		this.PFID = PFID;
		this.PFName = PFName;
		this.PFDetails = PFDetails;
	}
	
	public PFunction(String PFName, String PFDetails) {
		super();
		this.PFName = PFName;
		this.PFDetails = PFDetails;
	}
	
	@Override
	public String toString() {
		return "PFunction [PFID=" + PFID + ", PFName=" + PFName + ", PFDetails=" + PFDetails + "]";
	}
	
}