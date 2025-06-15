package murilo.api_autenticacao.controllers;

import lombok.RequiredArgsConstructor;
import murilo.api_autenticacao.dtos.UserResponseDTO;
import murilo.api_autenticacao.models.User;
import murilo.api_autenticacao.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(convertToDto(currentUser));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMyProfile(@RequestBody UserResponseDTO updatedData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User userToUpdate = userRepository.findByEmail(userEmail).orElseThrow();
        userToUpdate.setName(updatedData.getName());
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(convertToDto(userToUpdate));
    }

    private UserResponseDTO convertToDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}