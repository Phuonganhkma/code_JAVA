/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Them.Test_2.B1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuon
 */
public class HangThucPham {

    private String maHang;
    private String tenHang;
    private double donGia;
    private Date ngaySanXuat;
    private Date ngayHetHan;
    Date date = java.util.Calendar.getInstance().getTime();

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public HangThucPham() {

    }

    public HangThucPham(String maHang) {
        this.maHang = maHang;
        if (checkMa() == false) {
            return;
        }
        this.tenHang = "xxx";
        this.donGia = 1000;
        this.ngaySanXuat = date;
        this.ngayHetHan = ngaySanXuat;
    }

    public HangThucPham(String maHang, String tenHang, double donGia, Date ngaySanXuat, Date ngayHetHan) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donGia = donGia;
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public boolean checkMa() {
        if (maHang.equals("")) {
            return false;
        }
        return true;
    }

    public boolean checkTen() {
        if (this.tenHang.equals("")) {
            return false;
        }
        return true;
    }

    public boolean checkGia() {
        if (this.donGia < 0) {
            return false;
        }
        return true;
    }

    public boolean checkNgaySX() {
        if (this.ngaySanXuat.before(date)) {
            return true;
        }
        return false;
    }

    public boolean checkNgayHH() {
        if (this.ngayHetHan.after(this.ngaySanXuat)) {
            return true;
        }
        return false;
    }

    Scanner sc = new Scanner(System.in);

    public void nhap() {
        System.out.println("Mã: ");
        maHang = sc.nextLine();
        if (checkMa() == false) {
            System.out.println("Không được để trống. Tạo đối tượng không thành công");
            return;
        } else {
            System.out.println("Tên hàng: ");

            tenHang = sc.nextLine();
            if (checkTen() == false) {
                this.setTenHang("xxx");
            }
            System.out.println("Đơn giá: ");
            donGia = sc.nextDouble();
            if (checkGia() == false) {
                donGia = 0;
            }
            System.out.println("Ngày sản xuất: ");
            sc.nextLine();
            String ngay1 = sc.nextLine();
            if (ngay1.equals("")) {
                ngaySanXuat = date;
            } else {
                try {
                    ngaySanXuat = df.parse(ngay1);
                } catch (ParseException ex) {
                    Logger.getLogger(HangThucPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            while (checkNgaySX() == false && !ngay1.equals("")) {
                System.out.println("Ngày sản xuất phải trước ngày: " + df.format(date));
                String ngay2 = sc.nextLine();
                if (ngay2.equals("")) {
                    ngaySanXuat = date;
                    break;
                } else {
                    try {
                        ngaySanXuat = df.parse(ngay2);
                        
                    } catch (ParseException ex) {
                        Logger.getLogger(HangThucPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            System.out.println("Ngày hết hạn: ");
            String ngay2 = sc.nextLine();
            if (ngay2.equals("")) {
                ngayHetHan = ngaySanXuat;
            } else {
                try {
                    ngayHetHan = df.parse(ngay2);
                } catch (ParseException ex) {
                    Logger.getLogger(HangThucPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            while (checkNgayHH() == false && !ngay2.equals("")) {
                System.out.println("Ngày hết hạn phải sau ngày: " + df.format(ngaySanXuat));
                String ngay3 = sc.nextLine();
                if (ngay3.equals("")) {
                    ngayHetHan = date;
                    break;
                } else {
                    try {
                        ngayHetHan = df.parse(ngay3);
                    } catch (ParseException ex) {
                        Logger.getLogger(HangThucPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    public void xuat() {
        String kiemTra = " ";
        if (kiemTraHetHan() == false) {
            kiemTra = "Hàng hết hạn";
        }
        System.out.printf("%03d%20s%30s%30s%30s%30s\n", Integer.parseInt(maHang), tenHang, donGia + " VND", df.format(ngaySanXuat), df.format(ngayHetHan), kiemTra);
    }

    public boolean kiemTraHetHan() {
        if (ngayHetHan.before(date) == true) {
            return false;
        }
        return true;
    }

}
