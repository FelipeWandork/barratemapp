package com.apps.barratem.model;

import java.io.Serializable;

public class Cadastro implements Serializable {

    private String anunciante;
    private String descricao;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private String telefone1;
    private String telefone2;
    private String whatsapp1;
    private String whatsapp2;
    private String email;
    private String website;
    private String facebook;
    private String instagram;

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public String getWhatsapp1() {
        return whatsapp1;
    }

    public void setWhatsapp1(String whatsapp1) {
        this.whatsapp1 = whatsapp1;
    }

    public String getsWhatsapp2() {
        return whatsapp2;
    }

    public void setWhatsapp2(String whatsapp2) {
        this.whatsapp2 = whatsapp2;
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
}
