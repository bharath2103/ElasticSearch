package com.bootelastic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.Data;

@Entity
@Table(name="USER_ACCOUNT")
@Data
public class UserAccount  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7229313760469171556L;

	@Id
	@Column(name= "userid")
	private long userid;
	
	@ElementCollection
	@JoinTable(name = "user_adapter", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name= "adapter")
	private List<String> adapter;
	//private String adapter;

	@Column(name= "username")
	private String username;
	
	@Column(name= "password")
	private String password;
}
