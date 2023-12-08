package com.vti.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerUtils {
    private static Scanner sc = new Scanner(System.in);
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static Scanner getScanner() {
        return sc;
    }

    public static String inputString() {
        return sc.nextLine();
    }


    public static String inputEmail() {
        while (true) {
            String email = sc.nextLine().trim();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.find()) {
                return email;
            } else {
                System.err.println("Nhập sai định dạng email! Vui lòng nhập lại: ");
                System.out.print("Nhập Email: ");
            }
        }
    }
    public static String inputPassword() {
        while (true) {
            String password = sc.nextLine().trim();
            if (password.length() >= 6 && password.length() <= 12 && password.matches(".*[a-z].*")) {
                return password;
            } else {
                System.err.println("Nhập sai định dạng password! Vui lòng nhập lại: ");
                System.out.print("Nhập Password: ");
            }
        }
    }
    public static String inputFullname() {
        while(true) {
            String fullname=sc.nextLine().trim();
            if (fullname.toUpperCase().matches("^[A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ\\s]+")) {
                return fullname.replaceAll("\\s+", " ");
            } else {
                System.err.println("Nhập sai định dạng FullName! Vui lòng nhập lại:  ");

            }
        }
    }
    public static int inputInt() {
        while (true) {
            try {
                String iput = sc.nextLine().trim();
                return Integer.valueOf(iput).intValue();
            } catch (NumberFormatException e) {
                System.err.print("Vui lòng chọn lại(1~6): ");
            }
        }
    }

}
