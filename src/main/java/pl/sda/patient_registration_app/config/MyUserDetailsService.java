package pl.sda.patient_registration_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.bo.UtilsService;
import pl.sda.patient_registration_app.entity.User;
import pl.sda.patient_registration_app.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("myUserDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UtilsService utilsServices;


    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByLogin(login);

//        if (user == null) {
//            throw new UsernameNotFoundException(login);
//        }

        return utilsServices.mapUserToMyUserPrincipalDto(user);
    }


}
