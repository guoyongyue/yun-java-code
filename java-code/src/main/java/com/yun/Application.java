package com.yun;


import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class Application {
	private static List whiteList = new ArrayList<String>();
	static {
		whiteList.add("218.93.31.170");
		whiteList.add("61.172.240.227");
		whiteList.add("61.172.240.228");
		whiteList.add("210.51.28.119");
		whiteList.add("114.80.133.8");
		whiteList.add("114.80.133.7");
		whiteList.add("222.73.12.138");
		whiteList.add("180.166.81.83");
		whiteList.add("180.166.81.82");
	}

	public static void main(String[] args) {



		Application application = new Application();
		System.out.println(!application.isIpWhite("218.93.31"));

	}
	public boolean isIpWhite(String ip){
		return whiteList.contains(ip);
	}

}