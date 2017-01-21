/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.config;

import org.apache.catalina.Context;
import org.apache.catalina.session.StandardManager;

/**
 *
 */
public class MySessionManager extends StandardManager {
    int counter = 0; 
    
    @Override
    protected synchronized String generateSessionId() { 
        String sessionId = String.valueOf(counter++);
        return sessionId;
    }

    @Override
    public Context getContext() {
        Context c = super.getContext();
        c.setUseHttpOnly(false);
        return c; 
    }
    
    
}
