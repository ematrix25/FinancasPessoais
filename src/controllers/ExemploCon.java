package controllers;
//Exemplo de controlador
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

package controllers;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Con_Cliente {
    
    public Con_Cliente() {
    }
      
    public boolean verificaCnh(String cnh) {
    	cnh = cnh.replaceAll(" ", "");
        return verificaCpf(cnh);
    }
    
    // Formato:
    // (XX)XXXX-XXXX
    // X = Dígito de [0-9]
    public boolean verificaTelefone(String telefone) {
    	telefone = telefone.replaceAll(" ", "");
        return telefone.matches("\\(+[0-9]+\\)+\\d{4}+\\-+\\d{4}");
    }
    
    public boolean verificaEmail(String email) {
    	email = email.replaceAll(" ", "");
        return email.matches("[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})");
    }
    
    public boolean verificaDia(String dia) {
    	dia = dia.replaceAll(" ", "");
        if(dia.isEmpty()) {
            return false;
        }
        for(int i = 0; i < dia.length(); i++) {
            if(!isDigit(dia.charAt(i))) {
                return false;
            }
        }
        int n = Integer.parseInt(dia);
        if(n <= 0 || n > 31) {
            return false;
        }
        return true;
    }
    public boolean verificaMes(String mes) {
    	mes = mes.replaceAll(" ", "");
        if(mes.isEmpty()) {
            return false;
        }
        for(int i = 0; i < mes.length(); i++) {
            if(!isDigit(mes.charAt(i))) {
                return false;
            }
        }
        int n = Integer.parseInt(mes);
        if(n <= 0 || n > 12) {
            return false;
        }
        return true;
    }
    
    public boolean verificaAno(String ano) {
    	ano = ano.replaceAll(" ", "");
        if(ano.isEmpty()) {
            return false;
        }
        for(int i = 0; i < ano.length(); i++) {
            if(!isDigit(ano.charAt(i))) {
                return false;
            }
        }
        int n = Integer.parseInt(ano);
        if(n <= 0) {
            return false;
        }
        return true;
    }
    
    public boolean verificaNome(String nome) {
        if(nome.length() == 0 || nome.length() > 70) { return false; }
        int begin = 0, end = nome.length() - 1;       
        while(!nome.isEmpty() && nome.charAt(begin) == ' ') { begin++; }        
        while(!nome.isEmpty() && nome.charAt(end) == ' ') { end--; }
        if(nome.isEmpty() || begin > end) { return false; }
        nome = nome.substring(begin,end+1);
        return nome.matches("([A-Z]|[a-z]|\\s)*");
    }
    
    private int pInt(char x) {
        String temp = "";
        temp += x;
        return Integer.parseInt(temp);
    }
    
    public boolean verificaCpf(String cpf) {
        boolean ok = true;
        ok = cpf.matches("\\d{3}+\\.+\\d{3}+\\.+\\d{3}+\\-+\\d{2}");
        if(!ok) { return false; }
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("\\-", "");
        if(cpf.length() != 11) { return false; }
        boolean equal = true;
        for(int i = 1; i < 11; i++) {
            if(cpf.charAt(i) != cpf.charAt(i-1)) { equal = false; break; }
        }
        if(equal) { return false; }
        for(int i = 0; i < cpf.length(); i++) {
            if(isLetter(cpf.charAt(i))) { return false; }
        }
        int sum = 0;
        for(int i = 0, j = 10; i <= 8; i++,j--) { sum += j * pInt(cpf.charAt(i)); }        
        int first = ((sum%11) < 2) ? 0 : (11-(sum%11));       
        if(first != pInt(cpf.charAt(9))) { return false; }        
        sum = 0;
        for(int i = 0, j = 11; i <= 8; i++, j--) { sum += j * pInt(cpf.charAt(i)); }
        sum += first * 2;
        int second = sum%11;
        second = (second < 2) ? 0 : 11-second;
        if(second != pInt(cpf.charAt(10))) { return false; }        
        return true;
    }
}*/