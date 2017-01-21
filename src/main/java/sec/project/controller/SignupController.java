package sec.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String address, Model model) {
        //signupRepository.save(new Signup(username, address));
        addSignup(username, address);
        model.addAttribute("name", username);
        return "done";
    }

    private void addSignup(String userName, String address) {
        // harcoded for now...
        String databaseAddress = "jdbc:h2:mem:TEST";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(databaseAddress, "sa", "");
            Statement s = connection.createStatement();
            // Execute query and retrieve the query results
            connection.createStatement().executeUpdate("insert into signup (name,address) values ('" + userName + "','" + address + "')");

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
