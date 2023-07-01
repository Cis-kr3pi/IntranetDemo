package sc.kr3pi.UserManagement.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sc.kr3pi.UserManagement.Repository.UserRepository;
import sc.kr3pi.UserManagement.model.Role;
import sc.kr3pi.UserManagement.model.Users;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class SecureUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final static String USER_CREDENTIAL_INVALID= "user credential is invalid";

    private UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException(USER_CREDENTIAL_INVALID);
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles;
        mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return mapRoles;
    }



   //TODO when confirmation token is send enable User
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }


}
