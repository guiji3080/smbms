package cn.bdqn.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.bdqn.pojo.User;
//user登录权限验证
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object ret, Exception e)
			throws Exception {
		

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object ret, ModelAndView mv) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object ret) throws Exception {
		HttpSession session=request.getSession();
		User UserLogin=(User) session.getAttribute("userSession");
		if(UserLogin!=null){
			return true;
		}else{
		response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}
	}

}
