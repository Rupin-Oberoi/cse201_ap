import java.util.*;
public class a3 {
    public static Random rand = new Random();
    public static Scanner scan = new Scanner(System.in);
    public static ArrayList<Floor> flist = new ArrayList<>();
    public static ArrayList<Integer> hs_list = new ArrayList<>();
    public static void main(String[] args) {
        Game g1 = new Game();
        Floor f0 = new EmptyFloor(0);
        flist.add(f0);
        Floor f1 = new EmptyFloor(1);
        flist.add(f1);
        Floor f2 = new EleFloor(2);
        flist.add(f2);
        Floor f3 = new EmptyFloor(3);
        flist.add(f3);
        Floor f4 = new EmptyFloor(4);
        flist.add(f4);
        Floor f5 = new SnakeFloor(5);
        flist.add(f5);
        Floor f6 = new EmptyFloor(6);
        flist.add(f6);
        Floor f7 = new EmptyFloor(7);
        flist.add(f7);
        Floor f8 = new LadderFloor(8);
        flist.add(f8);
        Floor f9 = new EmptyFloor(9);
        flist.add(f9);
        Floor f10 = new EmptyFloor(10);
        flist.add(f10);
        Floor f11 = new CobraFloor(11);
        flist.add(f11);
        Floor f12 = new EmptyFloor(12);
        flist.add(f12);
        Floor f13 = new EmptyFloor(13);
        flist.add(f13);
        int main_sel = 1;
        while (main_sel==1){
        g1.setFloor(-1);
        g1.setPts(0);
        System.out.println("Enter the player name and hit enter");
        String name = scan.nextLine();
        System.out.println("The game setup is ready");
        System.out.print("Hit enter to roll dice");
        String misc = scan.nextLine();
        int res = g1.getDice().rollDice();
        while (res==2){
            System.out.println("Dice gave 2");
            System.out.println("Game cannot start until you get 1");
            System.out.print("Hit enter to roll the dice");
            misc = scan.nextLine();
            res = g1.getDice().rollDice();
        }
        int cat = 0;
        while (g1.getFloor()<13 && cat<10){
            if (g1.getFloor()!=12){
            System.out.println("Dice gave "+res);
            g1.setFloor(g1.getFloor()+res);
            
            System.out.println("Player position Floor "+g1.getFloor());
            System.out.println(name+" has reached a/an "+flist.get(g1.getFloor()).getFloorType()+" floor");
            flist.get(g1.getFloor()).changePts(g1);
            System.out.println("Total points: "+g1.getPts());
            if (flist.get(g1.getFloor()).getFloorType()!="Empty"){
                flist.get(g1.getFloor()).Elevate(g1);
                System.out.println("Player position Floor "+g1.getFloor());
                System.out.println(name+" has reached an Empty floor");
                g1.setPts(g1.getPts()+1);
                System.out.println("Total points: "+g1.getPts());
            }
            System.out.println("Hit enter to roll dice");
            misc = scan.nextLine();
            res = g1.getDice().rollDice();}
            else if (g1.getFloor()==12 && res == 2){
                System.out.println("Dice gave 2");
                System.out.println("Player cannot move");
                System.out.println("Hit enter to roll dice");
                misc = scan.nextLine();
                res = g1.getDice().rollDice();
            }
            else if (g1.getFloor()==12 && res==1){
                g1.setFloor(13);
            }
        }
        if (g1.getFloor()==13){
            System.out.println("Dice gave 1");
            System.out.println("Player position Floor 13");
            System.out.println(name+" has reached an Empty Floor");
            g1.setPts(g1.getPts()+1);
            System.out.println("Total points: "+g1.getPts());
            System.out.println("Game over");
            System.out.println(name+" accumulated "+g1.getPts()+" points.");
            hs_list.add(g1.getPts());
            System.out.println("Press 1 to play again");    // added for bonus
            System.out.println("Press 2 to view high scores");  // added for bonus
            System.out.println("Press 3 to exit");
            main_sel = scan.nextInt();
            misc = scan.nextLine();
            if (main_sel==2){
                main_sel=3;
                Collections.sort(hs_list, Collections.reverseOrder());
                for (int i=0; i<hs_list.size();i++){
                    System.out.println((i+1)+". "+hs_list.get(i));
                }
            }
        }
    }}
    
}
class Dice{
    public Dice(){}
    public int rollDice(){
        return a3.rand.nextInt(2)+1;
    }
}
class Game{
    private Dice d1 = new Dice();
    private int curr_level=-1;
    private int points=0;
    public Game(){};
    public Dice getDice(){
        return this.d1;
    }
    public int getFloor(){
        return this.curr_level;
    }
    public int getPts(){
        return this.points;
    }
    public void setFloor(int a){
        this.curr_level = a;
    }
    public void setPts(int p){
        this.points = p;
    }
}
class Floor{
    private int lvl;
    
    public Floor(int l){
        this.lvl = l;
    }
    public int getFloor(){
        return this.lvl;
    }
    public void setFloor(int l){
        this.lvl = l;
    }
    public void changeFloor(int l){
        this.lvl+=l;
    }
    public void setPoints(Game g, int p){

    }
    public void Elevate(Game g){
        
    }
    public String getFloorType(){
        if (this.lvl==0 || this.lvl==1 || this.lvl==3 || this.lvl==4 || this.lvl==6 || this.lvl==7 ||this.lvl==9 ||this.lvl==10 ||this.lvl==12)
            return "Empty";
        else if (this.lvl==2)
            return "Elevator";
        else if (this.lvl==5)
            return "Snake";
        else if (this.lvl==8)
            return "Ladder";
        else if (this.lvl==11)
            return "Cobra";
        else if (this.lvl==13)
            return "Finish";
        else
            return "NA";
    }
    public void changePts(Game g){}
}
class EmptyFloor extends Floor{
    public EmptyFloor(int l){
        super(l);
    }
    public void changePts(Game g){
        g.setPts(g.getPts()+1);
    }
}
class EleFloor extends Floor{
    public EleFloor(int l){
        super(l);
    }
    public void Elevate(Game g){
        g.setFloor(g.getFloor()+8);
    }
    public void changePts(Game g){
        g.setPts(g.getPts()+4);
    }
}
class SnakeFloor extends Floor{
    public SnakeFloor(int l){
        super(l);
    }
    public void Elevate(Game g){
        g.setFloor(g.getFloor()-4);
    }
    public void changePts(Game g){
        g.setPts(g.getPts()-2);
    }
}
class CobraFloor extends Floor{
    public CobraFloor(int l){
        super(l);
    }
    public void Elevate(Game g){
        g.setFloor(g.getFloor()-8);
    }
    public void changePts(Game g){
        g.setPts(g.getPts()-4);
    }
}
class LadderFloor extends Floor{
    public LadderFloor(int l){
        super(l);
    }
    public void Elevate(Game g){
        g.setFloor(g.getFloor()+4);
    }
    public void changePts(Game g){
        g.setPts(g.getPts()+2);
    }
}