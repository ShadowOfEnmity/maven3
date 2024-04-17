package by.javaguru;

import by.javaguru.exceptions.JsonReadWriteException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.management.ConstructorParameters;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Optional;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        String filePath = context.getRealPath("/") + "users.json";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.setProperty("users.path",file.getPath());
                userService = new UserService();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = null;
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.matches("^/\\d+$")) {
            String[] parts = pathInfo.split("/");
            String userId = parts[parts.length - 1];
            long id = Long.parseLong(userId);
            var user = userService.getUser(id);
            req.setAttribute("user", user.get());
            requestDispatcher = req.getRequestDispatcher("/user.jsp");
        } else {
            var users = userService.getAllUsers();
            req.setAttribute("users", users);
            requestDispatcher = req.getRequestDispatcher("/users.jsp");
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.matches("^/\\d+$")) {
            String[] parts = pathInfo.split("/");
            String userId = parts[parts.length - 1];
            long id = Long.parseLong(userId);
            var user = userService.getUser(id).get();
            user.setSurname(req.getParameter("surname"));
            user.setName(req.getParameter("name"));
            user.setPatronymic(req.getParameter("patronymic"));
            userService.updateUser(user);
            req.setAttribute("user", user);
            var requestDispatcher = req.getRequestDispatcher("/user.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ServletContext context = getServletContext();
        String filePath = context.getRealPath("/") + "users.json";
        File file = new File(filePath);
        if (file.exists()) {
            try {
                file.delete();
            } catch (SecurityException e) {
                e.getStackTrace();
            }
        }
    }
}
