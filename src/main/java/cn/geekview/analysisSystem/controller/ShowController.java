package cn.geekview.analysisSystem.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.geekview.analysisSystem.entity.model.TdreamCategory;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;
import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.service.ICommonService;
import cn.geekview.analysisSystem.utils.CommonAPI;
import cn.geekview.analysisSystem.utils.CrateHtmlUtil;
import cn.geekview.analysisSystem.utils.HttpUtil;


@Controller
@RequestMapping("/show")
public class ShowController {
	
	private static final Logger logger = LogManager.getLogger(ShowController.class.getName());
	
	@Autowired
	private ICommonService commonService;
	
	/**
	 * 展示一个平台所有的智能硬件产品
	 * @param websiteCode
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/website/{websiteCode}")
	public String showProductListBywebsiteCode(@PathVariable("websiteCode") Integer websiteCode){
		List<TdreamProduct> productList = commonService.getProductListByWebsiteCode(websiteCode);
		Map<String,List> mapParm = new HashMap<String,List>();
		mapParm.put("productList", productList);
		int createResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "productList.ftl", CommonAPI.CLASSPATH + "/html/", "productList.html", mapParm);
		if(createResult==1){
			return "productList";
		}else{
			return "error";
		}
	}
	/**
	 * 展示所有的一级分类
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/categorylist")
	public String showCategoryList(){
		List<TdreamCategory> categoryList = commonService.getCategoryList();
		Map<String,List> mapParm = new HashMap<String,List>();
		mapParm.put("categoryList", categoryList);
		int createResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "categoryList.ftl", CommonAPI.CLASSPATH + "/html/", "categoryList.html", mapParm);
		if(createResult==1){
			return "categoryList";
		}else{
			return "error";
		}
	}
	/**
	 * 展示某一个一级分类下的二级分类(competitor表)
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/category/{categoryId}")
	public String showCategory(@PathVariable("categoryId") Integer categoryId){
		List<TdreamCompetitor> competitorList = commonService.getCompetitorByCategoryId(categoryId);
		Map<String,List> mapParm = new HashMap<String,List>();
		mapParm.put("competitorList", competitorList);
		int createResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "competitorList.ftl", CommonAPI.CLASSPATH + "/html/", "competitorList.html", mapParm);
		if(createResult==1){
			return "competitorList";
		}else{
			return "error";
		}
	}
	
	/**
	 * 根据二级分类的编号，找到属于这个分类下的所有产品(t_dream_product_cate)
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/product/{competitorId}")
	public String showProductBycompetitorId(@PathVariable("competitorId")Integer competitorId){
		List<TdreamProduct> productList = commonService.getProductListByCompetitorId(competitorId);
		Map<String,List> mapParm = new HashMap<String,List>();
		mapParm.put("productList", productList);
		int createResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "productList.ftl", CommonAPI.CLASSPATH + "/html/", "productList.html", mapParm);
		if(createResult==1){
			return "productList";
		}else{
			return "error";
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/product")
	public String showProductByKeyword(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		if(StringUtils.isEmpty(keyword)){
			//跳转到首页
		}
		List<TdreamProduct> productList = commonService.getProductListByKeyword(keyword);
		Map<String,List> mapParm = new HashMap<String,List>();
		mapParm.put("productList", productList);
		int createResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "productList.ftl", CommonAPI.CLASSPATH + "/html/", "productList.html", mapParm);
		if(createResult==1){
			return "productList";
		}else{
			return "error";
		}
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request,Model model){
		logger.info(request.getAttributeNames());
		logger.info(request.getRemoteAddr());
		logger.info(request.getHeaderNames());
		logger.info(request.getParameterMap());
		return "error";
	}
	
	
	//=================以下接口供小程序使用==================
	
	/**
	 * 返回一级分类列表
	 * @return
	 */
	@RequestMapping("/wxcatetorylist")
	@ResponseBody
	public List<TdreamCategory> catetorylist(){
		List<TdreamCategory> categoryList = commonService.getCategoryList();
		return categoryList;
	}
	
	/**
	 * 返回单一的数据结果集
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/wxsingledatas")
	@ResponseBody
	public Map singledatas(){
		Map maprms = new HashMap();
		DateTime date = new DateTime();
		Integer productcount = commonService.selectProductCount(new DateTime(date.getYear(),date.getMonthOfYear(),date.getDayOfMonth(),12,0,0).plusDays(-2).toDate());//全平台总项目数
		List<TdreamProduct> hotlist = commonService.selectHotProduct(new Date(), 7);//热门项目
		maprms.put("productcount", productcount);
		/*
		 * 由于微信客户端不支持自动为图片的地址添加http通信协议，为了统一，将没有通信协议的图片地址添加http协议
		 */
		HttpUtil.addHttpProtocol(hotlist);
		maprms.put("hotproducts", hotlist);
		return maprms;
	}
	
	/**
	 * 产品分类关键字模糊查询
	 */
	@RequestMapping("/searchproduct")
	@ResponseBody
	public List<TdreamProduct> searchProductByKeyword(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		if(StringUtils.isEmpty(keyword)){
			return null;
		}
		 List<TdreamProduct> productListByKeyword = commonService.getProductListByKeyword(keyword);
		 HttpUtil.addHttpProtocol(productListByKeyword);
		 return productListByKeyword;
	}
	
	/**
	 * 根据产品编号查询产品详情
	 */
	@RequestMapping("/productdetail")
	@ResponseBody
	public TdreamProduct productDetail(HttpServletRequest request){
		String pkId = request.getParameter("pkId");
		if(StringUtils.isEmpty(pkId)){
			return null;
		}
		TdreamProduct tdreamProduct = commonService.getProductById(Integer.parseInt(pkId));
		HttpUtil.addHttpProtocol(tdreamProduct);
		return tdreamProduct;
	}
	
	/**
	 * 查询连续7天的已筹金额以及每天的净增额的变化
	 * 查询连续7天的总支持人数以及每天的净增支持人数的变化
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/productchange")
	@ResponseBody
	public Map getProductChange(HttpServletRequest request){
		String productId = request.getParameter("pkId");
		if(StringUtils.isEmpty(productId)){
			return null;
		}
		Map productChange = commonService.getProductChange(Integer.parseInt(productId));
		return productChange;
	}
	
	/**
	 * 众筹百科的文章内容
	 */
	@RequestMapping("/getacticle")
	@ResponseBody
	public Object getActicle(HttpServletRequest request){
		String articleId = request.getParameter("id");
		if(!StringUtils.isEmpty(articleId)){
			return commonService.getActicle(Integer.parseInt(articleId));
		}else{
			//获取文章列表
			return commonService.getActicleTilttle();
		}
	}
	
	/**
	 * 根据产品编号
	 * 	返回改产品的竞争者产品列表
	 * 	返回行业统计
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getcompetitor")
	@ResponseBody
	public Map getCompetitor(HttpServletRequest request){
		String articleId = request.getParameter("pkId");
		if(!StringUtils.isEmpty(articleId)){
			return commonService.getCompetitor(Integer.parseInt(articleId));
		}else{
			//获取文章列表
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		DateTime date = new DateTime();
		System.out.println(new DateTime(date.getYear(),date.getMonthOfYear(),date.getDayOfMonth(),12,0,0).plusDays(-1).toDate());
	}
}
