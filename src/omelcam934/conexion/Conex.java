package omelcam934.conexion;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Conex {
    private static Connection connect = null;
    private String bbdd = "bbdd.db";

    public Conex(String bbdd) {
        this.bbdd = bbdd;
        connectarBBDD();
    }

    public Conex() {
        if(connect==null) {
            File f = null;
            Scanner lector = null;
            try {
                f = new File("base.txt");
                lector = new Scanner(f);
                this.bbdd = lector.nextLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (lector != null)
                        lector.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }


            connectarBBDD();
        }
    }

    public void connectarBBDD(){
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+bbdd);
            if (connect!=null) {
                System.out.println("Conectado");
            }
        }catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }

    public  void cerrarBBDD(){
        try {
            connect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public String getBbdd() {
        return bbdd;
    }

    public void setBbdd(String bbdd) {
        this.bbdd = bbdd;
    }
}
