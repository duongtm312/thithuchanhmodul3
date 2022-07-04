package dao;

import connect.Connect_MySQL;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {
    public List<Student> getAll() {
        String sql = "SELECT * FROM student_codegym.students join class on students.class=class.id";
        List<Student> students = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("StudentID");
                String name = resultSet.getString("StudentName");
                String birth = resultSet.getString("birth");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String Email = resultSet.getString("Email");
                String classname = resultSet.getString("classname");
                students.add(new Student(id, name, birth, address, phone, Email, classname));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }
    public boolean create(Student student,int idClass) {
        String sql = "insert into students value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getBirth());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setInt(7, idClass);

            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
    public boolean delete(int id){
        String sql ="DELETE FROM `student_codegym`.`students` WHERE (`StudentID` =" + id+")";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public boolean editStudent(Student student,int idClass) {
        String sql = "UPDATE `student_codegym`.`students` SET  `StudentName` = ?, `birth` = ?, `address` =?, `phone` = ?, `Email` =?, `class` = ? WHERE (`StudentID` = ?);";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getBirth());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, idClass);

            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}
