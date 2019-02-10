/**
 * 
 */
package edu.neu.csye6225.spring19.cloudninja.model;

import static edu.neu.csye6225.spring19.cloudninja.constants.ApplicationConstants.DATE_FORMAT;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import edu.neu.csye6225.spring19.cloudninja.util.CommonUtil;

/**
 * @author gaurang
 *
 */
@Entity
@Table(name = "USR_NOTE_DTLS")
public class Note {

	@Autowired
	CommonUtil commonUtil;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
			@Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	@Column(name = "NOTE_ID")
	private UUID id;

	@Column(name = "NOTE_TITLE")
	private String title;

	@Column(name = "NOTE_CONTENT")
	private String content;

	@Column(name = "CREATED_DATE", updatable = false)
	private String creationDate;

	@Column(name = "LAST_UPDATED_DATE")
	private String updatedDate;

	@ManyToOne
	@JoinColumn(name = "USR_EML_ID")
	private int emailId;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	@PrePersist
	private void onCreate() {
		setCreationDate(commonUtil.getCurrentDateWithFormat(DATE_FORMAT));
		setUpdatedDate(commonUtil.getCurrentDateWithFormat(DATE_FORMAT));
	}

	@PreUpdate
	private void onUpdate() {
		setUpdatedDate(commonUtil.getCurrentDateWithFormat(DATE_FORMAT));
	}

}