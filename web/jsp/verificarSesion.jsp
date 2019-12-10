<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.modelos.Usuarios"%>
<%@page import="java.sql.ResultSet"%>
<%
HttpSession sesion=request.getSession();
int idusuario = 0;
String login_usuario="";
String nombre_usuario="";
String activo="false";
String mensaje="La sesión está cerrada.";
Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
if(usuarioLogueado!=null){
idusuario = usuarioLogueado.getIdusuario();
login_usuario = usuarioLogueado.getLogin_usuario();
nombre_usuario = usuarioLogueado.getNombre_usuario();
activo = "true";
mensaje="La sesión está abierta.";
}
JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("activo", activo);
obj.put("idusuario",idusuario);
obj.put("login_usuario",login_usuario);
obj.put("nombre_usuario",nombre_usuario);
out.print(obj);
out.flush();
%>
