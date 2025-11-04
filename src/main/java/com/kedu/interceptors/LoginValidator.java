package com.kedu.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/*
 *  로그인 검증을 위해 사용되는 인터셉터
 *  세션에 로그인 정보가 없으면 접근을 차단하는 역할
 */
public class LoginValidator implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// true를 반환하면 다음 컨트롤러로 진행
		// false를 반환하면 요청을 막음
		return false;
	}
}
