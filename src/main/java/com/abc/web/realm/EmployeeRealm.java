package com.abc.web.realm;

import com.abc.domain.Employee;
import com.abc.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRealm extends AuthorizingRealm
{

    @Autowired
    private EmployeeService employeeService;

    /*doGetAuthorization什么时候调用
    * 1.发现访问路径对应的方法上面有授权注解
    * 2.页面当中有授权标签
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        System.out.println("authorization info");
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();

        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        //管理员拥有所有权限
        if(employee.getAdmin()) {
            permissions.add("*:*");
        } else {
            roles = employeeService.getRolesByEid(employee.getId());
            permissions = employeeService.getPermissionsByEid(employee.getId());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        String username = (String) token.getPrincipal();
        Employee employee = employeeService.getEmployeeByUsername(username);
        System.out.println(employee);
        if(employee == null) {
            return null;
        }

        /*参数：主体，密码，盐，realm名*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(),
                ByteSource.Util.bytes(employee.getUsername()),this.getName());

        return info;
    }
}
