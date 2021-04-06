/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassPackage;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rhyzard
 */
public class TableContent {
    public void ConstructionTable(ResultSet re,String d1,String d2,DefaultTableModel aModel, JTable DataTable ){
        while(re.hasNext()){
                QuerySolution sol = re.nextSolution();
                RDFNode data1 = sol.get(d1); 
                RDFNode data2 = sol.get(d2); 	
       		aModel.addRow(new Object[]{ data1,data2});
                DataTable.setModel(aModel);
            }
    }
}
