/**
 * 
 */
package com.ps.security.auth.rbac.repository;

import org.springframework.stereotype.Repository;

import com.ps.security.auth.rbac.domain.Admin;

/**
 * @author zhailiang
 *
 */
@Repository
public interface AdminRepository extends ImoocRepository<Admin> {

	Admin findByUsername(String username);

}
