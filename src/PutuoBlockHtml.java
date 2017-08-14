import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PutuoBlockHtml {
    public static void main(String[] args){
        try {
            File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\putuoBlockCell.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            PrintStream printStream=new PrintStream(fileOutputStream);
            printStream.append("<table>\n");
            for(int i=9;i>=-1;i--){
                printStream.append("<tr>\n");
                for(int j=0;j<15;j++){
                    StringBuffer stringBuffer=new StringBuffer(0);
                    int y_int=j+65;
                    char y=(char)y_int;
                    int x_int=i+48;
                    char x=(char)x_int;
                    stringBuffer.append(y);
                    stringBuffer.append(x);
                    if(i!=-1){
                        String td_line="<td class=\""+stringBuffer.toString()+"\" id=\""+stringBuffer.toString()+"\"></td>\n";
                        printStream.append(td_line);
                    }
                    else{
                        printStream.append("<td></td>\n");
                    }
                }
                printStream.append("</tr>\n");
            }
            printStream.append("</table>\n");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
