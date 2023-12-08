package com.vti.fontend;
import com.vti.backend.controller.Controller;
import com.vti.backend.repository.Repository;
import com.vti.entity.User;
import com.vti.utils.JBDCUtils;
import com.vti.utils.ScannerUtils;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;


public class Main {


    private static Controller controller = new Controller();
    private static String leftAlignmentFormat = "| %-5d | %-15s | %-20s | %n";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        while (true) {
            try {
                System.out.println("====================================================");
                System.out.println("1. In ra thông tin của tất cả User.");
                System.out.println("2. Nhập id của User và in ra thông tin User đó.");
                System.out.println("3. Nhập id của User và xóa toàn bộ thông tin User đó.");
                System.out.println("4. Chức năng login.");
                System.out.println("5. Chức năng tạo User dành cho Admin.");
                System.out.println("6. Thoát chương trình.");
                System.out.println("====================================================");
                System.out.print("Chọn chức năng bạn muốn: ");
                int menuChoose= ScannerUtils.inputInt();
                switch (menuChoose) {
                    case 1:
                        question2();
                        break;
                    case 2:
                        question3();
                        break;
                    case 3:
                        question4();
                        break;
                    case 4:
                        question5();
                        break;
                    case 5:
                        question6();
                        break;
                    case 6:
                        System.out.println("Kết thúc chương trình...");
                        return;
                    default:
                        System.out.println("Vui lòng chọn lại(1~6): ");
                        break;
                }


            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void question2() throws ClassNotFoundException, SQLException {
        System.out.printf("| %-5s | %-15s | %-20s | %n", "ID", "FULLNAME", "EMAIL");
        System.out.println("|-------|-----------------|----------------------|");
        for (User user : controller.getListUser()) {
            System.out.printf(leftAlignmentFormat, user.getId(), user.getFullName(), user.getEmail());
        }
    }

    public static void question3() throws ClassNotFoundException, SQLException {
        System.out.print("Nhập ID của user để in ra thông tin: ");
        int id = ScannerUtils.inputInt();

        System.out.printf("| %-5s | %-15s | %-20s | %n", "ID", "FULLNAME", "EMAIL");
        System.out.println("|-------|-----------------|----------------------|");
        for (User user : controller.getUser(id)) {
            System.out.printf(leftAlignmentFormat, user.getId(), user.getFullName(), user.getEmail());
        }
    }
    public static void question4() throws ClassNotFoundException, SQLException {

        System.out.printf("| %-5s | %-15s | %-20s | %n", "ID", "FULLNAME", "EMAIL");
        System.out.println("|-------|-----------------|----------------------|");
        for (User user : controller.getListUser()) {
            System.out.printf(leftAlignmentFormat, user.getId(), user.getFullName(), user.getEmail());
        }

        System.out.print("Nhập ID của user để xóa thông tin: ");
        int id = ScannerUtils.inputInt();
        Repository repository = new Repository();
        repository.deleteUser(id);

        System.out.println("Đã xóa toàn bộ thông tin User của ID: " + id);

        System.out.printf("| %-5s | %-15s | %-20s | %n", "ID", "FULLNAME", "EMAIL");
        System.out.println("|-------|-----------------|----------------------|");
        for (User user : controller.getListUser()) {
            System.out.printf(leftAlignmentFormat, user.getId(), user.getFullName(), user.getEmail());
        }

    }
    public static void question5() throws ClassNotFoundException, SQLException {
        System.out.println("=================Đăng Nhập=================");
        System.out.print("Nhập Email: ");
        String email = ScannerUtils.inputEmail();
        System.out.print("Nhập Password: ");
        String password = ScannerUtils.inputPassword();
        User user = controller.isLogin(email, password);
        if (user != null ) {
            System.out.println("Đăng nhập thành công");
        } else {
            System.out.println("Đăng nhập không thành công");
        }
    }

    public static void question6() throws ClassNotFoundException, SQLException, Exception {
        System.out.println("Bạn phải đăng nhập bằng tài khoản ADMIN");
        System.out.println("=================Đăng Nhập=================");
        System.out.print("Nhập Email: ");
        String email = ScannerUtils.inputEmail();
        System.out.print("Nhập Password: ");
        String password = ScannerUtils.inputPassword();
        User admin = controller.isLogin(email, password);

        if (admin != null && admin.getProSkill() == null) {
            System.out.println("Đăng nhập thành công!");
            System.out.println("=================Tạo tài khoản Employee=================");
            System.out.print("Nhập Email: ");
            String inputEmail = ScannerUtils.inputEmail();
            System.out.print("Nhập FullName: ");
            String fullName = ScannerUtils.inputFullname();

            controller.createEmployee(fullName, inputEmail);
            System.out.println("Tạo tài khoản:" + inputEmail + "thành công!");
            System.out.printf("| %-5s | %-15s | %-20s | %n", "ID", "FULLNAME", "EMAIL");
            System.out.println("|-------|-----------------|----------------------|");
            for (User user : controller.getListUser()) {
                System.out.printf(leftAlignmentFormat, user.getId(), user.getFullName(), user.getEmail());
            }
        } else {
            System.out.println("Đăng nhập không thành công!");
        }


    }
}

