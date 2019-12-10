<%@page import="org.json.simple.JSONObject"%>
<%@page import="electrohouse.Controladores.CategoriasControlador"%>
<%@page import="electrohouse.modelos.Categorias"%>
<%

    int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
    String nombre_categoria = request.getParameter("nombre_categoria");

    String tipo = "error";
    String mensaje = "Datos no agregados";

    Categorias categoria = new Categorias();
    categoria.setIdcategoria(idcategoria);
    categoria.setNombre_categoria(nombre_categoria);

    if (CategoriasControlador.eliminar(categoria)) {
        tipo = "success";
        mensaje = "Datos eliminados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    System.out.println("tipo" + tipo);
    out.print(obj);
    out.flush();
%>