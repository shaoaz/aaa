package com.qphone.service;

import com.qphone.pojo.Permission;

public interface PermissionService {
	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);
}
