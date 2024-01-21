package com.springbootjwt.security.user;

import com.springbootjwt.security.util.EmailUtil;
import com.springbootjwt.security.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());

        // check if current pass correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }
        // check if new pass are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }

    public void changeUserInfo(ChangeUserInfoRequest request, Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        repository.save(user);
    }
    public String regenerateOtp(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        repository.save(user);
        return "Email sent... please verify account within 1 minute";
    }
    public String forgotPassword(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
//        try {
//            emailUtil.sendSetPasswordEmail(email);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Unable to set password email please try again");
//        }
        return "Please check your email to set new password";
    }

    public String setPassword(String email, String newPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        try {
            emailUtil.sendSetNewPasswordEmail(email, newPassword);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to set password email please try again");
        }
        return "New password set successfully";
    }
}
