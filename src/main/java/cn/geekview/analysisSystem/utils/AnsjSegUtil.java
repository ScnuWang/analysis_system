package cn.geekview.analysisSystem.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang.StringUtils;

public class AnsjSegUtil {
	
	/*** 国内智能硬件产品关键词列表*/
	private  static List<String> internalProductKeyWordList = new ArrayList<String>();
	/*** 国内非智能硬件产品关键词列表*/
	private  static List<String> nonInternalProductKeyWordList = new ArrayList<String>();
	/*** 国外智能硬件产品关键词列表*/
	private  static List<String> overseaProductKeyWordList = new ArrayList<String>();
	/*** 国外非智能硬件产品关键词列表*/
	private  static List<String> nonOverseaProductKeyWordList = new ArrayList<String>();
	private static boolean isInitData = false;
	
	/**
	 * 初始化关键词数据
	 * @param isImposed
	 * @return
	 */
	public static boolean initKeyWordData(boolean isImposed){
		
		try{
			if(isInitData||isImposed){
				String f1 = CommonAPI.CLASSPATH+"classes/ch";
				String f2 = CommonAPI.CLASSPATH+"classes/ch_non";
				String f3 = CommonAPI.CLASSPATH+"classes/en";
				String f4 = CommonAPI.CLASSPATH+"classes/en_non";
				/***清除关键词列表*/
				internalProductKeyWordList.clear();
				nonInternalProductKeyWordList.clear();
				overseaProductKeyWordList.clear();
				nonOverseaProductKeyWordList.clear();
				/***将关键词加入List中*/
				readFile(internalProductKeyWordList,f1);
				readFile(nonInternalProductKeyWordList,f2);
				readFile(overseaProductKeyWordList,f3);
				readFile(nonOverseaProductKeyWordList,f4);
				ToAnalysis.parse("初始化分词器");
				isInitData = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 获取关键词列表
	 * @param param
	 * @param websiteType 网站类型：1、国内     2、国外
	 * @return
	 */
	public static String getKeyword(String param,int websiteType){
		
		String keyword = "";
		if(websiteType == 1){
			keyword = getInternalKeyword(param);
		}else{
			keyword = getOverseaKeyword(param);
		}
		return keyword;
	}
	
	/**
	 * 获取国内关键词
	 * @param param
	 * @return
	 */
	private static String getInternalKeyword(String param){
		
		List<Term> listTerm = ToAnalysis.parse(param);
		String keyword = "";
		for (Term t : listTerm){
			if (StringUtils.isEmpty(t.getNatureStr())){
				continue;
			}
			if (internalProductKeyWordList.contains(t.getRealName())){
				keyword = t.getRealName().trim();
				break;
			} else if (nonInternalProductKeyWordList.contains(t.getRealName())){
				keyword = "0";
				break;
			}
		}
		return keyword;
	}
	
	/**
	 * 获取国外关键词
	 * @param param
	 * @return
	 */
	private static String getOverseaKeyword(String param){
		
		List<Term> listTerm = ToAnalysis.parse(param);
		String keyword = "";
		for (Term t : listTerm){
			if (StringUtils.isEmpty(t.getNatureStr())){
				continue;
			}
			if (overseaProductKeyWordList.contains(t.getRealName())){
				keyword = t.getRealName().trim();
				break;
			}else if (nonOverseaProductKeyWordList.contains(t.getRealName())){
				keyword = "0";
				break;
			}
		}
		return keyword;
	}
	
	/**
	 * 读取知道的文件，并将每一行的数据放入集合中
	 * @param collection
	 * @param filePath
	 * @throws Exception
	 */
	private static void readFile(Collection<String> collection, String filePath) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		String line = null;
		while ((line = br.readLine()) != null){
			if (StringUtils.isEmpty(line)){
				continue;
			}
			collection.add(line.trim());
		}
		br.close();
	}
}
