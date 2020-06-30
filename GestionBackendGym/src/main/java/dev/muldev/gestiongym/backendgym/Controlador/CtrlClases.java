/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Controlador;

import dev.muldev.gestiongym.backendgym.Modelos.ClasesGym;
import dev.muldev.gestiongym.backendgym.Modelos.ClientesGym;
import dev.muldev.gestiongym.backendgym.Service.ServiceClases;
import dev.muldev.gestiongym.backendgym.Service.ServiceCliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CtrlClases {
    
    @Autowired
    private ServiceClases service;
    
    @Autowired
    private ServiceCliente serviceCliente;
    
    
    //control de clases
    //solo obtenemos las clases de ESTA SEMANA
   
    @GetMapping("/clases")
    public Map<Integer, List<ClasesGym>> listaClases(){
        List<ClasesGym> lista = null;
        List<List<ClasesGym>> listaDeListas = new ArrayList<List<ClasesGym>>();
        
        Map<Integer, List<ClasesGym>> resultado = new TreeMap<Integer, List<ClasesGym>>();

        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df = new SimpleDateFormat("dd");
            
            //desde lunes
            LocalDate now = LocalDate.now();
            String desde = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1L);
            String hasta = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7L);
            String martes = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 2L);
            String miercoles = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 3L);
            String jueves = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 4L);
            String viernes = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 5L);
            String sabado = ""+now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 6L);

            Date fDesde = format.parse(desde);
            Date fHasta = format.parse(hasta);
            
            /*
                BUGFIX 30/6/2020 por error a finales de mes
                Christian Carsin (MulDev)
            
            */
            
            String [] diasAux = {desde,martes,miercoles,jueves,viernes,sabado,hasta};
            
            lista = service.listaClasesSemana(fDesde, fHasta);

            //group by fecha
            Map<Date, List<ClasesGym>> result = lista.stream().collect(Collectors.groupingBy(ClasesGym::getFechaClase));

            for (Map.Entry<Date, List<ClasesGym>> entry : result.entrySet()) {
                
                ArrayList <ClasesGym> clases = new ArrayList(entry.getValue());
                
                //ordenamos lista por hora
                Collections.sort(clases, new Comparator<ClasesGym>() {
                    public int compare(ClasesGym o1, ClasesGym o2) {
                        if (o1.getHoraClase() == null || o2.getHoraClase() == null)
                          return 0;
                        return o1.getHoraClase().compareTo(o2.getHoraClase());
                    }
                });

                listaDeListas.add(clases);
                
                for (List<ClasesGym> l: listaDeListas){
                    //segun sea el dia le pondremos una key u otra
                    Date date = l.get(0).getFechaClase();
                    String ds = format.format(date);
                    
                    if(ds.equals(diasAux[0])){
                        resultado.put(1, l);
                    }
                    else if(ds.equals(diasAux[1])){
                        resultado.put(2, l);
                    }
                    else if(ds.equals(diasAux[2])){
                        resultado.put(3, l);
                    }
                    else if(ds.equals(diasAux[3])){
                        resultado.put(4, l);
                    }
                    else if(ds.equals(diasAux[4])){
                        resultado.put(5, l);

                    }
                    else if(ds.equals(diasAux[5])){
                        resultado.put(6, l);
                    }
                    else if(ds.equals(diasAux[6])){
                        resultado.put(7, l);
                    }

                }
                
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    private Integer[] listaClientes(ClasesGym clase){
        //obtenemos los clientes para esa clase
        Integer [] clientes = (Integer[]) clase.getListaClientes();
        return clientes;
    }
    
    //decodificamos el token JWT para comprobar si es el usuario correcto
    public String decodeJWT(String tkn){
        try{
            String[] split_string = tkn.split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            String base64EncodedSignature = split_string[2];

            Base64 base64Url = new Base64(true);

            String body = new String(base64Url.decode(base64EncodedBody));
            String[] parts = body.split(":");

            String username = parts[1].replace("\"", "").replace("}", "");
            return username;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        } 
        
    }
    
    
    //le pasamos el id de la clase e insertamos el id del usuario a la clase en concreto
    
    @PostMapping("/clases")
    public boolean reservaClase(@RequestParam (name = "nomusu") String nomusu,
            @RequestParam (name = "id") String idClase,
            @RequestParam (name = "token") String token){
        
        //comprobamos que sea el usuario correcto
        
        String username = decodeJWT(token);
        
        if (nomusu.equals(username)){
        
            //buscamos el id de ese usuario y se lo insertamos a la lista de clientes de ESA clase

            ClientesGym cli = serviceCliente.buscaPorUsername(nomusu);

            int id = -1;

            try{
                id = Integer.parseInt(idClase);
            }
            catch(NumberFormatException e){
                System.out.println("Error");
            }

            boolean add = true;


            if (id != -1){
                ClasesGym clase = service.getOne(id);
                Integer [] clientes = listaClientes(clase);
                for (int i=0; i<clientes.length;i++){
                    if (clientes[i] == cli.getIdcliente()){
                        add = false;
                    }
                }
                if (add){
                    clase.setListaClientes(append(clientes, cli.getIdcliente()));
                    service.actualizaCliente(clase);
                    return true;
                }
                else{
                    System.out.println("Ya esta el cliente en esta clase");
                    return false;
                }

            }
        }
        
        return false;
       
        
    }
    
    private Integer[] append(Integer[] arr, int element) {
	Integer[] array = new Integer[arr.length + 1];
	System.arraycopy(arr, 0, array, 0, arr.length);
	array[arr.length] = element;
	return array;
    }
}
