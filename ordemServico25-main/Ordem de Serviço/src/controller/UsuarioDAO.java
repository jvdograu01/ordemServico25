/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import jdbc.ModuloConexao;
import view.TelaLogin;
import view.TelaPrincipal;

/**
 *
 * @author clebe
 */
public class UsuarioDAO {
    
    private Connection conexao;
    
    public UsuarioDAO(){
        this.conexao = ModuloConexao.conectar();
    }
    
    //Metodo efetuaLogin
    public void efetuaLogin(String email, String senha ) {
       
        try {

            //1 passo - SQL
            String sql = "select * from tbusuarios where usuario = ? and senha = ?";
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Usuario logou
                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);
                
            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new TelaLogin().setVisible(true);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }
    
    
    public void adicionarUsuario (Usuario obj) {
        try {
            string sql = "insert into tbusuario (iduder, usuario, fone, login, senha, perfil) values (?,?,?,?,?,?)";
            conexao = ModuloConexao.conectar();
            PreparadeStatement stmt = conexao.prepareStatement (sql);
            stmt.setInt(1, obj.getIdUser());
            smtm.setString(2, obj.getUsuario());
            smtm.setString(3, obj.getFone());
            smtm.setString(4, obj.getLogin());
            smtm.setString(5, obj.getSenha());
            smtm.setString(6, obj.getPerfil());
            
            smtm.execute();
            smtm.close();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
            
        } catch (SQLIntegrityConstraintViolationException el) {
            JOptionPane.showMessageDialog(null, "login em uso.|nEscolha outro login.");
        } catch (HeadlessException | SqlException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
        try {
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
            
            }
    }
    public  Usuario buscarUsuario(int idUser){
        try {
            String sql = "select * from tbusuarios WHERE iduser = ?;";
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            smtm.SetInt(1, idUser);
            resultSet rs = smtm.executeQuery();
            
            smtm.close();
            if(rs.next()){
                Usuario usuario = new Usuario ();
                usuario.setIdUser(rs.getInt("iduser"));
                usuario.setUsuario(rs.geString("usuario"));
                usuario.setFone(rs.geString("fone"));
                usuario.setLogin(rs.geString("login"));
                usuario.setSenha(rs.geString("senha"));
                usuario.setPerfil(rs.geString("perfil"));
                
                return usuario;
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
            }catch (SQLException e) {
                JOPtionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
