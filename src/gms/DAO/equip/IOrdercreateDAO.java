package gms.DAO.equip;

import java.util.List;

import gms.entry.equip.Ordersdetail;

public interface IOrdercreateDAO {

	public void createorders(Ordersdetail ordersdetail);//鍒涘缓璁㈠崟
	public Ordersdetail findordersbyid(Integer orders_id);//鏍规嵁id鏌ヨ璁㈠崟
	public void currstatebyid(Ordersdetail ordersdetail);//淇敼璁㈠崟鏍囧織浣�
	public List<Ordersdetail> bggetcandroporder();//鏌ヨ鍙互閫�鐨勮鍗�
	public void bgadddropmassage(Ordersdetail ordersdetail);//琚��鐢ㄦ埛娣诲姞娑堟伅鍒楄〃
/*	public List<Integer> bguserseeifmassage(Integer user_id);*///鐢ㄦ埛鏌ョ湅鏈夋病鏈夋秷鎭�
/*	public void bguserseemassage(Integer user_id);*///鐢ㄦ埛宸叉煡鐪嬫秷鎭�
	public List<Ordersdetail> noreturnontime();//绯荤粺妫�娴嬭繃鏈熸湭褰掕繕
	public List<Ordersdetail> nobookontime();//绯荤粺妫�娴嬮绾﹁繃鏈�
}
