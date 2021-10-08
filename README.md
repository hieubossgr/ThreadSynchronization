- Tạo 5 lớp để giải quyết bài toán:
- Class Main.java để chạy chương trình và bao gồm các phương thức đọc file, ghi file, sắp xếp mảng số và tìm kiếm trong file
- Class Handle.java để xử lý các công việc
- Class ThreadB.java 
  - Là một luồng thực hiện công việc đọc file đầu vào (input.txt): là một dãy số và lưu nó dưới dạng List<Integer>
  - Sau đó sắp xếp mảng đã đọc được
  - Ghi vào file đầu ra (output.txt)
- Class ThreadC.java
  - Là một luồng thực hiện công việc đọc file tìm kiếm (search.txt): cũng là một dãy số và lưu nó dưới dạng List<Integer>
  - Sau đó thực hiện công việc tìm kiếm trong file và ghi dữ liệu tìm kiếm được vào List<String> mes là một biến của lớp Main;
- Class ThreadA.java
  - Thực hiện công việc hiển thị thông tin đã tìm kiếm được đã được ghi lại ở trong List<String> mes
- Ý tưởng thực hiện:
  - Luồng B thực hiện công việc đọc file input, sắp xếp và ghi vào file output
  - Luồng C sẽ nhận đầu vào là file output(đầu ra của thread B) sau đó sẽ tìm kiếm trên file output đó và trả về một list<String> ghi lại thông tin tìm kiếm
  - Thread A sẽ hiển thị thông tin của list<String> là đầu ra của Thread C ở trên đó. 
