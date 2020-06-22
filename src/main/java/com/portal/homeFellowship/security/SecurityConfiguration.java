package com.portal.homeFellowship.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.portal.homeFellowship.filter.CORSFilterWebLogicFix;
import com.portal.homeFellowship.filter.SimpleAuthenticationFilter;
import com.portal.homeFellowship.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    /**
     *
     * @return Authentication trust provider used by Spring Security
     */
    @Bean
   // @Lazy(true)
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
     
    

    @Autowired
    @Qualifier("customUserDetailsService")
    CustomUserDetailsService userDetailsService;
    

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userDetailsService);
    } 

    protected void configure(HttpSecurity http) throws Exception {
        http.logout()
                .invalidateHttpSession(true)
                .deleteCookies("SESSION")
                .clearAuthentication(true)
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));

        //TODO ADD ALL URLS HERE SO THAT SECURITY WON'T BE BREACHED AND JUST DO IT
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()

                .antMatchers("/", "/dashboard", "/edituser", "/editusertorole", "/removeusertorole", "/loaduserstepproduct", "/enableuser-*", "/diasableuser-*", "/edituserdetails-*", "/approveuserdetails-*", "/approveeditrole", "/authusertorole", "/authoriseuser", "/modifyUser", "addSourceAccount",
                        "/deactivate", "/changeSourceAccount", "/enrollNewCustomer", "/enrollCustomer", "/returnedCustomer", "/awaitingCustomer", "/viewWaitingEnrolment-*", "/treatRejectedCustomer-*", "/unauthorisedCustomers", "/unauthorisedModification", "/getCustomerDetail", "allUserReport", "transactionReport",
                        "/approveenableuser", "/approvedisableuser", "/addAccountClass", "/getAccClass", "/addTransCode", "/getTransCode", "/getTransCodes", "/getAccClassRemove", "/awaitingModification", "/awaitingCustomer")
                .access("hasRole('MAKER') or hasRole('ADMIN') or hasRole('AUDIT') or hasRole('OPERATIONS') or hasRole('GROUPADMIN') or hasRole('CUSTOM') or hasRole('MEMBER') or hasRole('AREA SUPERVISOR') or hasRole('BUSINESS') or hasRole('USERACCESS')")

                .antMatchers("/auditlog").access("hasRole('ADMIN') or hasRole('AUDIT') or hasRole('SUPERADMIN')")
//                .antMatchers("/*").access("hasRole('AREA SUPERVISOR') or hasRole('ADMIN') or hasRole('AUDIT') or hasRole('OPERATIONS') or hasRole('GROUPADMIN') or hasRole('CUSTOM') or hasRole('MEMBER') or hasRole('CHECKER') or hasRole('BUSINESS') or hasRole('USERACCESS')")
                .antMatchers("/authpenduser-*", "/rejpenduser-*").access("hasRole('USERACCESS') or hasRole('GROUPADMIN') or hasRole('CUSTOM') or hasRole('SUPERADMIN') or hasRole('CHECKER')")
                .antMatchers("/editusertorole-*", "/diasableuser-*").access("hasRole('ADMIN') or hasRole('GROUPADMIN') or hasRole('CUSTOM') or hasRole('SUPERADMIN') or hasRole('CHECKER')")

                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()

               // .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error")
                .usernameParameter("ssoId").passwordParameter("password")
                //.and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied").and().sessionManagement().invalidSessionUrl("/login").maximumSessions(1);

        http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
    }
}
