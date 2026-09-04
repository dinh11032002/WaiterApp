## WaiterApp

Ứng dụng Android hỗ trợ nhân viên phục vụ quản lý bàn, thực đơn và đơn hàng theo thời gian thực cho quán cà phê / nhà hàng.

![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?logo=kotlin)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Repository-blue)
![Backend](https://img.shields.io/badge/Backend-Spring%20Boot-brightgreen?logo=springboot)

> **Backend Repository:** [WaiterApp-Backend](https://github.com/dinh11032002) *(Spring Boot + MySQL)*

## Giới thiệu
**WaiterApp** giúp nhân viên phục vụ tối ưu hóa quy trình làm việc hằng ngày:
* **Xác thực:** Đăng nhập và tự động giữ phiên làm việc (hỗ trợ Offline-first).
* **Quản lý bàn:** Xem danh sách bàn, tìm kiếm và lọc nhanh theo trạng thái.
* **Thực đơn:** Xem danh sách món ăn/thức uống phân theo danh mục, tìm kiếm món linh hoạt.
* **Giỏ hàng:** Thêm món, tùy chỉnh tăng/giảm số lượng, thay thế món hoặc hủy giỏ hàng.
* **Đồng bộ dữ liệu:** Tự động đồng bộ với Backend qua REST API, đảm bảo ứng dụng **vẫn hoạt động bình thường ngay cả khi mất kết nối mạng**.

## Công nghệ sử dụng
* **Ngôn ngữ:** Kotlin
* **UI:** Jetpack Compose (Single-Activity Architecture)
* **Kiến trúc:** MVVM, Repository Pattern, Clean Architecture Concept
* **Local Database:** Room Database
* **Network:** Retrofit, OkHttp
* **Dependency Injection:** Hilt
* **Bất đồng bộ & Reactive:** Coroutines, Flow, StateFlow
* **Lưu trữ phiên:** DataStore Preferences
* **Navigation:** Jetpack Navigation Compose
* **Testing:** JUnit, MockK

## Kiến trúc

```text
[ UI (Compose) ] 
       ↓
[ ViewModel ] 
       ↓
[ Repository ]
   ├──> [ Room Database (Local Source of Truth) ]
   └──> [ Retrofit API (Remote Backend) ]
```
* **Offline-first Paradigm**: Giao diện UI luôn đọc dữ liệu phát đổi theo thời gian thực (reactive stream) từ Room Database. Repository sẽ đảm nhận việc đồng bộ dữ liệu với Backend qua REST API. Nếu mất mạng, ứng dụng vẫn hoạt động trơn tru dựa trên dữ liệu đã được lưu vết trước đó.

* **Data Mapping**: Tách biệt rõ ràng giữa DTO (API Response), Entity (Room Database) và Domain Model dùng chung cho tầng ViewModel/UI, giúp hạn chế rò rỉ phụ thuộc (dependency leakage) và tăng tính bảo trì.

## Các màn hình chính

| Màn hình | Chức năng | 
|---|---|
| Đăng nhập | Xác thực, lưu phiên đăng nhập, tự động đăng nhập lại |
| Trang chủ | Hiển thị danh sách bàn, tìm kiếm bàn, lọc bàn theo trạng thái (Trống/Có khách) |
| Thực đơn | Xem danh sách món theo danh mục, tìm kiếm món ăn, thêm món nhanh vào giỏ |
| Giỏ hàng | Xem, chỉnh sửa số lượng, thay thế món, hủy đơn |
| Đơn hàng | (đang phát triển) |

## Cài đặt và chạy thử
1. Clone repository:
```bash
   git clone https://github.com/dinh11032002/WaiterApp.git
```
2. Mở project bằng Android Studio.
3. Chạy WaiterApp-Backend trước (xem hướng dẫn tại repo đó).
4. Cấu hình baseUrl trong NetworkModule trỏ về địa chỉ Backend đang chạy (mặc định http://10.0.2.2:8080/ khi chạy trên Android Emulator).
5. Build và chạy ứng dụng.

## Tác giả

**Trương Đình**
- GitHub: github.com/dinh11032002
