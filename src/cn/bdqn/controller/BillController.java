package cn.bdqn.controller;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bdqn.pojo.Bill;
import cn.bdqn.pojo.Provider;
import cn.bdqn.service.BillService;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageBean;

@Controller
@RequestMapping("/bill")
public class BillController {
	@Autowired
	private  BillService billService;
	@Autowired
	private  ProviderService providerService;
	//查询所有
	@RequestMapping("/showAll")
	public String showBill(Model model){
		List<Bill> billList =billService.findAll();
		model.addAttribute("billList", billList);
		return "billlist";
	}
	//分页查询
	@RequestMapping("/findByPage")
	public String findByPage(
			@RequestParam(value="pageNo",required=false,defaultValue="1"  ) Integer pageNo,
			@RequestParam(value="queryProductName",required=false, defaultValue="" ) String queryProductName,
			@RequestParam(value="queryProviderId",required=false, defaultValue="0" ) Integer queryProviderId,
			@RequestParam(value="queryIsPayment",required=false, defaultValue="0" ) Integer queryIsPayment,
			Model model
			){
		if(queryProductName!=null&&!queryProductName.equals("")){
			try {
				queryProductName=new String(queryProductName.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int pageSize=5;
		PageBean<Bill> billList=billService.findBypage(pageNo, pageSize, queryProductName, queryProviderId, queryIsPayment);
		List<Provider> providerList=providerService.findAll();
		model.addAttribute("billList", billList);
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment", queryIsPayment);
		return "billlist";
	}
	
	//拦截新增请求
	@RequestMapping("/billadd")
	public String  showBillAdd(){
		return "billadd";
	}
	//ajax遍历供应商
	@ResponseBody
	@RequestMapping(value="/showpro", method=RequestMethod.POST)
	public List<Provider> showPro(){
		List<Provider> prolist=providerService.findAll();
		return prolist;
	}
	
	//提交新增商品
	@RequestMapping("/addBill")
	public String addBill(Bill bill){
		int ret=billService.addBill(bill);
		if(ret>0){
			return "addbillsuccess";
		}else{
			return "billadd";
		}
	}
	//删除	
	@ResponseBody
	@RequestMapping("/delbill/{billid}")
	public Object delBill(@PathVariable Integer billid){
		Map<String , Object> map=new HashMap<String,Object>();
		Bill bill=billService.findById(billid);
		if(bill==null){
			map.put("delResult", "notexist");
		}else{
			int ret=billService.delBill(billid);
			if(ret>0){
				map.put("delResult", "true");
			}else{
				map.put("delResult", "false");
			}
		}
		return map;
		
	}
	
}

