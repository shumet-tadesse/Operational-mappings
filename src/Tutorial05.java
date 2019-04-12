/**
 * 
 */
/**
 * @author Daria
 *
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;





/** Tutorial 5 - read RDF XML from a file and write it to standard out
 */
public class Tutorial05 extends Object {

    /**
        NOTE that the file is loaded from the class-path and so requires that
        the data-directory, as well as the directory containing the compiled
        class, must be added to the class-path when running this and
        subsequent examples.
     * @throws FileNotFoundException 
    */    
    //static final String inputFileName  = "C:\\Users\\Daria\\Desktop\\Sources\\emp.owl";
                              
    public static void main (String args[]) throws FileNotFoundException {
        // create an empty model
       /* Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read(in, "");
                    
        // write it to standard out
        model.write(System.out);   
        */
       /* File f;

        FileReader fr;

        Model model;



        f = new File("C:\\Users\\Daria\\Desktop\\Sources\\emp.owl");

        fr = new FileReader(f);

        model = ModelFactory.createDefaultModel();

        model.read(fr, RDFS.getURI());
        
       // Model model;

        StmtIterator iter;

        Statement stmt = null;

     
        iter = model.listStatements();

        while (iter.hasNext())

            {

            stmt = iter.next();

            // Now use <stmt>

            }
        Property predicate;

        Resource subject;

        RDFNode obj;

        //Statement stmt;
        subject = stmt.getSubject();

        System.out.println("Subject = " + subject.getURI());

        predicate = stmt.getPredicate();

        System.out.println("Predicate = " +predicate.getLocalName());

        obj = stmt.getObject();

        System.out.println("Object = " + obj.toString());
        */
    	Model model = ModelFactory.createDefaultModel();
        
        try {
            model.read(new FileInputStream("C:\\Users\\Daria\\Desktop\\Sources\\emp.owl"), null, "TURTLE");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
        List<String> concepts = new ArrayList<>();
        List<String> attributes= new ArrayList<>();
        
        List<String> subconcepts = new ArrayList<>();
        List<String> subattributes= new ArrayList<>();
     // create map to store
        Map<String, String> map = new HashMap<String, String>();
       // MultiMap<String,String> map2 = new MultiMap<>();
        
        Map<String, List<String>> map2 = new HashMap<>(); 
        
        Table<String, String, String> relHandles = HashBasedTable.create();
        
        StmtIterator iter;
        Statement stmt;
        
        Property predicate;

        Resource subject;

        RDFNode obj;

        iter = model.listStatements();
        ArrayList<String> list;
        while (iter.hasNext())

            {

            stmt =  iter.next();

            // Now use <stmt>
            subject = stmt.getSubject();

            //System.out.println("Subject = " + subject.getURI());

            predicate = stmt.getPredicate();

           // System.out.println("Predicate = " +predicate.getLocalName());

            obj = stmt.getObject();

            //System.out.println("Object = " + obj.toString());
            //if(predicate.getLocalName().equals("type"))
           
            	//System.out.println("Subject = " + subject.getURI());
            
            //print list of classes
            //System.out.println("List of Classes\n");
            int idStr = subject.getURI().lastIndexOf('/');
            
            if(obj.toString().endsWith("Class"))
            {
            	
            	//System.out.println("Subject = " + subject.getURI().substring(idStr+1));
            	concepts.add(subject.getURI().substring(idStr+1));
            }
            
            //System.out.println("List of Properties\n");
            if(obj.toString().endsWith("Property"))
            {
            	//System.out.println("Property = " + subject.getURI().substring(idStr+1));
            	attributes.add(subject.getURI().substring(idStr+1));
            }
            
            //if(obj.toString().endsWith("Property"))
            //{
           
            if(predicate.getLocalName().equals("domain"))
            	{
            	String[] parts = obj.toString().split("/");
                
            	//System.out.println("Property = " + subject.getURI().substring(idStr+1)+ " domain "+ parts[parts.length-1]);
            	//relHandles.put((parts[parts.length-1]),"hasAttribute", (subject.getURI().substring(idStr+1)));
            	//map.put((parts[parts.length-1]),(subject.getURI().substring(idStr+1)));
            	//System.out.println("maping..."+(parts[parts.length-1]+" and "+ (subject.getURI().substring(idStr+1))));	
            	//subattributes.add(subject.getURI().substring(idStr+1));
            	//map2.put((parts[parts.length-1]), subattributes);
            	
            	
            	if(map2.containsKey((parts[parts.length-1]))){
            	    // if the key has already been used,
            	    // we'll just grab the array list and add the value to it
            	    list = (ArrayList<String>) map2.get(parts[parts.length-1]);
            	    list.add(subject.getURI().substring(idStr+1));
            	} else {
            	    // if the key hasn't been used yet,
            	    // we'll create a new ArrayList<String> object, add the value
            	    // and put it in the array list with the new key
            	    list = new ArrayList<String>();
            	    list.add(subject.getURI().substring(idStr+1));
            	    map2.put(parts[parts.length-1], list);
            	}
            	
            	}
            //}
            
            }
        
        
       // for (Map.Entry<String, String> entry : map.entrySet()) {
         //   String key = entry.getKey();
         //   String values = entry.getValue();
         //   System.out.println("Key = " + key);
          //  System.out.println("Values = " + values + "\n");
       // }
        
        Set<String> keys = map2.keySet();
        for (String key : keys) {
            System.out.println("Key = " + key+" Values = " + map2.get(key));
           // System.out.println("Values = " + map2.get(key));
       // System.out.println("created atoms ");
       // System.out.println(concepts);
       // System.out.println(attributes);
       // System.out.println("Relationships");
       // System.out.println(relHandles);

            }
/*Model model = ModelFactory.createDefaultModel();
        
        try {
            model.read(new FileInputStream("C:\\Users\\Daria\\Desktop\\Sources\\emp.owl"), null, "TURTLE");
        } catch (Exception e) {
            // TODO: handle exception
        }
        StmtIterator iter;
        Statement stmt;
        
        Property predicate;

        Resource subject;

        RDFNode obj;

        iter = model.listStatements();
        
        
        while (iter.hasNext())

            {

            stmt = (Statement) iter.next();

            // Now use <stmt>
            subject = ((org.apache.jena.rdf.model.Statement) stmt).getSubject();

            System.out.println("Subject = " + subject.getURI());

            predicate = ((org.apache.jena.rdf.model.Statement) stmt).getPredicate();

           System.out.println("Predicate = " +predicate.getLocalName());

            obj = ((org.apache.jena.rdf.model.Statement) stmt).getObject();

            //System.out.println("Object = " + obj.toString());
            //if(predicate.getLocalName().equals("type"))
           
            	//System.out.println("Subject = " + subject.getURI());
            
            //print list of classes
            //System.out.println("List of Classes\n");
            int idStr = subject.getURI().lastIndexOf('/');
            String sub=subject.getURI().substring(idStr+1);
            Atom subAtm= new Atom(sub);
             
            if(obj.toString().endsWith("Class"))
            {
             atoms.add(subAtm);
            atomHandles.add(graph.add(subAtm));
            }
    		 //System.out.println("Subject = " + subject.getURI().substring(idStr+1));
            
            //System.out.println("List of Properties\n");
            if(obj.toString().endsWith("Property"))
            {
            	
        		Atom pos = new Atom(sub, String.class.getName());
        		atoms.add(pos);
        		atomHandles.add(graph.add(pos));
        		
        		
        		try {
        			logger.info("Creating Relationships");
        			
        			// Stations
        			relHandles.put(subAtm, pos, graph.add(new Relationship("hasName",
        					atomHandles.get(atoms.indexOf(subAtm)), atomHandles.get(atoms.indexOf(pos)))));
                
        			// -----Document Store-------//
        			HGHandle stationstruct = graph.add(new Hyperedge("", HyperedgeTypeEnum.Struct,
        					atomHandles.get(atoms.indexOf(pos)), atomHandles.get(atoms.indexOf(subAtm)), relHandles.get(subAtm, pos)));
        			
        			HGHandle docSecondLevel = graph.add(
        					new Hyperedge("metros-trams", HyperedgeTypeEnum.SecondLevel, atomHandles.get(atoms.indexOf(subAtm)), // atomHandles.get(atoms.indexOf(tname)),
        						relHandles.get(subAtm, pos)));
        			
        			HGHandle docFirstLevel = graph
        					.add(new Hyperedge("metros-trams", HyperedgeTypeEnum.FirstLevel, docSecondLevel));
        			graph.add(new Hyperedge("MongoDB", HyperedgeTypeEnum.Database_Doc, docFirstLevel));
        			graph.close();
        		
        		
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        			// logger.error(e.toString());
        		}

        	}
            }
	}*/
       
       // model.write(System.out);
    }
    
}
