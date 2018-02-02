package com.dayuan.handler;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.dayuan.controller.BaseController;






public class HandlerMapping {
	
	private static final Map<String, Object> objectMapp = new HashMap<String, Object>();
	
	public static Object getController(String key) {
		return objectMapp.get(key);
	}
	
	public static void loadClassFile(String rootPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String classPath = "/WEB-INF/classes/";
		
		//TODO  �������Ը��Ի�
		String packagePath = "com/dayuanit/atm/controller";
		
		String filePath = rootPath + classPath + packagePath;
	
		File file = new File(filePath);
		
		File[] classFileArray = file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith("class")) {
					return true;
				}
				
				return false;
			}
		});
		
		//com.dayuanit.atm.controller
		
		for (File f : classFileArray) {
			String fileName = f.getName();// �����ɴ˳���·������ʾ���ļ���Ŀ¼�����ơ�
			String packageName = packagePath.replace(File.separator, ".") + ".";
			packageName += fileName.substring(0, fileName.length() - 6);
			System.out.println(">>>" + packageName);
			
			Class clazz = Class.forName(packageName);//��������и����ַ����������ӿ�������� Class ����
			
			if (Modifier.isInterface(clazz.getModifiers())) {//�ӿ�
				continue;
			}
			
			if (Modifier.isAbstract(clazz.getModifiers())) {
				continue;
			}
			
			Object controllerObject = clazz.newInstance();//���� Class ��������ʾ�����һ����ʵ����
			String[] parray = clazz.getName().split("\\.");
			objectMapp.put(parray[parray.length - 1], controllerObject);
		}
		
		Iterator<String> iterator = objectMapp.keySet().iterator();
		Set<Map.Entry<String, Object>> set = objectMapp.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			Object obj = objectMapp.get(iterator.next());
			System.out.println(">>>>>>>>>>>>[" + entry.getKey() + "," + entry.getValue().getClass().getName() + "]");
		}
		
		
		
	}
	
	public static void main(String[] args) {
		String packagePath = "com/dayuanit/atm/controller";
		String packageName = packagePath.replace("/", ".");
		
		System.out.println(packageName);
		
		System.out.println(String.class.getName());
		
		System.out.println(BaseController.class.getModifiers());
		
	}

}

