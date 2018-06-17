package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.entity.User;
import pl.sda.patient_registration_app.repository.UserRepository;

import javax.transaction.Transactional;

@Service("myUserDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private UtilsService utilsServices;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository, UtilsService utilsServices) {
        this.userRepository = userRepository;
        this.utilsServices = utilsServices;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByLogin(login);

        return utilsServices.mapUserToMyUserPrincipalDto(user);
    }


}
