package br.edu.ifpr.comat.entity;
// Generated 23/09/2013 21:24:33 by Hibernate Tools 3.2.1.GA



/**
 * TbPessoaJuridica generated by hbm2java
 */
public class TbPessoaJuridica  implements java.io.Serializable {


     private long cnpjPju;
     private Integer iePju;
     private String razaoPju;
     private String fantasiaPju;
     private String faxCli;
     private int idCli;

    public TbPessoaJuridica() {
    }

	
    public TbPessoaJuridica(long cnpjPju, String razaoPju, int idCli) {
        this.cnpjPju = cnpjPju;
        this.razaoPju = razaoPju;
        this.idCli = idCli;
    }
    public TbPessoaJuridica(long cnpjPju, Integer iePju, String razaoPju, String fantasiaPju, String faxCli, int idCli) {
       this.cnpjPju = cnpjPju;
       this.iePju = iePju;
       this.razaoPju = razaoPju;
       this.fantasiaPju = fantasiaPju;
       this.faxCli = faxCli;
       this.idCli = idCli;
    }
   
    public long getCnpjPju() {
        return this.cnpjPju;
    }
    
    public void setCnpjPju(long cnpjPju) {
        this.cnpjPju = cnpjPju;
    }
    public Integer getIePju() {
        return this.iePju;
    }
    
    public void setIePju(Integer iePju) {
        this.iePju = iePju;
    }
    public String getRazaoPju() {
        return this.razaoPju;
    }
    
    public void setRazaoPju(String razaoPju) {
        this.razaoPju = razaoPju;
    }
    public String getFantasiaPju() {
        return this.fantasiaPju;
    }
    
    public void setFantasiaPju(String fantasiaPju) {
        this.fantasiaPju = fantasiaPju;
    }
    public String getFaxCli() {
        return this.faxCli;
    }
    
    public void setFaxCli(String faxCli) {
        this.faxCli = faxCli;
    }
    public int getIdCli() {
        return this.idCli;
    }
    
    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }




}


