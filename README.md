WaiterApp

Ứng dụng Android hỗ trợ nhân viên phục vụ quản lý bàn, thực đơn và đơn hàng theo thời gian thực cho quán cà phê / nhà hàng.

Backend: WaiterApp-Backend — Spring Boot + MySQL

Giới thiệu
WaiterApp giúp nhân viên phục vụ:
Đăng nhập và giữ phiên làm việc (offline-first)
Xem danh sách bàn, tìm kiếm, lọc theo trạng thái
Xem thực đơn theo danh mục, tìm kiếm món
Thêm món vào giỏ hàng, tăng/giảm số lượng, thay thế món, hủy giỏ hàng
Đồng bộ dữ liệu với Backend qua REST API, vẫn hoạt động khi mất kết nối mạng
Công nghệ sử dụng
Ngôn ngữ: Kotlin
UI: Jetpack Compose
Kiến trúc: MVVM, Repository Pattern
Local Database: Room
Network: Retrofit, OkHttp
Dependency Injection: Hilt
Bất đồng bộ: Coroutines, Flow, StateFlow
Lưu trữ phiên đăng nhập: DataStore
Navigation: Jetpack Navigation Compose
Testing: JUnit, MockK

Kiến trúc
UI (Compose) → ViewModel → Repository → [ Room (local) | Retrofit (API) ]
Offline-first: Giao diện luôn đọc dữ liệu từ Room. Repository đồng bộ dữ liệu với Backend qua API; nếu mất mạng, ứng dụng vẫn hoạt động dựa trên dữ liệu đã đồng bộ trước đó.
DTO / Entity / Domain Model tách biệt: Dữ liệu từ API (DTO) và từ Room (Entity) đều được ánh xạ về Domain Model dùng chung cho tầng UI/ViewModel.

Các màn hình chính
Màn hình	Chức năng
Đăng nhập	Xác thực, lưu phiên đăng nhập, tự động đăng nhập lại
Trang chủ	Danh sách bàn, tìm kiếm, lọc theo trạng thái
Thực đơn	Danh sách món theo danh mục, tìm kiếm, thêm vào giỏ hàng
Giỏ hàng	Xem, chỉnh sửa số lượng, thay thế món, hủy đơn
Đơn hàng	(đang phát triển)

Cài đặt và chạy thử
Clone repository:
bash
   git clone https://github.com/dinh11032002/WaiterApp.git
Mở project bằng Android Studio.
Chạy WaiterApp-Backend trước (xem hướng dẫn tại repo đó).
Cấu hình baseUrl trong NetworkModule trỏ về địa chỉ Backend đang chạy (mặc định http://10.0.2.2:8080/ khi chạy trên Android Emulator).
Build và chạy ứng dụng.

Tác giả
Trương Đình
GitHub: github.com/dinh11032002
