package gms.entry.field;

public class Regulation {

	private Integer regulationid;
	private String regcontent;
	private String viodispose;
	private String regname;
	private String regstate;
	
	//get set
	public Integer getRegulationid() {
		return regulationid;
	}
	public void setRegulationid(Integer regulationid) {
		this.regulationid = regulationid;
	}
	public String getRegstate() {
		return regstate;
	}
	public void setRegstate(String regstate) {
		this.regstate = regstate;
	}
	public String getRegcontent() {
		return regcontent;
	}
	public void setRegcontent(String regcontent) {
		this.regcontent = regcontent;
	}
	public String getViodispose() {
		return viodispose;
	}
	public void setViodispose(String viodispose) {
		this.viodispose = viodispose;
	}
	
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	@Override
	public String toString() {
		return "Regulation [regulationid=" + regulationid + ", regcontent=" + regcontent + ", viodispose=" + viodispose
				+ ", regname=" + regname + ", regstate=" + regstate + "]";
	}
	
	
	
	
	
}
