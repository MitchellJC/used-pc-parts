package used_pc_parts.backend;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.Supplier;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) -> {
              requests
                  // Root pages
                  .requestMatchers("/", "/home", "logout")
                  .permitAll()
                  .requestMatchers("/greeting")
                  .authenticated()

                  // User resources
                  .requestMatchers("/user/register", "/user/logout")
                  .permitAll()
                  .requestMatchers("/user/all", "/user/remove")
                  .hasAuthority("ADMIN")

                  // Listing resources
                  .requestMatchers("/listing/all","/listing/new", "/listing/all/category")
                  .permitAll()
                  .requestMatchers("/listing/create", "/listing/buy")
                  .authenticated()

                  // Sale resources
                  .requestMatchers("/sale/purchases", "/sale/sold")
                  .authenticated()
                  .requestMatchers("/sale/all")
                  .hasAuthority("ADMIN");
            })
        .formLogin((form) -> form.loginPage("/user/login").permitAll())
        .logout(LogoutConfigurer::permitAll)
        .csrf(
            (csrf) ->
                // Allows cookie to be read by JavaScript
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler()))
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  /** Cross Site Request Forgery (CSRF) token handler for Single Page Applications (SPA). */
  final class SpaCsrfTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
    private final CsrfTokenRequestHandler delegate = new XorCsrfTokenRequestAttributeHandler();

    @Override
    public void handle(
        HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
      /*
       * Always use XorCsrfTokenRequestAttributeHandler to provide BREACH protection of
       * the CsrfToken when it is rendered in the response body.
       */
      this.delegate.handle(request, response, csrfToken);
    }

    @Override
    public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
      /*
       * If the request contains a request header, use CsrfTokenRequestAttributeHandler
       * to resolve the CsrfToken. This applies when a single-page application includes
       * the header value automatically, which was obtained via a cookie containing the
       * raw CsrfToken.
       */
      if (StringUtils.hasText(request.getHeader(csrfToken.getHeaderName()))) {
        return super.resolveCsrfTokenValue(request, csrfToken);
      }
      /*
       * In all other cases (e.g. if the request contains a request parameter), use
       * XorCsrfTokenRequestAttributeHandler to resolve the CsrfToken. This applies
       * when a server-side rendered form includes the _csrf request parameter as a
       * hidden input.
       */
      return this.delegate.resolveCsrfTokenValue(request, csrfToken);
    }
  }

  final class CsrfCookieFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
      CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
      // Render the token value to a cookie by causing the deferred token to be loaded
      csrfToken.getToken();

      filterChain.doFilter(request, response);
    }
  }
}
