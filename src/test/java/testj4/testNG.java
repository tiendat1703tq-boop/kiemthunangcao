package testj4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.annotations.Test;

import com.poly.asm.controller.LoginServlet;

public class testNG {
    @Test
    public void testLoginWrongPassword() throws Exception {
        // Tạo các đối tượng giả lập (Mock)
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        // Giả lập dữ liệu người dùng nhập vào
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("wrong_pass");
        when(request.getRequestDispatcher("/views/login.jsp")).thenReturn(dispatcher);

        // Chạy Servlet
        LoginServlet servlet = new LoginServlet();
        servlet.doPost(request, response);

        // Kiểm tra xem message lỗi có hiển thị đúng không
        verify(request).setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
    }
}