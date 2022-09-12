package persistence;

import model.Questions;
import util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;

public class DaoQuestions {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DaoQuestions() {
        connection = DbUtil.getDBConnection();
    }

    public void saveQuestion(Questions questions) {
        String sql = "INSERT INTO questions (questionId, question, option1, option2, option3, option4)" +
                "VALUES(?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,questions.getQuestionId());
            preparedStatement.setString(2, questions.getQuestion());
            preparedStatement.setString(3, questions.getOption1());
            preparedStatement.setString(4,questions.getOption2());
            preparedStatement.setString(5,questions.getOption3());
            preparedStatement.setString(6,questions.getOption4());
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                System.out.println("question saved!");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateQuestion(Questions questions) {
        String sql = "UPDATE questions SET question = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ? " +
                "WHERE questionId = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, questions.getQuestion());
            preparedStatement.setString(2,questions.getOption1());
            preparedStatement.setString(3,questions.getOption2());
            preparedStatement.setString(4, questions.getOption3());
            preparedStatement.setString(5, questions.getOption4());
            preparedStatement.setInt(6, questions.getQuestionId());
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                System.out.println("Question updated!");
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteQuestion(int questionId) {
        String sql = "DELETE FROM questions WHERE questionId = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,questionId);
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                System.out.println("Question " + questionId + " removed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Questions searchQuestion(int questionId) {
        String sql = "SELECT * FROM questions WHERE questionId = ?";
        Questions questions = new Questions();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,questionId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                questions.setQuestion(resultSet.getString("question"));
                questions.setOption1(resultSet.getString("option1"));
                questions.setOption2(resultSet.getString("option2"));
                questions.setOption3(resultSet.getString("option3"));
                questions.setOption4(resultSet.getString("option4"));
                questions.setQuestionId(resultSet.getInt("questionId"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return questions;
    }

    public ArrayList<Questions> listAllQuestions(){

        String sql = "SELECT * FROM questions";
        ArrayList<Questions> list = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Questions questions = new Questions();
                questions.setQuestionId(resultSet.getInt("questionId"));
                questions.setQuestion(resultSet.getString("question"));
                questions.setOption1(resultSet.getString("option1"));
                questions.setOption2(resultSet.getString("option2"));
                questions.setOption3(resultSet.getString("option3"));
                questions.setOption4(resultSet.getString("option4"));
                list.add(questions);
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }



}
