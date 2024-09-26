/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian
 */
public class Docentes extends Persona {
    private int id;
    private String codigo;
    private Double salario;
    private String fecha_laborar,fecha_registro;
    Conexion cn;
    
   public Docentes(){};

    public Docentes(int id, String codigo, Double salario, String fecha_laborar, String fecha_registro, String nit, String nombres, String apellidos, String direccion, String telefono, String fn) {
        super(nit, nombres, apellidos, direccion, telefono, fn);
        this.id = id;
        this.codigo = codigo;
        this.salario = salario;
        this.fecha_laborar = fecha_laborar;
        this.fecha_registro = fecha_registro;
    }
   
    
   
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getFecha_laborar() {
        return fecha_laborar;
    }

    public void setFecha_laborar(String fecha_laborar) {
        this.fecha_laborar = fecha_laborar;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn= new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT id_persona as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,codigo_docente,salario,Fecha_ingreso_laborar,Fecha_ingreso_registro from personas;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            
            String encabezado[] = {"id","nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento", "codigo","salario","Fecha Laboral","Fecha Registro"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]=new String[11];
          
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("codigo_docente");
                datos[8] = consulta.getString("salario");
                datos[9] = consulta.getString("Fecha_ingreso_laborar");
                datos[10] = consulta.getString("Fecha_ingreso_registro");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return tabla;
    }
    
    @Override
    public void agregar() {
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "INSERT INTO personas (`nit`,`nombres`,`Apellidos`,`direccion`,`telefono`,`Fecha_nacimiento`,`codigo_docente`,`Salario`,`Fecha_ingreso_laborar`,`Fecha_ingreso_registro`) VALUES (?,?,?,?,?,?,?,?,?,?);";
           parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFn());
            parametro.setString(7, getCodigo());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_laborar());
            parametro.setString(10, getFecha_registro());
           
            
           int executer = parametro.executeUpdate();
           System.out.println("Inserto exitoso");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Algo salio mal" + ex.getMessage());
        }
    }
    
    public void borrar(){
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "Delete FROM personas WHERE `id_persona` = ?;";
           parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
           parametro.setInt(1, getId());
           int executer = parametro.executeUpdate();
           System.out.println("Eliminación exitosa");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Algo salio mal" + ex.getMessage());
        }
    }  
    public void actualizar(){
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "UPDATE `db_escuela`.`personas` SET `nit` = ?, `nombres` = ?,`Apellidos` = ?,`direccion` = ?,`telefono` = ?,`Fecha_nacimiento` = ?,`codigo_docente` = ?,`Salario` = ?,`Fecha_ingreso_laborar` = ?,`Fecha_ingreso_registro` = ? WHERE `id_persona` = ?;";
           parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFn());
            parametro.setString(7, getCodigo());
            parametro.setDouble(8, getSalario());
            parametro.setString(9, getFecha_laborar());
            parametro.setString(10, getFecha_registro());
            parametro.setInt(11, getId());        
            
           int executer = parametro.executeUpdate();
           System.out.println("Modificación exitosa");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Algo salio mal" + ex.getMessage());
        }

    }
 
    
}
