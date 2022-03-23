package com.restoreempire.mvc.repository.postgres;

import com.restoreempire.mvc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 
@Transactional
public interface UserRepositorty extends JpaRepository<User, Long>{

    User findByUsername(String username);

    User findByGithubId(String githubId);

}
