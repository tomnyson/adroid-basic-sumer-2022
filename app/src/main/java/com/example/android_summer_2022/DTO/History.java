package com.example.android_summer_2022.DTO;

import java.util.Date;

public class History {
    private int id;
    private int hinh;
    private Date thoigian;
    private boolean ketqua;
    private  int soDuDoan;
    private  int ketQuaCuoiCung;

    public History() {
    }

    public History(int hinh, Date thoigian, boolean ketqua, int soDuDoan, int ketQuaCuoiCung) {
        this.hinh = hinh;
        this.thoigian = thoigian;
        this.ketqua = ketqua;
        this.soDuDoan = soDuDoan;
        this.ketQuaCuoiCung = ketQuaCuoiCung;
    }

    public History(int id, int hinh, Date thoigian, boolean ketqua, int soDuDoan, int ketQuaCuoiCung) {
        this.id = id;
        this.hinh = hinh;
        this.thoigian = thoigian;
        this.ketqua = ketqua;
        this.soDuDoan = soDuDoan;
        this.ketQuaCuoiCung = ketQuaCuoiCung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }

    public boolean isKetqua() {
        return ketqua;
    }

    public void setKetqua(boolean ketqua) {
        this.ketqua = ketqua;
    }

    public int getSoDuDoan() {
        return soDuDoan;
    }

    public void setSoDuDoan(int soDuDoan) {
        this.soDuDoan = soDuDoan;
    }

    public int getKetQuaCuoiCung() {
        return ketQuaCuoiCung;
    }

    public void setKetQuaCuoiCung(int ketQuaCuoiCung) {
        this.ketQuaCuoiCung = ketQuaCuoiCung;
    }
}
