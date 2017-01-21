package sec.project.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping("*")
    public String defaultMapping(Authentication authentication) {
        if(authentication.isAuthenticated()){
            for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
                     if(role.equalsIgnoreCase("ADMIN")) {
                         return "admin";
                     }
            }
        }
        return "home";
    }
   
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String admin(ModelMap model) {

        return "admin";
    }
    
}
