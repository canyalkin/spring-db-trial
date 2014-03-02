package spring.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 7851794269407495684L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id = null;
	@Column(name="ADDRESS")
	private String address = null;
	@Column(name="CITY")
	private String city = null;
	@Column(name="STATE")
	private String state = null;
	@Column(name="ZIP_POSTAL")
	private String zipPostal = null;
	@Column(name="COUNTRY")
	private String country = null;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRATED")
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
	 * Gets address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets zip or postal code.
	 */
	public String getZipPostal() {
		return zipPostal;
	}

	/**
	 * Sets zip or postal code.
	 */
	public void setZipPostal(String zipPostal) {
		this.zipPostal = zipPostal;
	}

	/**
	 * Gets country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets country.
	 */
	public void setCountry(String country) {
		this.country = country;
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
