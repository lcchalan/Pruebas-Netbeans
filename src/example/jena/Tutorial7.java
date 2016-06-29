/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.jena;


import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;
import org.apache.jena.sparql.vocabulary.FOAF;
/**
 *
 * @author luischalan
 */
public class Tutorial7 extends Object {
    
    static final String inputFileName = "/Users/luischalan/Desktop/people.rdf";
    
    public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
       
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read( in, "");
        
        // select all the resources with a VCARD.FN property
        
        StmtIterator iter = model.listStatements((Selector) RDF.type);
        if (iter.hasNext()) {
            //System.out.println("The database contains vcards for:");
            
            while (iter.hasNext()) {
                /*System.out.println("  " + iter.nextResource()
                                              .getRequiredProperty(RDFS.label)
                                              .getString() );*/
                ResIterator iter2 = model.listResourcesWithProperty(RDFS.label);
                while (iter2.hasNext()) {
                System.out.println("  " + iter2.nextResource()
                                              .getRequiredProperty(RDFS.label)
                                              .getString() );
                }
            }
        } else {
            System.out.println("No vcards were found in the database");
        }            
    }
}
