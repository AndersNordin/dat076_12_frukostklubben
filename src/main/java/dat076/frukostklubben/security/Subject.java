/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Common base for anything (the subject) that can log in
 *
 * @author hajo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="SUBJECT")
// Below, because common data from many types end up in same table
//@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable=false)
    private String mail; 
    @Column(nullable=false)
    private String passwd;  
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "SUBJECT_GROUP")
    @Enumerated(EnumType.STRING)
    private final List<SubjectGroup> groups = new ArrayList<>();

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }    
    
    public Subject() {
    }

    public Subject(String mail, String passwd) {
        this.mail = mail;
        this.passwd = passwd;
    }

    public void addGroup(SubjectGroup group) {
        groups.add(group);
    }

    public void removeGroup(SubjectGroup group) {
        groups.remove(group);
    }

    public List<SubjectGroup> getGroups() {
        return groups;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subject other = (Subject) obj;
        if ((this.mail == null) ? (other.mail != null) : !this.mail.equals(other.mail)) {
            return false;
        }
        if ((this.passwd == null) ? (other.passwd != null) : !this.passwd.equals(other.passwd)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.mail != null ? this.mail.hashCode() : 0);
        hash = 29 * hash + (this.passwd != null ? this.passwd.hashCode() : 0);
        return hash;
    }
}
