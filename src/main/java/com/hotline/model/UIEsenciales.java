package com.hotline.model;

public class UIEsenciales {
    private String mensaje;
    private String srcImg;

    public UIEsenciales(String mensage, String srcImg){
        this.mensaje=mensage;
        this.srcImg=srcImg;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }
    public String getSrcImg() {
        return srcImg;
    }
}
