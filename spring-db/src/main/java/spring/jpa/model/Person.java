package spring.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "person")
public class Person implements Serializable  {
	
	 private static final long serialVersionUID = -8712872385957386182L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="ID")
	 private Integer id = null;
	 @Column(name="FIRST_NAME")
	 private String firstName = null;
	 @Column(name="LAST_NAME")
	 private String lastName = null;
	 
	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	 @JoinTable(name="PERSON_ADDRESS_MATRIX")
	 private Set<Address> addresses = null;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name="CREATED")
	 private Date created = null;
	 
	 /**
	     * Gets id (primary key).
	     */
	    
	    public Integer getId() {
	        return id;
	    }

	    /**
	     * Sets id (primary key).
	     */
	    public void setId(Integer id) {
	        this.id = id;
	    }

	    /**
	     * Gets first name.
	     */
	    public String getFirstName() {
	        return firstName;
	    }

	    /**
	     * Sets first name.
	     */
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    /**
	     * Gets last name.
	     */
	    public String getLastName() {
	        return lastName;
	    }

	    /**
	     * Sets last name.
	     */
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    /**
	     * Gets list of <code>Address</code>es.
	     */
	   
	   
		@JoinColumn(name = "ID", nullable = false)
	    public Set<Address> getAddresses() {
	        return addresses;
	    }

	    /**
	     * Sets list of <code>Address</code>es.
	     */
	    public void setAddresses(Set<Address> addresses) {
	        this.addresses = addresses;
	    }

	    /**
	     * Gets date created.
	     */
	    public Date getCreated() {
	        return created;
	    }

	    /**
	     * Sets date created.
	     */
	    public void setCreated(Date created) {
	        this.created = created;
	    }
	

}
