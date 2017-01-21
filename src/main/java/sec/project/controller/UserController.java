/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

/**
 *
 * 
 */
@Controller
public class UserController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("items", signupRepository.findAll());
        return "signups";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("items", listSignups(name));
        //model.addAttribute("items", signupRepository.findByName(name));
        return "signups";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "signups";
    }

    private List<Signup> listSignups(String userName) {
        List<Signup> signups = new ArrayList<>();
        // harcoded for now...
        String databaseAddress = "jdbc:h2:mem:TEST";
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(databaseAddress, "sa", "");
            Statement s = connection.createStatement();
            // Execute query and retrieve the query results
            resultSet = connection.createStatement().executeQuery("SELECT * from signup where name='" + userName + "'");
            while (resultSet.next()) {
                String address = resultSet.getString("address");
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("account_id");
                signups.add(new Signup(name, address));
                System.out.println(resultSet.toString() + address + " " + name + " " + id + " " + accountId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return signups;
    }

}
