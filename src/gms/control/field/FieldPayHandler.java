package gms.control.field;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gms.entry.field.PayMent;
import gms.service.field.FieldPayService;

@RequestMapping("/pay")
@Controller
public class FieldPayHandler {

	@Resource(name="FieldPayServiceImpl")
	private FieldPayService fieldPayService;
	
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/updateStatePay.do")
	public String updateStatePay(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String needToReturn;
		String temp = req.getParameter("payid");
		if(temp!=null&&temp.length()>0) {
			PayMent payment = getPayMentFromPostForm(req);
			Integer payid = Integer.parseInt(temp);
			//更改支付状态
			fieldPayService.paidPay(payid);
			//创建金额信息
			//fieldPayService.addPayMent(payment);
			//System.out.println(fieldid+"is Del");
			needToReturn="Success to UpdatePayState";
		}else {
			needToReturn="Fail to UpdatePayState";
		}
		return needToReturn;
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/delPay.do")
	public String delPay(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String needToReturn;
		String temp = req.getParameter("payid");
		if(temp!=null&&temp.length()>0) {
			Integer payid = Integer.parseInt(temp);
			fieldPayService.delPay(payid);
			//System.out.println(fieldid+"is Del");
			needToReturn="Success to delPay";
		}else {
			needToReturn="Fail to delPay";
		}
		return needToReturn;
	}
	
	public PayMent getPayMentFromPostForm(HttpServletRequest req) {
		PayMent payment = new PayMent();
		
		String payid = req.getParameter("payid");
		if(payid!=null&&payid.length()>0)
			payment.setPayid(Integer.parseInt(payid));
		//String returnid = req.getParameter("returnid");
			//payment.setPayid(Integer.parseInt(payid));
			
		String money = req.getParameter("money");
		if(money!=null&&money.length()>0)
			payment.setMoney(Float.valueOf(money));
		
		return payment;
		
	}
}
