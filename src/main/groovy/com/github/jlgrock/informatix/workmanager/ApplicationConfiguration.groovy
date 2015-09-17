package com.github.jlgrock.informatix.workmanager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal
/**
 *
 */
// Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically
// when it sees spring-webmvc on the classpath.
@SpringBootApplication // is the same as @Configuration, @EnableConfiguration, and @ComponentScan
//@Configuration // tags the class as a source of bean definitions for the application context.
//@EnableAutoConfiguration // tells Spring Boot to start adding beans based on classpath settings and properties
//@ComponentScan // tells Spring to look for other components in this package (and below)
@EnableConfigurationProperties // Looks for classes annotated with @ConfigurationProperties and populates them
@RestController
class ApplicationConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @RequestMapping("/principal")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

//    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.httpBasic().and().authorizeRequests()
//                    .antMatchers("/index.html", "/", "**/*.js", "services/userlogin.js", "/templates/login.html", "/templates/navbar.html")
//                        .permitAll().anyRequest()
//                    .authenticated().and().csrf()
//                    .csrfTokenRepository(csrfTokenRepository()).and()
//                    .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
//        }
//
//        private Filter csrfHeaderFilter() {
//            return new OncePerRequestFilter() {
//                @Override
//                protected void doFilterInternal(HttpServletRequest request,
//                                                HttpServletResponse response, FilterChain filterChain)
//                        throws ServletException, IOException {
//
//                    // The CSRF Token needs to be renamed to XSRF for Angular to use it
//                    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//                            .getName());
//                    if (csrf != null) {
//                        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//                        String token = csrf.getToken();
//                        if (cookie == null || token != null
//                                && !token.equals(cookie.getValue())) {
//                            cookie = new Cookie("XSRF-TOKEN", token);
//                            cookie.setPath("/");
//                            response.addCookie(cookie);
//                        }
//                    }
//                    filterChain.doFilter(request, response);
//                }
//            };
//        }
//
//        private CsrfTokenRepository csrfTokenRepository() {
//            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//            repository.setHeaderName("X-XSRF-TOKEN");
//            return repository;
//        }
//    }

    static void main(String[] args) throws Exception {
        LOGGER.info("Starting Spring Boot Application.");
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

}
