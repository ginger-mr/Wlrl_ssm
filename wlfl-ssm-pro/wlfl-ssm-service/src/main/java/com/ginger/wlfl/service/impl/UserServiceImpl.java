package com.ginger.wlfl.service.impl;

import com.ginger.wlfl.dao.IUserDao;
import com.ginger.wlfl.pojo.Role;
import com.ginger.wlfl.pojo.UserInfo;
import com.ginger.wlfl.service.IUserService;
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
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcpe; //注入二次加密类(当然也可以提取一个工具类)

    /**
     * 用户登录功能版本1.0没有进行二次加密
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        UserInfo userInfo = userDao.findByUsername(username);
        // 将查询出用户中的角色添加到 SimpleGrantedAuthority 类中
        List<SimpleGrantedAuthority> sgas = getAuthorities(userInfo.getRoles());
        // 将封装的 user 返回，底层会比较用户和密码。
        // 因为密码是明文的所以要{noop}前缀才能访问。
        return new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), sgas);
    }*/

    /**
     * 用户登录功能版本2.0对输入密码进行二次加密
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        UserInfo userInfo = userDao.findByUsername(username);
        //将加密后的密码重新设置到用户中
        userInfo.setPassword(bcpe.encode(userInfo.getPassword()));
        // 将查询出用户中的角色添加到 SimpleGrantedAuthority 类中
        List<SimpleGrantedAuthority> sgas = getAuthorities(userInfo.getRoles());
        // 将封装的 user 返回，底层会比较用户和密码。
        // 因为密码已经进行二次加密{noop}前缀就可以不用写了。
        return new User(userInfo.getUsername(),userInfo.getPassword(),sgas);
    }*/

    /**
     * 用户登录功能版本3.0判断账户是否启用，启用后才能登录。
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        UserInfo userInfo = userDao.findByUsername(username);
        //将加密后的密码重新设置到用户中
        userInfo.setPassword(bcpe.encode(userInfo.getPassword()));
        // 将查询出用户中的角色添加到 SimpleGrantedAuthority 类中
        List<SimpleGrantedAuthority> sgas = getAuthorities(userInfo.getRoles());
        // 将封装的 user 返回，底层会比较用户和密码。
        // 因为密码已经进行二次加密{noop}前缀就可以不用写了。
        return new User(userInfo.getUsername(), userInfo.getPassword(),
               userInfo.getStatus()==0 ? false:true,true, true, true, sgas);
    }

    /**
     * 将查询的用户中的角色添加到 SimpleGrantedAuthority 类中
     *
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


    public List<UserInfo> findAll(){
        return userDao.findAll();
    }

}
