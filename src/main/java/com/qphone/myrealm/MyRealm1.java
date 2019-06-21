package com.qphone.myrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
	// 根据Token获取认证信息
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*
		 * principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。 一个主体可以有多个
		 * principals，但只有一个 Primary principals，一般是用户名 / 密码 / 手机号。
		 */
		String username = (String) token.getPrincipal();
		/* credentials：证明 / 凭证，即只有主体知道的安全值，如密码 / 数字证书等。 */
		String userpass = new String((char[]) token.getCredentials());
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();// 用户名错误
		}
		if ("123".equals(userpass)) {
			throw new IncorrectCredentialsException();// 密码错误
		}
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, userpass, getName());
	}

	// 返回一个唯一的Realm名字
	@Override
	public String getName() {
		return "myrealm1";
	}

	// 判断此Realm是否支持此Token
	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

}
