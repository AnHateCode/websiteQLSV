package vnua.qlsv.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vnua.qlsv.utils.Constant;

public class SecurityConfig {

private static final Map<Byte, List<String>> mapConfig=new HashMap<Byte, List<String>>();
	
	static {
		init();
	}

	private static void init() {
		// TODO Auto-generated method stub
		List<String> urlPatterns1=new ArrayList<String>();
		urlPatterns1.add("/userInfor");
		urlPatterns1.add("/editStudent");
		urlPatterns1.add("/viewCTDT");
		urlPatterns1.add("/dangKiMonHoc");
		mapConfig.put(Constant.CUSTOMER_ROLE, urlPatterns1);
		
		List<String> urlPatterns2=new ArrayList<String>();
		urlPatterns2.add("/userInfor");
		urlPatterns2.add("/createGiangVien");
		urlPatterns2.add("/adminHome");
		urlPatterns2.add("/createStudent");
		urlPatterns2.add("/deleteGiangVien");
		urlPatterns2.add("/deleteStudent");
		urlPatterns2.add("/editGV");
		urlPatterns2.add("/editStudent");
		urlPatterns2.add("/QLGiangVienHome");
		urlPatterns2.add("/QLLopHoc");
		urlPatterns2.add("/QLLop");
		urlPatterns2.add("/QLUserHome");
		urlPatterns2.add("/QLNewsHome");
		
		
		mapConfig.put(Constant.ADMIN_ROLE, urlPatterns2);
		
		List<String> urlPatterns3=new ArrayList<String>();
		urlPatterns3.add("/userInfor");
		urlPatterns3.add("/editGV");
		urlPatterns3.add("/viewStudentByGVCN");
		
		mapConfig.put(Constant.TEACHER_ROLE, urlPatterns3);
		
		List<String> urlPatterns4=new ArrayList<String>();
		urlPatterns4.add("/userInfor");
		urlPatterns4.add("/createGiangVien");
		urlPatterns4.add("/adminHome");
		urlPatterns4.add("/createStudent");
		urlPatterns4.add("/deleteGiangVien");
		urlPatterns4.add("/deleteStudent");
		urlPatterns4.add("/editGV");
		urlPatterns4.add("/editStudent");
		urlPatterns4.add("/QLGiangVienHome");
		urlPatterns4.add("/QLLopHoc");
		urlPatterns4.add("/QLLop");
		urlPatterns4.add("/QLNewsHome");
		mapConfig.put(Constant.Faculty_ROLE, urlPatterns4);
		
	}
	
	public static boolean checkPermission(byte role,String servletPath) {
		List<String> urlPatternsForRole=mapConfig.get(role);
		if(urlPatternsForRole.contains(servletPath)) {
			return true;
		}
		return false;
	}
	
	public static boolean checkDenyUrlPattern(String servletPath) {
		for (Map.Entry<Byte, List<String>> entry : mapConfig.entrySet()) {
			List<String> urlPatterns=entry.getValue();
			if(urlPatterns.contains(servletPath)) {
				return true;
			}
		}
		return false;
	}
}
