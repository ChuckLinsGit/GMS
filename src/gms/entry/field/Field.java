package gms.entry.field;

public class Field {

	private Integer fieldid;
	private String fieldname;
	private String fieldtype;
	private String fieldinout;
	private String address;
	private String fieldstate;
	private Integer fieldlong;
	private Integer fieldwidth;
	private String fieldimg;
	private Integer unitprice;
	private String fieldaddordel;
	
	
	public Integer getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}
	public String getFieldimg() {
		return fieldimg;
	}
	public void setFieldimg(String fieldimg) {
		this.fieldimg = fieldimg;
	}
	//get set
	public Integer getFieldid() {
		return fieldid;
	}
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getFieldinout() {
		return fieldinout;
	}
	public void setFieldinout(String fieldinout) {
		this.fieldinout = fieldinout;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFieldstate() {
		return fieldstate;
	}
	public void setFieldstate(String fieldstate) {
		this.fieldstate = fieldstate;
	}
	public Integer getFieldlong() {
		return fieldlong;
	}
	public void setFieldlong(Integer fieldlong) {
		this.fieldlong = fieldlong;
	}
	public Integer getFieldwidth() {
		return fieldwidth;
	}
	public void setFieldwidth(Integer fieldwidth) {
		this.fieldwidth = fieldwidth;
	}
	public String getFieldaddordel() {
		return fieldaddordel;
	}
	public void setFieldaddordel(String fieldaddordel) {
		this.fieldaddordel = fieldaddordel;
	}
	@Override
	public String toString() {
		return "Field [fieldid=" + fieldid + ", fieldname=" + fieldname + ", fieldtype=" + fieldtype + ", fieldinout="
				+ fieldinout + ", address=" + address + ", fieldstate=" + fieldstate + ", fieldlong=" + fieldlong
				+ ", fieldwidth=" + fieldwidth + ", fieldimg=" + fieldimg + ", unitprice=" + unitprice
				+ ", fieldaddordel=" + fieldaddordel + "]";
	}
	
	
	

	
	
	
}
