/**
 * 
 */
package com.ps.security.auth.rbac.repository.spec;

import com.ps.security.auth.rbac.domain.Admin;
import com.ps.security.auth.rbac.dto.AdminCondition;
import com.ps.security.auth.rbac.repository.support.ImoocSpecification;
import com.ps.security.auth.rbac.repository.support.QueryWraper;

/**
 * @author zhailiang
 *
 */
public class AdminSpec extends ImoocSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
