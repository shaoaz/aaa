package com.qphone.service;

import java.util.Set;

import com.qphone.pojo.User;

public interface UserService {
	public int createUser(User user); //�����˻�
   /* public void changePassword(Long userId, String newPassword);*///�޸�����
   /* public void correlationRoles(Long userId, Long... roleIds); //����û�-��ɫ��ϵ
    public void uncorrelationRoles(Long userId, Long... roleIds);// �Ƴ��û�-��ɫ��ϵ
*/    public User findByUsername(String username);// �����û��������û�
  /*  public Set<String> findRoles(String username);// �����û����������ɫ
    public Set<String> findPermissions(String username); //�����û���������Ȩ��
*/}
