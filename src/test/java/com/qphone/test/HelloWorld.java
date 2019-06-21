package com.qphone.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;
import com.qphone.controller.OrderController;

import junit.framework.Assert;

public class HelloWorld {

	private static void login(String iniFile, String username, String password) {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			System.out.println("��¼�ɹ�");
		} catch (AuthenticationException e) {
			System.out.println("��¼ʧ��");
			e.printStackTrace();
		}
	}

	@Test
	public void testHeollWorld() {
		// 1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		/*
		 * if(subject.hasRole("admin")){
		 * 
		 * }
		 */
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4����¼���������֤
			subject.login(token);
			System.out.println("��½�ɹ�");
			
		} catch (AuthenticationException e) {
			// 5�������֤ʧ��
			/* e.printStackTrace(); */
			System.err.println("��¼ʧ��");
		}
		Assert.assertEquals(true, subject.isAuthenticated());// �����û��Ѿ���¼
		// 6���˳�
		subject.logout();
	}

	/* ���ڽ�ɫ�ķ��ʿ��ƣ���ʽ��ɫ�� */
	@Test
	public void testHasRole() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-realm.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			subject.login(token);

		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		subject.logout();
	}

	@Test
	public void salt() {
		String str = "hello";
		String salt = "123";
		/* ��һ���� */
		String mds = new Md5Hash(str, salt).toString();
		System.out.println(mds);
		/* �Ӷ����� */
		String mds2 = new Md5Hash(str, salt, 2).toString();
		System.out.println(mds2);
		/* ʹ�� SHA256 �㷨������Ӧ��ɢ�����ݣ����⻹���� SHA1��SHA512 �㷨�� */
		String sha = new Sha256Hash(str, salt).toString();
		System.out.println(sha);
	}

	@Test
	public void testHashRole() {
		login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		// �ж�ӵ�н�ɫ��role1
		Assert.assertEquals(true, subject.hasRole("role1"));
		// �ж�ӵ�н�ɫ��role1 and role2.
		Assert.assertEquals(true, subject.hasAllRoles(Arrays.asList("role1", "role2")));
		// �ж�ӵ�н�ɫ��role1 and role2 and !role3
		/*boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(true, result[2]);*/
		/*�ж��û��Ƿ����ĳһ����ɫ*/
		subject.checkRole("role2");
	}
	@Test
	public void testIsPermitted(){
		login("classpath:shiro-permission.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		/*Assert.assertTrue(subject.isPermitted("user:create"));*/
		/* Assert.assertTrue(subject.isPermittedAll("user:create", "user:delete"));*/
		 subject.checkPermission("user:create");
	}
	@Test
	public void map(){
		Map<String, Integer> map= new HashMap<String, Integer>();
		int a=map.put("lisi0", 1);
		System.out.println(a);
		System.out.println(map.put("zs", 2));
	}

}
