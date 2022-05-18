package com.rank.dao;

import com.rank.common.jpa.JpaCompatibilityRepository;
import com.rank.dto.User;

import java.util.List;

/**
 * @author Caolp
 */
public interface UserRepository extends JpaCompatibilityRepository<User, Number> {

    /**
     * 通过 id 查询 user
     * @param name 名称
     * @return List<User>
     */
    List<User> findByNameLike(String name);

}
