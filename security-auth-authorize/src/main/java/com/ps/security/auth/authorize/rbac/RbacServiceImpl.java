package com.ps.security.auth.authorize.rbac;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.ps.security.auth.authorize.rbac.service.IRbacService;


@Component("rbacService")
public class RbacServiceImpl implements IRbacService {
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if(principal instanceof UserDetails) {
			String userName = ((UserDetails)principal).getUsername();
			//读取用户所有权限的所有URL
			Set<String> urls = new HashSet<>();
			for(String url: urls) {
				if(this.antPathMatcher.match(url, request.getRequestURI())) {
					// 配置的URI有可能是模糊匹配，故不能用String.equal
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;
	}

}
