package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import vnua.qlsv.bean.Message;

public class MessageDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    private Statement statement;
    private PreparedStatement preStatement;
    private ResultSet resultSet;

    public MessageDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    // Thêm tin nhắn mới
    public boolean addMessage(Message message) throws SQLException {
        String sql = "INSERT INTO messages (content, sender_id, receiver_id, timestamp) VALUES (?, ?, ?, ?)";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            preStatement = jdbcConnection.prepareStatement(sql);
            preStatement.setString(1, message.getContent());
            preStatement.setString(2, message.getSenderId());
            preStatement.setString(3, message.getReceiverId());
            preStatement.setTimestamp(4, (Timestamp) message.getTimestamp());
            int rowsAffected = preStatement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            close();
        }
    }

    // Xóa tin nhắn
    public boolean deleteMessage(int messageId) throws SQLException {
        String sql = "DELETE FROM messages WHERE message_id = ?";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            preStatement = jdbcConnection.prepareStatement(sql);
            preStatement.setInt(1, messageId);
            int rowsAffected = preStatement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            close();
        }
    }

    // Truy vấn tin nhắn theo ID
    public Message getMessageById(int messageId) throws SQLException {
        String sql = "SELECT * FROM messages WHERE message_id = ?";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            preStatement = jdbcConnection.prepareStatement(sql);
            preStatement.setInt(1, messageId);
            resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                return new Message(resultSet.getString("message_id"), resultSet.getString("content"),
                                    resultSet.getString("sender_id"), resultSet.getString("receiver_id"),
                                    resultSet.getTimestamp("timestamp"));
            }
            return null;
        } finally {
            close();
        }
    }

    // Đóng kết nối và tài nguyên
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (jdbcConnection != null) {
                jdbcConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

