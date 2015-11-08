import java.io.*;

public class PCP
{
    static Element element[];
    static boolean noSolution= true;
    static int ep;
    static String a[],b[];
    
    public static void initiate()
    {
        element = new Element[1500];
        ep=0;        
        
        int size = a.length;
        
        //Add start to queue
        for(int i=0; i<a.length; i++)
        {
            if(a[i].startsWith(b[i])||b[i].startsWith(a[i]))
            {                
                element[ep] = new Element((i+1)+"",size);
                element[ep].setSelected(i);
                if(a[i].length()>b[i].length())
                    element[ep++].aR= a[i].substring(b[i].length(),a[i].length());
                else
                    element[ep++].bR= b[i].substring(a[i].length(),b[i].length());
            }
        }              
    }
    
    public static void main(String[] args)throws IOException
    {
        input();
                
        if(a.length!=b.length)
        {
            System.out.println("No output!");
            return;
        }
        
        initiate();
        System.out.println();
        
        for(int i=0; i<ep ;i++)
        {
            if(allSelected(element[i]))
            {
                System.out.print(element[i].index+"\t->\t");
                getSequence(element[i].index);          
                return;
            }
        }
        
        
        compute();
        if(noSolution)
            System.out.println("No solutions found!");
    }
    
    public static void input()throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter set A");
        String a1 = br.readLine();
        System.out.println("Enter set B");
        String b1 = br.readLine();
        
        a = a1.split(":");
        b = b1.split(":");
    }
    
    public static void compute()
    {
        //Remove from queue and reorder
        for(int j=0; j<1000 && j<ep;j++)
        {                
            Element e = element[j];
            for(int i=0; i<a.length; i++)
            {      
                if((e.aR+a[i]).startsWith(e.bR+b[i])||(e.bR+b[i]).startsWith(e.aR+a[i])) 
                {                                          
                    element[ep] = new Element(e.index+(i+1),e.getSelected());    
                    element[ep].setSelected(i);
                    
                    if((e.aR+a[i]).length()>(e.bR+b[i]).length())
                        element[ep++].aR= (e.aR+a[i]).substring((e.bR+b[i]).length(),(e.aR+a[i]).length());
                    else if((e.aR+a[i]).length()<(e.bR+b[i]).length())
                        element[ep++].bR= (e.bR+b[i]).substring((e.aR+a[i]).length(),(e.bR+b[i]).length());
                    else if(allSelected(element[ep]))
                    {
                        System.out.print(element[ep].index+"\t->\t");
                        getSequence(element[ep].index);
                    }                    
                }
            }
        }
    }
    public static boolean allSelected(Element e)
    {
        int s[] = e.getSelected();
        for(int i =0; i<s.length;i++)
            if(s[i]==0)
                return false;
                
        if(noSolution)
        {
            System.out.println("Valid sequences are: \nSequence\t->\tString");
            noSolution = false;
        }              
        return true;
    }
    public static void getSequence(String in)
    {
        for(int i=0; i<in.length(); i++)
            System.out.print(a[Integer.parseInt(in.charAt(i)+"")-1]);
        System.out.println();
    }
}
