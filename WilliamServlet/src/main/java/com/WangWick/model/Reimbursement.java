package com.WangWick.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "reimbursements")
public class Reimbursement {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private float amount;
	@Column
	private Timestamp submitted;
	@Column
	private Timestamp resolved;
	@Column
	private String description;

	@ManyToOne
	@JoinColumn
	@Access(AccessType.FIELD)
	private User author;
	@ManyToOne
	@JoinColumn
	private User resolver;

	//these next two fields would be complex enums that have consequences, but
	//for 1.5 we can probably leave them as int stubs.
	@Column
	private int status_id;//0==pending, 1==accepted, 2==rejected
	@Column
	private int type_id;//0==dining, 1==relocation
	
	public Reimbursement() {
		//No-arg constructor
	}
	
	public Reimbursement(int id, float amount, Timestamp submitted, Timestamp resolved, String description, User author,
			User resolver, int status_id, int type_id) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public Reimbursement(int i) {

		this.id = i;
		this.amount = i;
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.resolved = new Timestamp(System.currentTimeMillis() + 5000);
		this.description = Integer.toString(i);
		this.status_id = i;
		this.type_id = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		author.setPassword("");
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + author.getUser_id();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver.getUser_id();
		result = prime * result + status_id;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status_id != other.status_id)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status_id="
				+ status_id + ", type_id=" + type_id + "]";
	}
	
}
