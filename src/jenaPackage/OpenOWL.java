// Package 
package jenaPackage;

//Imports
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author None
 */
//Class OpenOWL
class OpenOWL {
   static  String  s;
   
   //Connexion
     static  OntModel OpenConnectOWL(){
        
        OntModel mode = null;
        mode = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
        java.io.InputStream in = FileManager.get().open( "C:/Users/Rhyzard/Documents/NetBeansProjects/OWLProyectoAGV/ProyectoFinal.owl" );
        //C:/Users/Rhyzard/Documents/NetBeansProjects/ProyectoFinalTopicos
        //C:/Users/Rhyzard/Documents/NetBeansProjects/OWLProyectoAGV/ProyectoFinal.owl
        if (in == null) {
            throw new IllegalArgumentException("Archivo de ontología no encontrado");
        }
            return  (OntModel) mode.read(in, "");
    }
     // Conectar con el archivo OWL y devolver el ResultSet de Jena
     static  com.hp.hpl.jena.query.ResultSet ExecSparQl(String Query){
         
          com.hp.hpl.jena.query.Query query = QueryFactory.create(Query);
                QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
                com.hp.hpl.jena.query.ResultSet results = qe.execSelect();
           //    System.out.println("test " + ResultSetFormatter.asText(results, (Prologue) qe));
                //();
                
                return results;
         
     }
     
     // Conectado al archivo OWL y devuelve la cadena
      static  String GetResultAsString(String Query){
        try {
            com.hp.hpl.jena.query.Query query = QueryFactory.create(Query);
                  QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
                  com.hp.hpl.jena.query.ResultSet results = qe.execSelect();
                  if(results.hasNext()){
                     ByteArrayOutputStream go = new ByteArrayOutputStream ();
                   ResultSetFormatter.out((OutputStream)go ,results, query);
                  //  String s = go.toString();
                   // devolver los resultados de la búsqueda (String ) ;)
                       s = new String(go.toByteArray(), "UTF-8");
                   }
                  else{
                      // si no se encuentra nada => para la prueba
                      s = "nada";
                  }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OpenOWL.class.getName()).log(Level.SEVERE, null, ex);
        }
         return s;
      }
    
}
//End