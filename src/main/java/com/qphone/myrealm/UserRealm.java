package com.qphone.myrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.qphone.pojo.User;
import com.qphone.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/* ��Ȩ��Ϣ */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		/*authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));*/
		return authorizationInfo;
	}

	/* ��ȡ�����֤��Ϣ */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();// û�ҵ��ʺ�
		}
		/*if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); // �ʺ�����
		}*/
		// ����AuthenticatingRealmʹ��CredentialsMatcher��������ƥ�䣬��������˼ҵĲ��ÿ����ڴ��жϻ��Զ���ʵ��
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(), // �û���
				user.getPassword(), // ����
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	//init-method 配置.
	public void setCredentialMatcher(){
	    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
	    credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
	    credentialsMatcher.setHashIterations(2);//1024次循环加密
	    setCredentialsMatcher(credentialsMatcher);
	}

}
