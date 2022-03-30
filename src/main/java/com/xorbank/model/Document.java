package com.xorbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="documents")
public class Document {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer documentId;
	private String docName;
	private String docType;
	@Lob
	@Column(name = "docData", length = 1000)
	private byte[] data;
	@OneToOne
	@JsonBackReference(value="user-document")
	private User user;
	

	
	public Document() {
		super();
	}

	public Document( String docName, String docType, byte[] data, User user) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		this.user = user;
	}
	public Document(Integer documentId, String docName, String docType, byte[] data, User user) {
		super();
		this.documentId = documentId;
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		this.user = user;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Document [documentId=" + documentId + ", docName=" + docName + ", docType=" + docType + ", data="
				 + ", user=" + user + "]";
	}
}
