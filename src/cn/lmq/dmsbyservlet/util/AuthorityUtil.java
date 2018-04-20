package cn.lmq.dmsbyservlet.util;

public class AuthorityUtil {
	public static boolean hasAuthority(boolean isAssessor, String url) {
		// 公共目录都可以访问
		if (url.contains("common")) {
			return true;
		}
		//审核员的权限
		if (isAssessor && url.contains("assessor")) {
			return true;
		}
		//一般用户的权限
		if(!isAssessor && url.contains("user")) {
			return true;
		}
		return false;
	}
}
