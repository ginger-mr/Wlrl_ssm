package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IUserDao;
import com.ginger.wlfl.pojo.Role;
import com.ginger.wlfl.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义UserServiceImpl类实现springsecurity框架的UserDetailsService接口
 */
@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    /**
     * 用户登录功能版本1.0没有进行二次加密
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        UserInfo userInfo = userDao.findByUsername(username);
        // 将查询出用户中的角色添加到 SimpleGrantedAuthority 类中
        List<SimpleGrantedAuthority> sgas = getAuthorities(userInfo.getRoles());
        // 将封装的 user 返回，底层会比较用户和密码。
        // 因为密码是明文的所以要{noop}前缀才能访问。
        return new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), sgas);
    }

    /**
     * 将查询的用户中的角色添加到 SimpleGrantedAuthority 类中
     * @param roles
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> sgas = new ArrayList<>();
        for (Role role : roles) {
            //将用户中的角色拼接上 "ROLE_" 添加到 SimpleGrantedAuthority 类中
            sgas.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return sgas;
    }
}
