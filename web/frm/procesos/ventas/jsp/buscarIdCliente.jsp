
<%@page import="electrohouse.Controladores.ClientesControlador"%>
<%@page import="electrohouse.modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int idcliente =Integer.parseInt(request.getParameter("idcliente"));
    String nombre_cliente =request.getParameter("nombre_cliente");
    String ruc_cliente =request.getParameter("ruc_cliente");
    Clientes cliente= new Clientes();
    cliente.setIdcliente(idcliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    String tipo="error";
    String nuevo="true";
    String mensaje= "Datos no encontrados";
    cliente= ClientesControlador.buscarId(cliente);
    //System.out.println("llega");
    if (cliente.getIdcliente()!=0){
        tipo= "success";
        nuevo= "false";
        mensaje ="Datos Encontrados";
    }
    
    /*if (cliente!=null){
        tipo= "success";
        mensaje= "Datos encontrados.";
        nuevo= "false";
    }*/
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("idcliente",cliente.getIdcliente());
    obj.put("nombre_cliente",cliente.getNombre_cliente());
    obj.put("ruc_cliente",cliente.getRuc_cliente());
    //obj.put("id_cliente",cliente.getCliente(),getId_cliente());
    //obj.put("nombre_cliente",cliente.getCliente().getNombre_cliente());
    out.print(obj);
    out.flush();
    %>