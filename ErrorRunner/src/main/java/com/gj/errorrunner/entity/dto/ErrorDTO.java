package com.gj.errorrunner.entity.dto;

public class ErrorDTO {

	private String message;
	private Long messageID;
	private String tranactionID;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getMessageID() {
		return messageID;
	}
	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}
	public String getTranactionID() {
		return tranactionID;
	}
	public void setTranactionID(String tranactionID) {
		this.tranactionID = tranactionID;
	}
	public ErrorDTO(String message, Long messageID, String tranactionID) {
		super();
		this.message = message;
		this.messageID = messageID;
		this.tranactionID = tranactionID;
	}
	public ErrorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ErrorDTO [message=" + message + ", messageID=" + messageID + ", tranactionID=" + tranactionID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageID == null) ? 0 : messageID.hashCode());
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
		ErrorDTO other = (ErrorDTO) obj;
		if (messageID == null) {
			if (other.messageID != null)
				return false;
		} else if (!messageID.equals(other.messageID))
			return false;
		return true;
	}
	
	
	
}
