/**
 *
 * @author Juan Camilo Fernández
 * Programa que permite registrar un formulario de inscripción a los eventos ofrecidos por la Universidad
 */

package registro;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

//La clase Formato permite obtener los datos de registro e ingresarlos a la base de datos
public class Formato extends javax.swing.JFrame {
        conectar con = new conectar();
        Vector vector = new Vector();
        Connection conexion = con.conectar();
        PreparedStatement ps; 
        int event;
        String tall;
        //Constructor
    public Formato() {
        initComponents();
        //Si la conexion es exitosa, carga datos para que el usuario haga el registro 
        if (conexion != null){
            //Selecciona países
            String sentencia = "select * from paises ";
            try{
                ps = conexion.prepareStatement(sentencia);
                ResultSet rs = ps.executeQuery();
            //Llena la lista de países en el Jcombobox
            while (rs.next())
            {
             pais.addItem(rs.getObject(2));
            }
            
            }
            //Captura la excepción de la conexion a la tabla paises
            catch (SQLException ex) {
            System.out.println("Error "+ex);
            } 
            
           //Selecciona universidades       
            String sent = "select * from universidades ";
            try{
                ps = conexion.prepareStatement(sent);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
            //Llena la lista de universidades en el Jcombobox
            {
             universidad.addItem(rs.getObject(2));
            }
            
            }
            //Captura la excepción de la conexion a la tabla universidades
            catch (SQLException ex) {
            System.out.println("Error "+ex);
            }
        
            //Selecciona ciudades según el país seleccionado por el usuario
            pais.addItemListener( new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                //Cada vez que se selecciona un país diferente, las ciudades del país anterior que estaban visibles deben eliminarse
                ciudad.removeAllItems();
                if ( e.getStateChange() == ItemEvent.SELECTED ) 
                {
                //El listener permite obtener el país que se haya seleccionado y tomar las ciudades correspondientes
                Object item = e.getItem();
                String senten = "select * from paises where nom_pais = '"+String.valueOf(item)+"'";
                              
                try{
                 ps = conexion.prepareStatement(senten);
                 ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    //Llena la lista de ciudades en el JCombobox
                 ciudad.addItem(rs.getObject(4));
                }
                }
                //captura la excepción de la conexión con la tabla paises
                catch (SQLException ex) {
                System.out.println("Error "+ex);
                }  
                }
            }
            });
            //Muestra los eventos que ofrece la Universidad
            String statement = "select distinct nombre_evento from eventos ";
            try{
                ps = conexion.prepareStatement(statement);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                //Llena la lista de eventos en el Jcombobox
                 evento.addItem(rs.getObject(1));
                }
                }
                //Captura la excepción de la conexión con la tabla Eventos
                catch (SQLException ex) {
                System.out.println("Error "+ex);
                }
            }
            //Permite obtener los talleres de los eventos ofrecidos
            evento.addItemListener( new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                //Cada vez que el usuario selecciona un nuevo evento, los talleres del evento seleccionado anteriormente no deben visualizarse
                vector.removeAllElements();
                //Si se selecciona otro evento, toma todos los talleres asociados a este
                if ( e.getStateChange() == ItemEvent.SELECTED ) 
                {
                Object item = e.getItem();
                String senten = "select * from eventos where nombre_evento = '"+String.valueOf(item)+"'";
                try{
                ps = conexion.prepareStatement(senten);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                 //Llena el vector con los nombres de los talleres obtenidos de la tabla eventos
                 //Toma el número del evento para registrarlo más adelante en la tabla de detalles del asistente al evento
                 event = rs.getInt(1);
                 vector.add(rs.getString(4));
                 //El JList se llena con los elementos del vector
                 taller.setListData(vector);
                }
                }
                //Se captura la excepción de la conexión a la BD
                catch (SQLException ex) {
                System.out.println("Error "+ex);
                }  
                }
                }
                });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taller = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        medio = new javax.swing.JComboBox();
        pais = new javax.swing.JComboBox();
        ciudad = new javax.swing.JComboBox();
        universidad = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        evento = new javax.swing.JComboBox();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        documento = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        Nombres = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("Ciudad");

        jScrollPane1.setViewportView(taller);

        jLabel6.setText("Universidad");

        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        jLabel7.setText("País");

        jLabel5.setText("Telefono");

        jLabel1.setText("Medio por el que se enteró");

        jLabel4.setText("Correo electronico");

        jLabel3.setText("Nro Documento");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "CC", "CE", "TI" }));

        jLabel9.setText("Tipo Doc.");

        medio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Periódico", "Radio", "Televisión", "Internet", "Universidades", "Congresos" }));
        medio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medioActionPerformed(evt);
            }
        });

        pais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisActionPerformed(evt);
            }
        });

        ciudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciudadItemStateChanged(evt);
            }
        });

        universidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Formato inscripción Eventos Facultad de Ingeniería");

        jLabel11.setText("Evento");

        jLabel12.setText("Taller");

        evento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        nombre.setToolTipText("");
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                nombreInputMethodTextChanged(evt);
            }
        });

        jLabel2.setText("Apellidos");

        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        Nombres.setText("Nombres");

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Juan Camilo\\Juan\\Ingeniería de Software\\Ingeniería de Software III\\Segundo corte\\inscripcion\\escudo.png")); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Universidad Libre de Colombia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(7, 7, 7))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Nombres)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(documento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegistrar)
                        .addGap(99, 99, 99)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(universidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(evento, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(medio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombres))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(105, 161, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonRegistrar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(medio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(universidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(evento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(142, 142, 142))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nombreInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreInputMethodTextChanged

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    //Este método recibe una cadena de caracteres y la convierte en número. Retorna verdadero si lo hizo o falso si existe una excepción
    private static boolean validarNumero(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
    //Este método recibe un evento al dar click en el botón registrar y retorna vacío
    //Permite validar los datos del registro y si son correctos, los ingresa a la tabla de asistentes
    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        //Valida los campos obligatorios y el formato de estos
         if (nombre.getText().isEmpty()== true)
        JOptionPane.showMessageDialog(this, "Ingrese Nombre", "Nombre", JOptionPane.ERROR_MESSAGE);
         if (apellido.getText().isEmpty()== true)
        JOptionPane.showMessageDialog(this, "Ingrese Apellidos", "Apellidos", JOptionPane.ERROR_MESSAGE);
         if (tipo.getSelectedItem()== " ")
        JOptionPane.showMessageDialog(this, "Ingrese Tipo", "Tipo", JOptionPane.ERROR_MESSAGE);
         if (documento.getText().isEmpty()== true)
        JOptionPane.showMessageDialog(this, "Ingrese Número de Documento", "Documento", JOptionPane.ERROR_MESSAGE);
         else 
              //Valida que el campo documento tenga números
           if (validarNumero(documento.getText())==false)
           JOptionPane.showMessageDialog(this, "Solo se aceptan números", "Documento", JOptionPane.ERROR_MESSAGE);       
             //Valida que la longitud del documento sea correcta                  
        if(documento.getText().length()<7 || documento.getText().length()>10)
        JOptionPane.showMessageDialog(this, "La longitud del documento es incorrecta", "Documento", JOptionPane.ERROR_MESSAGE);
        if (correo.getText().isEmpty()== true)
        JOptionPane.showMessageDialog(this, "Ingrese Correo", "Correo", JOptionPane.ERROR_MESSAGE);
        if ((telefono.getText().isEmpty())== true)
        JOptionPane.showMessageDialog(this, "Ingrese Telefono", "Telefono", JOptionPane.ERROR_MESSAGE);
         else 
            //Valida que el teléfono tenga solo números
          if (validarNumero(telefono.getText())==false)
          JOptionPane.showMessageDialog(this, "Solo se aceptan números", "Telefono", JOptionPane.ERROR_MESSAGE);       
                                      
        if (medio.getSelectedItem()== " ")
        JOptionPane.showMessageDialog(this, "Ingrese Medio", "Medio", JOptionPane.ERROR_MESSAGE);
        if (pais.getSelectedItem()== " ")
        JOptionPane.showMessageDialog(this, "Ingrese País", "País", JOptionPane.ERROR_MESSAGE);
        if (universidad.getSelectedItem()== " ")
        JOptionPane.showMessageDialog(this, "Ingrese Universidad", "Universidad", JOptionPane.ERROR_MESSAGE);
        if (evento.getSelectedItem()== " ")
        JOptionPane.showMessageDialog(this, "Ingrese Evento", "Evento", JOptionPane.ERROR_MESSAGE);
        //Valida que sea seleccionado mínimo un taller
        if (taller.getSelectedValuesList().size()==0)
        JOptionPane.showMessageDialog(this, "Debe escoger mínimo un taller", "Taller", JOptionPane.ERROR_MESSAGE);
        //Valida la conexión a la DB y si es correcta hace el registro de la inscripción
        if (conexion != null){
            String sentencia = "insert into assistans  values(?,?,?,?,?,?,?,?,?,?) ";
            String sentence = "insert into detalle_asistentes values (?,?,?) ";
            PreparedStatement prepared; 
            try{
                 ps = conexion.prepareStatement(sentencia);
                 prepared = conexion.prepareStatement(sentence);
                 int cedula =Integer.parseInt(documento.getText());
                 ps.setInt(1, cedula);
                 ps.setString(2, nombre.getText()+" "+apellido.getText());
                 ps.setString(3, String.valueOf(tipo.getSelectedItem()));
                 ps.setString(4, correo.getText());
                 ps.setInt(5, Integer.parseInt(telefono.getText()));
                 ps.setString(6, String.valueOf(medio.getSelectedItem()));
                 ps.setString(7, String.valueOf(universidad.getSelectedItem()));
                 ps.setString(8, String.valueOf(pais.getSelectedItem()));
                 ps.setString(9, String.valueOf(ciudad.getSelectedItem()));
                 ps.setString(10, String.valueOf(evento.getSelectedItem()));
                 ps.execute();
                 //Toma la cantidad de talleres seleccionados por el usuario y lo almacena en un array de String
                 int num = taller.getSelectedValuesList().size();
                 String []vlrtaller = new String[num];
                // vlrtaller= String.valueOf(taller.getSelectedValues());
                for (int i = 0; i< num; i++)
                {
                    //Registra el número de evento y los talleres seleccionados por el usuario
                    prepared.setInt(1, event);
                    vlrtaller[i]= String.valueOf(taller.getSelectedValuesList().get(i));
                    prepared.setString(2,vlrtaller[i]);
                    prepared.setInt(3, cedula);
                    prepared.execute();
                }
                JOptionPane.showMessageDialog(this, "Registro exitoso", "Registro", JOptionPane.WARNING_MESSAGE);
               //conexion.close();
                }
            //Captura la excepción del registro en la DB
                catch (SQLException ex) {
                System.out.println("Error "+ex);
                JOptionPane.showMessageDialog(this, "Ya existe un registro con el número de documento ingresado", "Registro no exitoso", JOptionPane.ERROR_MESSAGE);
                }
        }    
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void paisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_paisActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_apellidoActionPerformed

    private void ciudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciudadItemStateChanged
        // TODO add your handling code here:
         
    }//GEN-LAST:event_ciudadItemStateChanged

    private void medioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medioActionPerformed
    
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombres;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JComboBox ciudad;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField documento;
    private javax.swing.JComboBox evento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox medio;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox pais;
    private javax.swing.JList taller;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox tipo;
    private javax.swing.JComboBox universidad;
    // End of variables declaration//GEN-END:variables
}
