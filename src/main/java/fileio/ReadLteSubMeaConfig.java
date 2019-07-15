package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ȡ�����ļ�QETXT.INI
 * 
 * @author heli
 * 
 */
public class ReadLteSubMeaConfig {

	private static ReadLteSubMeaConfig instance = new ReadLteSubMeaConfig();

	// Map<object,Map<subFileName,Map<subColumnName,subColumnValue>>>
	// private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String,
	// String>>> LteQeTxt = null;
	// private LinkedHashMap<String, LinkedHashMap<String, String>> subFile =
	// null;
	// private LinkedHashMap<String, String> nameAndValue = null;

	// private LinkedHashMap<String,LinkedList<String>> colNames = null;
	// private LinkedHashMap<String,LinkedList<String>> colTypes = null;

	// ˽�л����췽����ʵ�ֵ���ģʽ
	private ReadLteSubMeaConfig() {

	}

	public static ReadLteSubMeaConfig getInstance() {
		return instance;
	}

	
	/**
	 * 
	 * @param String configPath
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> Read(
			String configPath) {

		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> LteSubMea = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();
		LinkedHashMap<String, LinkedHashMap<String, String>> subMea = null;
		LinkedHashMap<String, String> nameAndValue = null;
		
		File file = new File(configPath);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			String strFind = "";
			String colName = "";
			Pattern p1 = Pattern.compile("^\\s*FILE=(\\S+)");
			Pattern p2 = Pattern
					.compile("^\\s*FIELD\\d*=(\\S+),(\\w+).*");
			Pattern p3 = Pattern.compile("^\\[(\\w+)\\]");
			
			Matcher m = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				m = p3.matcher(line);
				if (m.find()) {
					strFind = m.group(1).toUpperCase();
					if (!LteSubMea.containsKey(strFind)) {
						subMea = new LinkedHashMap<String, LinkedHashMap<String, String>>();
						LteSubMea.put(strFind, subMea);
						
					}
				}
				m = p1.matcher(line);
				if (m.find()) {
//					System.out.println("find");
					strFind = m.group(1).toUpperCase();
//					System.out.println(strFind);
					nameAndValue = new LinkedHashMap<String, String>();
					subMea.put(strFind, nameAndValue);
				}

				m = p2.matcher(line);
				if (m.find()) {
//					System.out.println("find");
					colName = m.group(1);
//					System.out.println(colName);
					nameAndValue.put(colName, "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					file = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return LteSubMea;

	}
	
	/**
	 * 
	 * @param File configFile
	 * @return
	 */
	
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> Read(
			File configFile) {

		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> LteSubMea = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();
		LinkedHashMap<String, LinkedHashMap<String, String>> subMea = null;
		LinkedHashMap<String, String> nameAndValue = null;
		
//		File file = new File(adapterPath);

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(configFile));
			String line = null;

			String strFind = "";
			String colName = "";
			Pattern p1 = Pattern.compile("^\\s*FILE=(\\S+)");
			Pattern p2 = Pattern
					.compile("^\\s*FIELD\\d*=(\\S+),(\\w+).*");
			Pattern p3 = Pattern.compile("^\\[(\\w+)\\]");
			
			Matcher m = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				m = p3.matcher(line);
				if (m.find()) {
					strFind = m.group(1).toUpperCase();
					if (!LteSubMea.containsKey(strFind)) {
						subMea = new LinkedHashMap<String, LinkedHashMap<String, String>>();
						LteSubMea.put(strFind, subMea);
						
					}
				}
				m = p1.matcher(line);
				if (m.find()) {
//					System.out.println("find");
					strFind = m.group(1).toUpperCase();
//					System.out.println(strFind);
					nameAndValue = new LinkedHashMap<String, String>();
					subMea.put(strFind, nameAndValue);
				}

				m = p2.matcher(line);
				if (m.find()) {
//					System.out.println("find");
					colName = m.group(1).toUpperCase();
//					System.out.println(colName);
					nameAndValue.put(colName, "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					configFile = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return LteSubMea;

	}

}
