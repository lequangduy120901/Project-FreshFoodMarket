/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.extra;

/**
 *
 * @author zedqu
 */
public class DVHC {
    private String maDVHC;
    private String Ten;
    private String CapTren;
    private String Cap;

    public DVHC() {
    }

    public DVHC(String maDVHC, String Ten, String CapTren, String Cap) {
        this.maDVHC = maDVHC;
        this.Ten = Ten;
        this.CapTren = CapTren;
        this.Cap = Cap;
    }

    public String getMaDVHC() {
        return maDVHC;
    }

    public void setMaDVHC(String maDVHC) {
        this.maDVHC = maDVHC;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getCapTren() {
        return CapTren;
    }

    public void setCapTren(String CapTren) {
        this.CapTren = CapTren;
    }

    public String getCap() {
        return Cap;
    }

    public void setCap(String Cap) {
        this.Cap = Cap;
    }

    @Override
    public String toString() {
        return "DVHC{" + "maDVHC=" + maDVHC + ", Ten=" + Ten + ", CapTren=" + CapTren + ", Cap=" + Cap + '}';
    }
    
}
