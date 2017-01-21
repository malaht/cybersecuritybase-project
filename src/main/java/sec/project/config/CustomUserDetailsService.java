package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, String> accountDetails;
 @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String tedPw = "ted";
        String hashedPassword = passwordEncoder.encode(tedPw);

        Account account = new Account();
        account.setUsername("ted");
        account.setPassword(hashedPassword);
        accountRepository.save(account);
        
        String adminPw = "admin";
        String hashedAdminPassword = passwordEncoder.encode(adminPw);

        Account adminAccount = new Account();
        adminAccount.setUsername("admin");
        adminAccount.setPassword(hashedAdminPassword);
        accountRepository.save(adminAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        if(username.equals("admin")) {
                return new org.springframework.security.core.userdetails.User(  
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        }
        return new org.springframework.security.core.userdetails.User(  
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
