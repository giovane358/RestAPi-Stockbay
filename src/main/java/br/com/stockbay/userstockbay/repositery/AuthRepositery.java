package br.com.stockbay.userstockbay.repositery;

import br.com.stockbay.userstockbay.domain.user.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthRepositery extends JpaRepository<Auth, String> {

    UserDetails findByLogin(String login);


}
