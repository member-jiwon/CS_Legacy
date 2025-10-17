package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 	 세션 확인해서 로그인한 유저인지 확인하는 뭐 그런거있잖아 그거임 ㅇㅇ
 */

public class LoginValidator implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return false;
	}
}
