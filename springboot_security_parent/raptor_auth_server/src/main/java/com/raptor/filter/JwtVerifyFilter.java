package com.raptor.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raptor.config.RsaKeyProperties;
import com.raptor.domain.Payload;
import com.raptor.domain.SysUser;
import com.raptor.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.raptor_auth_server.filter
 * @date 2020/11/5 12:03
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperties rsaKeyProperties;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
        super(authenticationManager);
        this.rsaKeyProperties = rsaKeyProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            //请求体的头中是否包含Authorization
            String header = request.getHeader("Authorization");
            //Authorization中是否包含Bearer，不包含直接返回
            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                responseJson(response);
                return;
            }
            //获取权限失败，会抛出异常
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            //获取后，将Authentication写入SecurityContextHolder中供后序使用
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            responseJson(response);
            e.printStackTrace();
        }
    }

    private void responseJson(HttpServletResponse response) {
        try {
            //未登录提示
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpServletResponse.SC_FORBIDDEN);
            map.put("message", "请登录！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            //通过token解析出载荷信息
            Payload<SysUser> payload = JwtUtils.getInfoFromToken(token.replace("Bearer ", ""), rsaKeyProperties.getPublicKey(), SysUser.class);
            SysUser user = payload.getUserInfo();
            //不为null，返回
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
            }
            return null;
        }
        return null;
    }
}
