


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class avltree {



    public static void main(String[] args) {
        Avl_Tree tree=new Avl_Tree();
        String fileName=args[0];

        try {
            //make files
            File myObj = new File(fileName);
            FileWriter outputFile=new FileWriter("output_file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String command = myReader.nextLine();

                //check commands
                if (command=="Initialize()"){
                    tree=new Avl_Tree();
                }
                else if (command.substring(0,7).equals("Insert(")){
                    String val=command.substring(7,command.length()-1);
                    tree.insert(Integer.valueOf(val));
                }
                else if (command.substring(0,7).equals("Delete(")){
                    String val=command.substring(7,command.length()-1);
                    tree.delete(Integer.valueOf(val));
                }
                else if (command.substring(0,7).equals("Search(")){
                    //search for a value or range
                    String[] val=command.substring(7,command.length()-1).split(",");
                    List<Integer> result=new ArrayList<>();
                    if (val.length==1){
                        result.add(tree.search(Integer.valueOf(val[0])));
                    }
                    if (val.length==2){
                        result=tree.search(Integer.valueOf(val[0]),Integer.valueOf(val[1]));
                    }

                    if (result.size()==0){
                        outputFile.append("NULL");
                        if (myReader.hasNextLine())
                            outputFile.append("\n");
                    }
                    else{
                        for (int i=0; i<result.size(); i++){
                            if (result.get(i) == null){
                                outputFile.append("NULL");
                            }
                            else{
                                outputFile.append(String.valueOf(result.get(i)));
                            }

                            if (i!=result.size()-1){
                                outputFile.append(",");
                            }
                        }
                        if (myReader.hasNextLine()){
                            outputFile.append("\n");
                        }
                           
                    }

                }

            }
            //close files
            outputFile.close();
            myReader.close();
        } 
        //catch errors
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e){
            System.out.println("File not created.");
        }





    }
}
