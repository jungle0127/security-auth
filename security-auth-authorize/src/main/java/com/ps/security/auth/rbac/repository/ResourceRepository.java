/**
 * 
 */
package com.ps.security.auth.rbac.repository;

import org.springframework.stereotype.Repository;

import com.ps.security.auth.rbac.domain.Resource;

/**
 * @author zhailiang
 *
 */
@Repository
public interface ResourceRepository extends ImoocRepository<Resource> {

	Resource findByName(String name);

}
