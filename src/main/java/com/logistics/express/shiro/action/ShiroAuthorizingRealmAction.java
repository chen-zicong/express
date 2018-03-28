package com.logistics.express.shiro.action;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Controller;
import com.logistics.express.entity.Admin;
import com.logistics.express.service.AdminService;

@Controller
public class ShiroAuthorizingRealmAction extends AuthorizingRealm{

	@Resource
	AdminService adminservice;
	public static final String SESSION_USER_KEY = "admin"; 
	
	/*public ShiroAuthorizingRealmAction(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }*/
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Admin adminInfo = (Admin) principals.getPrimaryPrincipal(); //获取用户名
		Set<String>privilegeSet=new HashSet<String>();
		//privilegeSet.add(adminInfo.getStatus());
		Set<String> roleSet=new HashSet<String>();
		roleSet.add(String.valueOf(adminInfo.getAdminRoleId()));//赋予角色id
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);//角色
        //authorizationInfo.setStringPermissions(privilegeSet);//权限
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
        Admin admin = tokenToUser((UsernamePasswordToken) authcToken);
        //user=uservice.selcetByTel(user.getUserName());
        admin = adminservice.getAdminByPhone(admin.getAdminPhone());
        // 设置session  
        //Session session = SecurityUtils.getSubject().getSession();  
        //session.setAttribute(ShiroAuthorizingRealmAction.SESSION_USER_KEY, ui);
        //当前 Realm 的 name  
        String realmName = this.getName();
        //System.out.println("user.getAdAccount()>>"+user.getTel());
        return new SimpleAuthenticationInfo(admin,admin.getAdminPassword(),realmName);
	}
	
	private Admin tokenToUser(UsernamePasswordToken authcToken) {  
		Admin admin = new Admin();  
		admin.setAdminPhone(authcToken.getUsername());
		admin.setAdminPassword(String.valueOf(authcToken.getPassword()));
        return admin;  
    }
	
}
