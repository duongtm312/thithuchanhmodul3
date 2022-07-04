package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                studentDao.delete(id);
                req.setAttribute("students", studentDao.getAll());
                RequestDispatcher dispatcher4 = req.getRequestDispatcher("/index.jsp");
                dispatcher4.forward(req, resp);
                break;
            case "create":
                RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
                dispatcher.forward(req, resp);
            case "edit":
                showEditForm(req,resp);
                break;
            default:
                req.setAttribute("students", studentDao.getAll());
                RequestDispatcher dispatcher3 = req.getRequestDispatcher("/index.jsp");
                dispatcher3.forward(req, resp);
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = findById(id);
        RequestDispatcher dispatcher;
        if (student == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("student", student);
            dispatcher = request.getRequestDispatcher("edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Student findById(int id) {
        for (Student st : studentDao.getAll()
        ) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
               editCustomer(req,resp);
               break;
        }
    }
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String birth = request.getParameter("birth");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            int classs = Integer.parseInt(request.getParameter("class"));
            Student st = new Student(id, name, birth, address, phone, email, "");
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
                if (studentDao.editStudent(st, classs)) {
                    request.setAttribute("message", "Customer information was updated");
                } else {
                    request.setAttribute("message", "Customer was updated failed");
                }
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
            try {
                request.setAttribute("message", "Customer was updated failed");
                dispatcher.forward(request, response);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {


            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String birth = request.getParameter("birth");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            int classs = Integer.parseInt(request.getParameter("class"));
            Student st = new Student(id, name, birth, address, phone, email, "");
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
                if (studentDao.create(st, classs)) {
                    request.setAttribute("message", "New customer was created");
                } else {
                    request.setAttribute("message", "New customer was creation failed");
                }
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
            try {
                request.setAttribute("message", "New customer was creation failed");
                dispatcher.forward(request, response);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
