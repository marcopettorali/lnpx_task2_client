/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx.messages;

import java.io.Serializable;

/**
 *
 * @author rnoce
 */
public class DeleteUserMsg implements Serializable{
    
    private String username;

    public DeleteUserMsg(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
