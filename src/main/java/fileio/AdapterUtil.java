package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ����������Ĺ��߼���
 * @author heli 2012-09-10
 *
 */

public class AdapterUtil {
	private static AdapterUtil instance = new AdapterUtil();
	
	private AdapterUtil(){}
	
	public static void main(String[] args) {
		System.out.println(AdapterUtil.class.getResource("../colNameCutConfig.ini"));
	}
	/**
	 * ���쳣��ջ��Ϣת�����ַ���
	 * @param t
	 * @return
	 */
	public  static String getStackTrace(Throwable t){ 
		StringWriter sw = new StringWriter();       
		PrintWriter pw = new PrintWriter(sw, true);   
		try{
			t.printStackTrace(pw);       
			pw.flush();      
			sw.flush(); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pw.close();
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sw.toString();
	}
	
	/**
	 * ���������е������ַ�
	 * @param columnName
	 * @return
	 */
	public String ColumnNameFormat(String columnName) {
		if (columnName != null) {
			columnName = columnName.trim();
			columnName = columnName.replaceAll("&gt;&lt;", ".");
			columnName = columnName.replaceAll("&gt;", "");
			columnName = columnName.replaceAll("&lt;", "");
			columnName = columnName.replaceAll("\\[", "");
			columnName = columnName.replaceAll("\\]", "");
			columnName = columnName.replaceAll("\\.", "_");

			return columnName;
		} else {
			return "";
		}
	}
	
	/**
	 * ����ֵ�е������ַ�
	 * @param value
	 * @return
	 */
	public  String valueFormat(String value){
		if(value == null){
			return "";
		}else{
			String res = value.trim();
			
			res = res.replaceAll("\\\\", ""); //�滻б��"\"
			
			res = res.replaceAll("\n", "");    //�滻����
			
			res = res.replaceAll("\\|", "_");  //�滻����"|",��Ϊ�ɼ�ƽ̨Ĭ�ϵ��ֶηָ���Ϊ���� 
			
			if(res.equalsIgnoreCase("NIL") || res.equalsIgnoreCase("NULL")){
				res = "";
			}
			
			return res;
		}
	}
	
	/**
	 * �����ϻ��б���ʽ���ַ���
	 * @param value 
	 * @return String
	 */
	
	public String colletionValueHandle(String value){
		String res = "";
		Pattern p = Pattern.compile("^\\{(.*)\\}$");//ƥ�������
		Matcher m = p.matcher(value);
		if(m.find()){
			value = m.group(1);//ȥ��������
			String[] temp = value.split(",");
			p = Pattern.compile("^\\\"(.*)\\\"$");//ƥ��˫����
			for(int i=0;i<temp.length;i++){
				m = p.matcher(temp[i]);
				if(m.find()){
					temp[i] = m.group(1).trim();//ȥ��˫����
				}
			}
			
			for(String s : temp){
				res = res+s+",";
			}
			if(res.contains(","))res = res.substring(0, res.length()-1);
		}
		return res;
	}
	
	/**
	 * ������"2011-12-08T18:00:00+08:00"ʱ���ַ���
	 * @param timeString
	 * @return String
	 */
	public String TimeString(String timeString){
		if(timeString == null){
			return "";
		}else{
			timeString = timeString.replace("T", " ");
			if(timeString.contains("+")){
				timeString = timeString.substring(0, timeString.indexOf("+"));
			}
			return timeString;
		}
	}
	
	/**
	 * �����ļ���ȡ
	 * @param file
	 * @return HashMap
	 * @author heli 2012-09-12
	 */
	public HashMap<String, ArrayList<String>> ReadConfig(File file){
		HashMap<String,ArrayList<String>> resMap = new LinkedHashMap<String, ArrayList<String>>();
		BufferedReader reader = null;
		// ��������Ԫ����ͬ���ӽڵ����һ���б���
		ArrayList<String> list = null;
		
		String father = null;
		String[] tempArr = null;
		
		Pattern p = Pattern.compile("^\\s*\\[(\\S+)\\]\\s*");
		Matcher m = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if ("".equals(line) || line.startsWith("#")){
					continue;
				}
				m = p.matcher(line);
				
				if(m.find()){
					//ƥ��[]����ʾ��ʼ��ȡһ���������������
					list = new ArrayList<String>();
					father = m.group(1).toUpperCase();
					resMap.put(father, list);
					continue;
				}
				
				tempArr = line.split(",");
				if (tempArr.length > 0) {
					list.add(tempArr[1].toUpperCase()); // ���������Ԫ�ؽ��б�
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return resMap;		
	}

	/**
	 * �������������ļ���ȡ
	 * @param file
	 * @return HashMap
	 * @author heli 2012-09-12
	 */
	public HashMap<String, HashMap<String,String>> ReadColumnNameCutConfig(File file){
		HashMap<String,HashMap<String,String>> resMap = new LinkedHashMap<String, HashMap<String,String>>();
		BufferedReader reader = null;
		// ��������Ԫ����ͬ���ӽڵ����һ���б���
		HashMap<String,String> smap = null;
		
		String father = null;
		String[] tempArr = null;
		
		Pattern p = Pattern.compile("^\\s*\\[(\\S+)\\]\\s*");
		Matcher m = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if ("".equals(line) || line.startsWith("#")){
					continue;
				}
				m = p.matcher(line);
				
				if(m.find()){
					//ƥ��[]����ʾ��ʼ��ȡһ���������������
					smap = new LinkedHashMap<String,String>();
					father = m.group(1).toUpperCase();
					resMap.put(father, smap);
					continue;
				}
				
				tempArr = line.split(",");
				if (tempArr.length > 0) {
					smap.put(tempArr[0].toUpperCase().trim(),tempArr[1].toUpperCase().trim()); // ��ӳ�������ӳ��
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return resMap;		
	}
	
	public static AdapterUtil getInstance() {
		return instance;
	}

	public String list2String(List<String> list) {
		String res = "";
		for(int i=0; i<list.size(); i++){
			res = res+list.get(i)+"|";
		}
		res = res.substring(0, res.length()-1);
		return res;
	}
	

}
