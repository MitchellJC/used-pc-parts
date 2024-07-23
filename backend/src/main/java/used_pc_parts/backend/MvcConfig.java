package used_pc_parts.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/user/login").setViewName("login");
    registry.addViewController("/user/register").setViewName("register");
    registry.addViewController("/user/logout").setViewName("logout");
    registry.addViewController("/listing/create").setViewName("create-listing");
  }
}
