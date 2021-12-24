package com.highcom.aop;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用于验证登录的拦截器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException, ServletException {
	/**
		 最后一个参数,可以得到调用的方法的相关信息
		HandlerMethod handlerMethod = (HandlerMethod) o;
		System.out.println("当前拦截的方法为："+ handlerMethod.getMethod().getName()  );
		System.out.println("当前拦截的方法参数长度为: "+handlerMethod.getMethod().getParameters());
		System.out.println("当前拦截执行这个方法的类是: "+ handlerMethod.getBean().getClass().getName());
	*/	
		if (request.getSession().getAttribute("adminInfo") == null) {
			 /* 事实上,对于ajax请求,是不可以在这里进行重定向的
			 if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
	             System.out.print("发生ajax请求..."+request.getRequestURI());
	             response.setHeader("sessionstatus", "timeout"); //在响应头设置session状态  
	         }
	         */
			 
			//System.out.println("请求:"+request.getRequestURI()+" 因为没 session 被处理掉");
			//response.sendRedirect(request.getContextPath()+"/home_detail/"+"adminlogin.jsp");
			String loginPath=request.getContextPath()+"/home_detail/"+"adminlogin.jsp";
			
			//改成下面的写法,是为了去掉某些时候登录窗口在框架集中间弹出的bug
			response.getWriter().print("<script>window.top.location.href='"+loginPath+"'</script>");
			return false;
		}
		
		return true;
	}	
}
