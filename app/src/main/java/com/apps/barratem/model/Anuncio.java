package com.apps.barratem.model;

import java.io.Serializable;

public class Anuncio implements Serializable {

    private Long id;
    private String empresa;
    private String descricao;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone1;
    private String telefone2;
    private String telefone3;
    private String telefone4;
    private String whatsapp1;
    private String whatsapp2;
    private String Whatsapp3;
    private String whatsapp4;
    private String email;
    private String website;
    private String latitude;
    private String longitude;
    private String status;
    private String facebook;
    private String instagram;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getWhatsapp1() {
        return whatsapp1;
    }

    public void setWhatsapp1(String whatsapp1) {
        this.whatsapp1 = whatsapp1;
    }

    public String getWhatsapp2() {
        return whatsapp2;
    }

    public void setWhatsapp2(String whatsapp2) {
        this.whatsapp2 = whatsapp2;
    }

    public String getWhatsapp3() {
        return Whatsapp3;
    }

    public void setWhatsapp3(String whatsapp3) {
        Whatsapp3 = whatsapp3;
    }

    public String getWhatsapp4() {
        return whatsapp4;
    }

    public void setWhatsapp4(String whatsapp4) {
        this.whatsapp4 = whatsapp4;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String celular1) {
        this.telefone3 = celular1;
    }

    public String getTelefone4() {
        return telefone4;
    }

    public void setTelefone4(String celular2) {
        this.telefone4 = celular2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
