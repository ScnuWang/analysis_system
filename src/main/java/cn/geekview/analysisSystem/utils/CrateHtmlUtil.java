package cn.geekview.analysisSystem.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
/**
 * 将Map格式的数据填充到ftl模板中，然后生成Html文件  成功返回1，异常返回-1
 * @author Jason
 * templatePath：模板的路径
 * templateName：模板的名称
 * htmlPath：生成Html的路径
 * htmlName：生成Html的名称
 * mapParm：模板填充的参数
 */
public class CrateHtmlUtil {
	public static int createHTML(String templatePath,String templateName,String htmlPath,String htmlName, Map<?,?> mapParm){  
		try {  
			  Configuration config = new Configuration(Configuration.VERSION_2_3_23);  
			  //设置要解析的模板所在的目录，并加载模板文件  
			  config.setDirectoryForTemplateLoading(new File(templatePath));  
			  //设置包装器，并将对象包装为数据模型  
			  config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));  
			     
			  //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
			  //否则会出现乱码  
			   Template template=config.getTemplate(templateName,"UTF-8"); 
			   File file = new  File(htmlPath);
			   if(!file.exists()){
				   file.mkdirs();
			   };
			   htmlPath = htmlPath + htmlName;
			   file = new  File(htmlPath);
			   if(!file.exists()){
				   file.createNewFile();
			   };
			   //合并数据模型与模板  
			   FileOutputStream fos = new FileOutputStream(file);  
			   Writer out = new OutputStreamWriter(fos,"UTF-8");  
			   template.process(mapParm, out);  
			   out.flush();  
			   out.close();  
		  } catch (Exception e) {  
			  e.printStackTrace();  
			  return -1;
		  }
		return 1;
	 }
}
