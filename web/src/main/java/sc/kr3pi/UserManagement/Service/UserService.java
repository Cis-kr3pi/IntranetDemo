package sc.kr3pi.UserManagement.Service;

import sc.kr3pi.UserManagement.Dto.UserDto;
import sc.kr3pi.UserManagement.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void saveUser(UserDto userDto);

    public Users findByEmail(String email);

    List<UserDto> findAllUsers();
}
