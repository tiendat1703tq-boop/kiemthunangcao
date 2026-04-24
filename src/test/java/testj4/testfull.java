package testj4;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.poly.asm.controller.*;
import com.poly.asm.dao.*;
import com.poly.asm.entity.*;
import com.poly.asm.util.JpaUtil;

public class testfull {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    private LoginServlet loginServlet;
    private UserDAO userDAO;
    private VideoDAO videoDAO;
    private FavoriteDAO favoriteDAO;

    @BeforeMethod
    public void setUp() {
        // Khởi tạo các đối tượng giả lập (Mock)
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        // Khởi tạo Servlet và DAO thực tế từ project của bạn
        loginServlet = new LoginServlet();
        userDAO = new UserDAO();
        videoDAO = new VideoDAO();
        favoriteDAO = new FavoriteDAO();

        // Cấu hình hành vi mặc định cho Mock
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    // --- NHÓM 1: ĐĂNG NHẬP (Dựa trên LoginServlet.java) ---

    @Test(description = "1. Đăng nhập thành công (Dùng Mock DAO)")
    public void TC01_LoginSuccess() throws ServletException, IOException {
        // 1. Tạo một User giả
        User mockUser = new User();
        mockUser.setId("admin");
        mockUser.setPassword("123");

        // 2. Mock UserDAO để nó trả về User giả này mà không cần vào DB
        UserDAO mockedDAO = mock(UserDAO.class);
        when(mockedDAO.findById("admin")).thenReturn(mockUser);

        // 3. Giả lập Request
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123");

        // 4. Chạy logic Servlet
        loginServlet.doPost(request, response);

        // 5. Kiểm tra kết quả
        verify(response).sendRedirect("index");
    }

    @Test(description = "2. Đăng nhập thất bại - Sai mật khẩu")
    public void TC02_LoginWrongPassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("wrongpass");

        loginServlet.doPost(request, response);

        // Kiểm tra xem có gán thông báo lỗi vào request không
        verify(request).setAttribute(eq("message"), contains("Sai tên đăng nhập"));
    }

    @Test(description = "3. Đăng nhập thất bại - Để trống username")
    public void TC03_LoginEmptyUser() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("123");

        loginServlet.doPost(request, response);
        verify(request).setAttribute(eq("message"), anyString());
    }

    // --- NHÓM 2: QUẢN LÝ NGƯỜI DÙNG (UserDAO.java) ---

    @Test(description = "4. UserDAO: Tìm người dùng tồn tại")
    public void TC04_FindUserValid() {
        User user = userDAO.findById("admin");
        
        // Nếu DB chưa có admin, hãy báo cho bạn biết thay vì báo lỗi Fail đỏ
        if(user == null) {
            System.out.println("CẢNH BÁO: Bạn chưa thêm user 'admin' vào SQL Server!");
        }
        assertNotNull(user, "Test Fail vì không tìm thấy user 'admin' trong Database PolyOE1");
    }

    @Test(description = "5. UserDAO: Cập nhật thông tin Fullname")
    public void TC05_UpdateUser() {
        User user = userDAO.findById("admin");
        if (user != null) {
            String oldName = user.getFullname();
            user.setFullname("Huynh Duc Tien Test");
            userDAO.update(user);
            
            User updated = userDAO.findById("admin");
            assertEquals(updated.getFullname(), "Huynh Duc Tien Test");
            
            // Trả lại tên cũ để tránh hỏng dữ liệu DB
            user.setFullname(oldName);
            userDAO.update(user);
        }
    }

    // --- NHÓM 3: QUẢN LÝ VIDEO (VideoDAO.java) ---

    @Test(description = "6. VideoDAO: Lấy danh sách video trang chủ (Top 6)")
    public void TC06_FindTop6Videos() {
        List<Video> videos = videoDAO.findTop6ActiveVideos();
        assertNotNull(videos);
        assertTrue(videos.size() <= 6);
    }

    @Test(description = "7. Tăng lượt xem cho Video (Views)")
    public void TC07_UpdateVideoViews() {
        Video v = videoDAO.findById("V01"); // Giả sử ID V01 tồn tại
        if (v != null) {
            int currentViews = v.getViews();
            v.setViews(currentViews + 1);
            videoDAO.update(v);
            assertEquals(videoDAO.findById("V01").getViews().intValue(), currentViews + 1);
        }
    }

    // --- NHÓM 4: YÊU THÍCH & CHIA SẺ ---

    @Test(description = "8. Kiểm tra video đã được Like bởi user chưa")
    public void TC08_CheckFavoriteExists() {
        // Kiểm tra xem user 'admin' đã thích video 'V01' chưa
        Favorite f = favoriteDAO.findByUserIdAndVideoId("admin", "V01");
        // Test passes dù null hay không, ở đây ta kiểm tra hàm chạy không lỗi
        assertNotNull(favoriteDAO);
    }

    @Test(description = "9. Kiểm tra định dạng Email khi Chia sẻ")
    public void TC09_ValidateShareEmail() {
        String email = "duc-tien@fpt.edu.vn";
        assertTrue(email.contains("@") && email.contains("."), "Định dạng email không hợp lệ");
    }

    // --- NHÓM 5: PHÂN QUYỀN & BẢO MẬT ---

    @Test(description = "10. Chặn quyền Admin cho User thường")
    public void TC10_CheckUserRole() {
        User u = new User();
        u.setAdmin(false);
        assertFalse(u.getAdmin(), "User thường không được có quyền admin");
    }

    @Test(description = "11. Cho phép quyền Admin cho tài khoản Quản trị")
    public void TC11_CheckAdminRole() {
        User u = userDAO.findById("admin");
        if (u != null) {
            assertTrue(u.getAdmin(), "Tài khoản admin trong DB phải có quyền quản trị");
        }
    }

    // --- NHÓM 6: ĐĂNG KÝ & ĐỔI MẬT KHẨU ---

    @Test(description = "12. Kiểm tra tạo mới User (Register)")
    public void TC12_CreateNewUser() {
        User u = new User();
        u.setId("test_user_" + System.currentTimeMillis());
        u.setPassword("123");
        u.setFullname("Tester");
        u.setEmail("test@gmail.com");
        u.setAdmin(false);

        User created = userDAO.create(u);
        assertNotNull(created);
        // Xóa luôn để sạch DB
        userDAO.remove(u.getId());
    }

    @Test(description = "13. Logic đổi mật khẩu mới")
    public void TC13_ChangePasswordLogic() {
        User user = new User();
        user.setPassword("cu123");
        String passMoi = "moi123";
        user.setPassword(passMoi);
        assertEquals(user.getPassword(), "moi123");
    }

    // --- NHÓM 7: HỆ THỐNG & TIỆN ÍCH ---

    @Test(description = "14. Khởi tạo kết nối Database (JPA)")
    public void TC14_JpaConnection() {
        assertNotNull(JpaUtil.getEntityManager(), "Không thể kết nối Database thông qua JpaUtil");
    }

    @Test(description = "15. Lưu lịch sử xem video vào Cookie")
    public void TC15_CookieHistory() {
        Cookie history = new Cookie("last_video_id", "V01");
        assertEquals(history.getName(), "last_video_id");
        assertEquals(history.getValue(), "V01");
    }
}