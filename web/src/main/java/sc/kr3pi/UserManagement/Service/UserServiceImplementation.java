package sc.kr3pi.UserManagement.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sc.kr3pi.UserManagement.Dto.UserDto;
import sc.kr3pi.UserManagement.Repository.RoleRepository;
import sc.kr3pi.UserManagement.Repository.UserRepository;
import sc.kr3pi.UserManagement.model.Role;
import sc.kr3pi.UserManagement.model.Users;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        Users user = new Users();
        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByRoleName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return usersList.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRoleName("ROLE_User");
        return roleRepository.save(role);
    }
}
/*+++++++++++++++++++++++References+++++++++++++++++++++++
=
=  amigos code
=
* */