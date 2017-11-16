package lab5.zad4;

import io.indico.Indico;


import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    static public boolean networkConnection() {
        boolean netAccess = false;
        try {
            Socket socket = new Socket("www.google.com", 80);
            netAccess = socket.isConnected();
            socket.close();

        }catch(IOException e) {
                e.printStackTrace();
        }

        return netAccess;
    }


    static public String SortResult(Map<String, Double> data){

        LinkedList <ResultType> list = new LinkedList<ResultType>();
        for(Map.Entry<String, Double> data_it : data.entrySet()){
            list.add(new ResultType(data_it.getValue(), data_it.getKey()));
        }

        Collections.sort(list, new Comparator<ResultType>(){
            @Override
            public int compare(ResultType res1, ResultType res2){
                if(res1.score > res2.score){
                    return -1;
                }else if(res1.score < res2.score){
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        return list.getFirst().recognised_as;
    }



    public static void main(String [] args){


        try{

            Indico indico = new Indico("48471054fd5f336df6dc0e9a9f843d7a");
            String path = "/home/kartytko/Pulpit/foto2/";
            String pattern = "[^\\.]*\\.(jpg|bmp|png|jpeg)";


            Pattern patt = Pattern.compile(pattern);
            Matcher matcher;

            try{
                File dir = new File(path);
                List <File> files = new ArrayList<File>();

                for (final File fileEntry : dir.listFiles()) {

            //        System.out.println(fileEntry.getName());
                    String current_file = fileEntry.getName();

                    matcher = patt.matcher(current_file);

                    if(matcher.find()){
                        current_file=path+current_file+"/";
                        files.add(new File(current_file));
                    }else{
                        throw new NotAnImageException();
                    }
                }



                try{
                    for(File it_files : files) {
                        IndicoResult tmp = indico.imageRecognition.predict(it_files);
                        Map<String, Double> single_result = tmp.getImageRecognition();
                        File directory = new File(path+SortResult(single_result));
                        try {
                            if (!directory.exists()) {
                                directory.mkdir();
                            }
                            it_files.renameTo(new File(path + SortResult(single_result) + "/" + it_files.getName()));
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }catch(NoInternetConnection e){
                    e.printStackTrace();
                }
                catch(IndicoException e){
                    e.printStackTrace();
                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }catch(IndicoException e){
            e.printStackTrace();
        }
    }
}
