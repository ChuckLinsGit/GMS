package gms.entry.user;

public class PAPFunction {
	private Integer PAPID;
	private Integer PID;
	private Integer PFID;

    private PFunction PFunction;

	public PFunction getPFunction() {
		return PFunction;
	}

	public void setPFunction(PFunction pFunction) {
		PFunction = pFunction;
	}

	public Integer getPAPID() {
		return PAPID;
	}

	public void setPAPID(Integer pAPID) {
		PAPID = pAPID;
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public Integer getPFID() {
		return PFID;
	}

	public void setPFID(Integer pFID) {
		PFID = pFID;
	}

	public PAPFunction() {
		super();
	}

	public PAPFunction(Integer PAPID, Integer PID, Integer PFID) {
		super();
		this.PAPID = PAPID;
		this.PID = PID;
		this.PFID = PFID;
	}
	
	public PAPFunction(Integer PID, Integer PFID) {
		super();
		this.PID = PID;
		this.PFID = PFID;
	}
	
	@Override
	public String toString() {
		return "PAPFunction [PAPID=" + PAPID + ", PID=" + PID + ", PFID=" + PFID + ", PFunction=" + PFunction + "]";
	}
	
}