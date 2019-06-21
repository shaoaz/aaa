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
	// ����Token��ȡ��֤��Ϣ
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*
		 * principals����ݣ�������ı�ʶ���ԣ��������κζ��������û���������ȣ�Ψһ���ɡ� һ����������ж��
		 * principals����ֻ��һ�� Primary principals��һ�����û��� / ���� / �ֻ��š�
		 */
		String username = (String) token.getPrincipal();
		/* credentials��֤�� / ƾ֤����ֻ������֪���İ�ȫֵ�������� / ����֤��ȡ� */
		String userpass = new String((char[]) token.getCredentials());
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();// �û�������
		}
		if ("123".equals(userpass)) {
			throw new IncorrectCredentialsException();// �������
		}
		// ��������֤��֤�ɹ�������һ��AuthenticationInfoʵ�֣�
		return new SimpleAuthenticationInfo(username, userpass, getName());
	}

	// ����һ��Ψһ��Realm����
	@Override
	public String getName() {
		return "myrealm1";
	}

	// �жϴ�Realm�Ƿ�֧�ִ�Token
	@Override
	public boolean supports(AuthenticationToken token) {
		// ��֧��UsernamePasswordToken���͵�Token
		return token instanceof UsernamePasswordToken;
	}

}
