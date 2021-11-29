import java.util.*;
public class a4 {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) { 
        System.out.println("Hit enter to initialise the game");
        String misc = scan.nextLine();
        Game g1 = new Game();
        g1.main();
    }
}
class Game{
    private static int curr_hop = 0;
    private Carpet my_carpet;
    private Player my_player;
    public Game(){
    Carpet crpt = new Carpet();
    Player plyr = new Player(this);
    this.my_carpet = crpt;
    this.my_player = plyr;
        Toy a = new Toy("A");
        Toy b = new Toy("B");
        Toy c = new Toy("C");
        Toy d = new Toy("D");
        Toy e = new Toy("E");
        Toy f = new Toy("F");
        Toy g = new Toy("G");
        Toy h = new Toy("H");
        Toy i = new Toy("I");
        Toy j = new Toy("J");
        Toy k = new Toy("K");
        Toy l = new Toy("L");
        Toy m = new Toy("M");
        Toy n = new Toy("N");
        Toy o = new Toy("O");
        Toy p = new Toy("P");
        Toy q = new Toy("Q");
        Toy r = new Toy("R");
        Toy s = new Toy("S");
        Toy t = new Toy("T");
        my_carpet.addToy(a);
        my_carpet.addToy(b);
        my_carpet.addToy(c);
        my_carpet.addToy(d);
        my_carpet.addToy(e);
        my_carpet.addToy(f);
        my_carpet.addToy(g);
        my_carpet.addToy(h);
        my_carpet.addToy(i);
        my_carpet.addToy(j);
        my_carpet.addToy(k);
        my_carpet.addToy(l);
        my_carpet.addToy(m);
        my_carpet.addToy(n);
        my_carpet.addToy(o);
        my_carpet.addToy(p);
        my_carpet.addToy(q);
        my_carpet.addToy(r);
        my_carpet.addToy(s);
        my_carpet.addToy(t);
    }
    private static Random rand = new Random();
    private static Calculator<Integer> cal = new Calculator<Integer>();
    public static Calculator<String> cal1 = new Calculator<String>();
    public void main() {
        System.out.println("The game is ready");
        while (curr_hop<5){
            System.out.println("Hit enter to play your hop number "+(curr_hop+1));
            String misc = a4.scan.nextLine();
            int reach =0;
            reach = rand.nextInt(25)+1;  //can jump from 1 to 25
            if (reach%2==1 && reach<21){
                boolean res = false;
                boolean choice=false;
                String chosen="";
                while(!choice){
                    System.out.println("You landed on tile " + reach);
                    System.out.println("Question answer round. Integer or strings?");
                    chosen = a4.scan.nextLine();
                    if (chosen.toLowerCase().equals("integer") || (chosen.toLowerCase().equals("string"))){
                    choice = true;
                    }
                    else{
                    System.out.println("Invalid choice. Try again");
                    }
                }
                if (chosen.toLowerCase().equals("integer")){
                    int[] arr = rand.ints(2, Integer.MIN_VALUE, Integer.MAX_VALUE).toArray();
                    int a = arr[0], b = arr[1];
                    cal.setOpr1(a);
                    cal.setOpr2(b);
                    System.out.println("Calculate the result of "+ arr[0] + " divided by "+ arr[1]);
                    res = cal.check_int();
                }
                else if (chosen.toLowerCase().equals("string")){
                    int len1 = rand.nextInt(3) + 3;
                    int len2 = rand.nextInt(3) + 3;
                    String s1 = "";
                    String s2="";
                    String sample = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    for (int i = 0; i<len1; i++){
                        int random = rand.nextInt(52);
                        s1+=sample.charAt(random);
                    }
                    for (int i =0; i<len2; i++){
                        int random = rand.nextInt(52);
                        s2+=sample.charAt(random);
                    }
                    cal1.setOpr1(s1);
                    cal1.setOpr2(s2);
                    res = cal1.check_str();
                }
            if (res==true){
                System.out.println("Correct answer!");
                System.out.println("You have won a " + my_carpet.getList().get(reach-1).getName()+" soft toy");
                Toy toGive = my_carpet.getList().get(reach-1).clone();
                my_player.getBucket().addToy(toGive);

            }
            else if (res==false){
                System.out.println("Wrong answer!");
            }}
            else if (reach%2==0 && reach<21) {
                System.out.println("You landed on tile "+reach);
                System.out.println("Congratulations! You win toy "+ my_carpet.getList().get(reach-1).getName());
                Toy toGive = my_carpet.getList().get(reach-1).clone();
                my_player.getBucket().addToy(toGive);
            }
            else {
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
            }
        curr_hop++;   
        }
        System.out.println("\n   GAME OVER");
        System.out.println("The toys won by you are: ");
        for (int i=0; i<my_player.getBucket().getList().size(); i++){
            System.out.print(my_player.getBucket().getList().get(i).getName()+" ");
        }
    }
}
class Calculator<T>{
    private T opr1;
    private T opr2;
    public void setOpr1(T a){
        this.opr1 = a;
    }
    public void setOpr2(T b){
        this.opr2 = b;
    }
    public boolean check_int(){
        int a = (int)this.opr1;
        int b = (int)this.opr2;
        int correct = a/b;
        boolean valid_ans = false;
        while (valid_ans==false){
        try{
            int ans = a4.scan.nextInt();
            String misc = a4.scan.nextLine();
            valid_ans = true;
            if (ans==correct){
                return true;
            }
            else{return false;}
        }
        catch (InputMismatchException in){
            System.out.println("Invalid answer. Try again");
            String misc = a4.scan.nextLine();
        }
    }
        return false;
    }
    public boolean check_str(){
        System.out.println("Calculate the concatenation of strings "+ opr1 +" and "+opr2);
        String correct_str = ((String)opr1).concat((String)opr2);
        String ans = a4.scan.nextLine();
        if (ans.equals(correct_str))
            return true;
        return false;
    }
}
class Carpet{
    private ArrayList<Toy> al;
    public Carpet(){
        ArrayList<Toy> lst = new ArrayList<Toy>();
        this.al = lst;
    }
    public void addToy(Toy t){
        this.al.add(t);
    }
    public ArrayList<Toy> getList(){
        return this.al;
    }
}
class Toy implements Cloneable{
    private String name;
    public Toy(String n){
        this.name = n;
    }
    public Toy clone(){
        try{
        Toy copy = (Toy) super.clone();
        copy.name = this.name;
        return copy;
        }
        catch(CloneNotSupportedException ex){
            return null;
        }
    }
    public String getName(){
        return this.name;
    }
}
class Player{
    private Bucket bckt;
    private Game my_game;
    public Player(Game g){
        this.my_game = g;
        Bucket b = new Bucket();
        this.bckt = b;
    }
    public Bucket getBucket(){
        return this.bckt;
    }
}
class Bucket{
    private ArrayList<Toy> bl;
    public Bucket(){
        ArrayList<Toy> tl = new ArrayList<>();
        this.bl = tl; 
    }
    public void addToy(Toy t){
        this.bl.add(t);
    }
    public ArrayList<Toy> getList(){
        return this.bl;
    }
}
