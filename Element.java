public class Element
{
    String index;
    String aR,bR;
    Element link;
    private int selected[];
    
    public Element(String i,int size)
    {
        index =i;
        aR="";
        bR="";
        selected = new int[size];
        for(int j=0; j<size; j++)
            selected[j]=0;
    }
    
    public Element(String i,int s[])
    {
        index =i;
        aR="";
        bR="";
        selected = new int[s.length];
        for(int j=0;j<s.length;j++)
            selected[j] = s[j];
    }
    public void setSelected(int i)
    {
        selected[i]=1;
    }
    public int[] getSelected()
    {
        return selected;
    }
}
