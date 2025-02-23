import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer intentos = (Integer) session.getAttribute("intentos");
        if (intentos == null) {
            intentos = 0;
        }

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // Validar campos vacíos
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            response.getWriter().write("<p style='color: red;'>Usuario y contraseña son obligatorios.</p>");
            return;
        }

        // Crear objeto JSON
        JSONObject datos = new JSONObject();
        datos.put("usuario", usuario);
        datos.put("contrasena", contrasena);

        // Convertir a cadena JSON
        String jsonString = datos.toString();
        System.out.println(jsonString);

        // Validar usuario y contraseña
        if (validarUsuario(usuario, contrasena)) {
            session.setAttribute("intentos", 0); // Resetear intentos en caso de éxito
            response.sendRedirect("siguiente_vista.html");
        } else {
            intentos++;
            session.setAttribute("intentos", intentos);
            if (intentos >= 3) {
                response.getWriter().write("<p style='color: red;'>Has alcanzado el número máximo de intentos. El formulario se bloqueará.</p>");
            } else {
                response.getWriter().write("<p style='color: red;'>Usuario o contraseña incorrectos. Intentos restantes: " + (3 - intentos) + ".</p>");
            }
        }
    }

    private boolean validarUsuario(String usuario, String contrasena) {
        // Validar usuario y contraseña (esto es solo un ejemplo)
        return "alumnodasc".equals(usuario) && "1234".equals(contrasena);
    }
}