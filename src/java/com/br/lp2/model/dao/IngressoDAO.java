/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.model.entities.Ingresso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1147106
 */
public class IngressoDAO implements GenericDAO<Ingresso>{

    private Connection con;
    public IngressoDAO(){
        //Passo 1
        con = ConnectionDB.getInstance();
    }
    
    @Override
    public boolean insert(Ingresso ingresso) {
        boolean resp = false;
        
        //Passo 2
        String sql = "insert into ingresso(cadeira,tipo) values(?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ingresso.getCadeira());
            ps.setInt(2,ingresso.getTipo());
            
            //Passo 3
            int temp = ps.executeUpdate();
            
            //Passo 4
            resp = true;
            
            //Passo 5
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    @Override
    public List<Ingresso> read() {
        
        List<Ingresso> lista = new ArrayList<>();
        
        //Passo 2
        String sql = "select * from ingresso";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            //Passo 3
            ResultSet rs = ps.executeQuery();
            
            //Passo 4
            while(rs.next()){
                Ingresso ingresso = new Ingresso();
                ingresso.setId_ingresso(rs.getInt("id_ingresso"));
                ingresso.setCadeira(rs.getInt("cadeira"));
                ingresso.setTipo(rs.getInt("tipo"));
                lista.add(ingresso);
            }
            
            //Passo 5
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }

    @Override
    public boolean update(Ingresso ingresso) {
        boolean resp = false;
        
        //Passo 2
        String sql = "update ingresso set cadeira=?,tipo=? where id_ingresso=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ingresso.getCadeira());
            ps.setInt(2,ingresso.getTipo());
            ps.setInt(3,ingresso.getId_ingresso());
            
            //Passo 3
            int temp = ps.executeUpdate();
            
            //Passo 4
            resp = true;
            
            //Passo 5
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    @Override
    public boolean delete(Ingresso ingresso) {
        
        boolean resp = false;
        
        //Passo 2
        String sql = "delete from ingresso where id_ingresso=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ingresso.getId_ingresso());
            
            //Passo 3
            int temp = ps.executeUpdate();
            
            //Passo 4
            resp = true;
            
            //Passo 5
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    
    
}
