package com.lunch.appfeeder.service.user;


import com.lunch.appfeeder.model.entity.Customer;
import com.lunch.appfeeder.model.entity.DTO.UserPrincipal;
import com.lunch.appfeeder.model.login.AppRole;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.repository.IUserRepository;
import com.lunch.appfeeder.service.customer.ICustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AppUserService implements IAppUserService{
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser saveAdmin(AppUser appUser) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của admin
        appUser.setPassword(encodePassword);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(1L, "ROLE_ADMIN"));
        appUser.setRoles(roles);
        appUser.setEnabled(true);
        return userRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return null;
    }

    @Override
    public AppUser save(AppUser appUser, String siteURL,String email,String name) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        appUser.setPassword(encodePassword);
        String randomCode = RandomString.make(64);
        appUser.setVerificationCode(randomCode);
        appUser.setEnabled(false);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(2L, "ROLE_USER"));
        appUser.setRoles(roles);
        userRepository.save(appUser);
        try {
            sendVerificationEmail(appUser, siteURL,email,name);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    @Override
    public AppUser saveMerchant(AppUser appUser, String siteURL,String email,String name ) {
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        appUser.setPassword(encodePassword);
        String randomCode = RandomString.make(64);
        appUser.setVerificationCode(randomCode);
        appUser.setEnabled(false);
        List<AppRole> roles = new ArrayList<>();
        roles.add(new AppRole(3L, "ROLE_MERCHANT"));
        appUser.setRoles(roles);
        userRepository.save(appUser);
        try {
            sendVerificationEmail(appUser, siteURL,email,name);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    private void sendVerificationEmail(AppUser appUser, String siteURL,String email, String name) throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = "koibitoc1221h1@gmail.com";
        String senderName = "Trưa nay ăn gì";
        String subject = "Xác nhận đăng ký tài khoản";
        String content = "Dear [[name]],<br>"
                + "Nhấn vào link bên dưới để xác nhận đăng ký :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Cảm ơn ,<br>"
                + "Group Trưa Nay Ăn Gì <3.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", name);
        String verifyURL = siteURL + "/login/verify?code=" + appUser.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }
    @Override
    public boolean verify(String verificationCode) {
        AppUser user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }
}
