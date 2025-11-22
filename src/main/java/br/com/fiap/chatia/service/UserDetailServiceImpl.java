package br.com.fiap.chatia.service;

import br.com.fiap.chatia.repository.UserRepository;
import br.com.fiap.chatia.security.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repo.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado")
        );
        return new UserSecurityDetails(user);
    }
}
