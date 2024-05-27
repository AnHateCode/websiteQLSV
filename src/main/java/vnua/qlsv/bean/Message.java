package vnua.qlsv.bean;


import java.sql.Timestamp;
import java.util.Date;

public class Message {

	private String messageId;
	private String content;
	private String senderId;
	private String receiverId;
	private Timestamp timestamp;
	
	
	
	
	
	
	public Message(String messageId, String content, String senderId, String receiverId, Timestamp timestamp) {
		super();
		this.messageId = messageId;
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.timestamp = timestamp;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public String getContent() {
		return content;
	}
	public String getSenderId() {
		return senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
