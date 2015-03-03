/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author m.elz
 */
@Entity
@NamedQueries({@NamedQuery(name = PmsUser.findUserByLoginname, 
                           query = "SELECT pu FROM PmsUser pu WHERE pu.loginName = :loginName"),
               @NamedQuery(name = PmsUser.getUsers,
                           query = "SELECT pu FROM PmsUser pu"),
               @NamedQuery(name = PmsUser.findUserById,
                           query = "SELECT pu FROM PmsUser pu WHERE pu.id = :id")})
public class PmsUser implements Serializable {
    
    public static final String findUserByLoginname = "findUserByLoginname";
    public static final String getUsers = "getUsers";
    public static final String findUserById = "findUserById";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PmsUser_ID")
    private Long id;
    
    @Column(unique = true)
    private String loginName;
    
    @Lob
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
    
    @Override
    public String toString(){
        return this.loginName;
    }
}
