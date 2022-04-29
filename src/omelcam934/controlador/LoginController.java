package omelcam934.controlador;

import omelcam934.dao.UsuarioBBDD;
import omelcam934.modelo.Usuario;
import omelcam934.views.LoginVista;
import omelcam934.views.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginVista vista;
    private Usuario user;
    private UsuarioBBDD usuarioBBDD;

    public LoginController() {
        vista = new LoginVista(this);
        usuarioBBDD = new UsuarioBBDD();
        vista.iniciar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.getLogInButton()){
            user = usuarioBBDD.listarUsuario(vista.getUsuario().getText(), String.valueOf(vista.getContrasenya().getPassword()));
            if(user!=null){
                omelcam934.views.Principal principal = new Principal();

                vista.setVisible(false);
                principal.iniciar(new PrincipalController(user.isAdmin()));
                //principal.iniciar(new PrincipalController());
            }else{
                vista.getLblAviso().setText("Login incorrecto");
            }
        }
    }
}
