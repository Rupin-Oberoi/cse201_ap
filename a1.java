import java.util.*;
public class a1 {
    public static Scanner scan= new Scanner(System.in);
    public static ArrayList<Citizen> citlist = new ArrayList<Citizen>(0);
    public static ArrayList<Hospital> hoslist = new ArrayList<Hospital>(0);
    public static ArrayList<Vaccine> vaxlist = new ArrayList<Vaccine>(0);
    public static ArrayList<Slot> slotlist = new ArrayList<Slot>(0);
    public static void main(String[] args) {
        int main_selection=0;
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a hospital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");       

        
        while (main_selection!=8){

            main_selection=scan.nextInt();
            if (main_selection==3){
                System.out.println("Enter name:");
                String mis = scan.nextLine();
                String name = scan.nextLine();
                System.out.println("Enter age:");
                int age = scan.nextInt();
                if (age<18){
                    System.out.println("You are too young to be eligible");
                }
                else{mis = scan.nextLine();
                System.out.println("Enter unique ID:");
                long ID = scan.nextLong();
                citlist.add(new Citizen(name, ID, age));
                System.out.println("Registered citizen: "+"Name: "+name+" Age "+ age+" UID "+ID);}
            }
            else if (main_selection==2){
                String mis = scan.nextLine(); 
                System.out.println("Enter hospital name:");
                String name = scan.nextLine();
                System.out.println("Enter pincode:");
                int pincode = scan.nextInt();
                int hosp_id = 100000;
                hosp_id+=hoslist.size();
                hoslist.add(new Hospital(name, pincode, hosp_id));
                System.out.println("Hospital added: "+ "Name: " + name + " Pincode: " + pincode + " Hospital ID "+ hosp_id);
            }
            else if (main_selection==1){
                String vax_name;
                String mis = scan.nextLine();
                System.out.println("Enter vaccine name:");
                vax_name = scan.nextLine();
                System.out.println("Enter number of doses:");
                int n_doses = scan.nextInt();
                mis = scan.nextLine();
                int gap_doses = 0;
                if (n_doses>1){
                    System.out.println("Enter gap between doses");
                    gap_doses = scan.nextInt();
                }
                vaxlist.add(new Vaccine(vax_name, n_doses, gap_doses));
                System.out.println("Vaccine added: "+ "Name: " + vax_name + " Number of doses: " + n_doses + " Gap between doses: "+ gap_doses);
            }
            else if (main_selection==4){
                String misc = scan.nextLine();
                create_slot();
            }
            else if (main_selection==7){
                System.out.println("Enter patient ID:");
                long person_id;
                String misc3;
                misc3 = scan.nextLine();
                person_id = scan.nextLong();
                Citizen c1=null;
                for (int k=0; k<citlist.size(); k++){
                    if (citlist.get(k).getID()==person_id){
                        c1 = citlist.get(k);
                        break;
                    }
                }
                System.out.println(c1.getStatus());
                System.out.println("Vaccine given: "+ c1.getVaxUsed());
                System.out.println("Number of doses given:"+0);
                if (c1.getStatus()=="PARTIALLY VACCINATED"){
                    System.out.println("Next Due Date: "+ c1.getDueDate());
                }

            }
            else if (main_selection==6){
                System.out.println("Enter hospital ID");
                String misc4=scan.nextLine();
                int h_id = scan.nextInt();
                Hospital h1=null;
                for (int k=0; k<hoslist.size();k++){
                    if (hoslist.get(k).getID()==h_id){
                    h1=hoslist.get(k);
                    break;
                    }
                }
                ArrayList<Slot> al2 = h1.getSlist();
                for (int p=0;p<h1.getSlist().size();p++){
                    System.out.println("Day:"+al2.get(p).getDay()+" Vaccine:"+al2.get(p).getVName()+" Available Quantity:"+al2.get(p).getQty());
                }
            }
            else if (main_selection==5){
                System.out.println("Enter unique ID");
                String misc5 = scan.nextLine();
                Long p_id = scan.nextLong();
                System.out.println("1. Search by area");
                System.out.println("2. Search by vaccine");
                System.out.println("3. Exit");
                int search_sel = scan.nextInt();
                if (search_sel==1){
                    search_pin();
                }
                else if (search_sel==2){
                    search_vax();
                }
            }
        }
    
    }
    static public void search_pin(){
        System.out.print("Enter pincode: ");
        int pincode = a1.scan.nextInt();
        for (int i =0; i<a1.hoslist.size();i++){
            if (a1.hoslist.get(i).getPin()==pincode){
                System.out.println(i +" "+a1.hoslist.get(i).getName());
            }
        }
        String mis10 = scan.nextLine();
        int hospital_id = scan.nextInt();
        int slot_len = hoslist.get(hospital_id).getSlist().size();
        for (int k=0;k<slot_len;k++){
            System.out.println(k+"->Day:"+hoslist.get(hospital_id).getSlist().get(k).getDay()+" Quantity:"+hoslist.get(hospital_id).getSlist().get(k).getQty()+" Vaccine Name: "+ hoslist.get(hospital_id).getSlist().get(k).getVName());
        }
        mis10 = scan.nextLine();
        int vaccine_selected = scan.nextInt();
        
    }
    public static void search_vax(){
        System.out.println("Enter vaccine name");
        String misc6 = scan.nextLine();
        String v_name = scan.nextLine();
        for (int q=0;q<hoslist.size();q++){
            for (int r = 0; r<hoslist.get(q).getSlist().size();r++){
                if (hoslist.get(q).getSlist().get(r).getVName()==v_name){
                    System.out.println(q+hoslist.get(q).getName());
                }
            }
        }
    }
    static public void create_slot(){
        System.out.println("Enter hospital ID:");
        int hosp_id;
        hosp_id = scan.nextInt();
        Hospital h1=null;
        for (int k=0; k<hoslist.size();k++){
            if (hoslist.get(k).getID()==hosp_id){
                h1=hoslist.get(k);
                break;
            }
        }
        ArrayList<Slot> al1 = new ArrayList<Slot>(0);
        al1 = h1.getSlist();
        System.out.println("Enter number of slots: ");
        String misc1 = scan.nextLine();
        int n_slots = scan.nextInt();
        String misc2;
        for (int i=0; i<n_slots; i++){
            System.out.println("Enter day number");
            misc2 =scan.nextLine();
            int day_num = scan.nextInt();
            misc2 = scan.nextLine();
            System.out.println("Enter quantity");
            int quant = scan.nextInt();
            for (int j=0; j<vaxlist.size(); j++){
                System.out.println(j+" " + vaxlist.get(j).getName());}
            int vax_sel = scan.nextInt();
            a1.slotlist.add(new Slot(hosp_id, quant, day_num, a1.vaxlist.get(vax_sel).getName()));
            System.out.println("Slot added by Hospital "+ hosp_id+" for Day:"+day_num+", Available Quantity:" + quant + " of Vaccine " + a1.vaxlist.get(vax_sel).getName());
            al1.add(new Slot(hosp_id, quant, day_num, a1.vaxlist.get(vax_sel).getName()));
            h1.setSlist(al1);
        }

    }
}
class Vaccine{
    //Attributes
    private ArrayList<Vaccine> vlist;
    private String name;
    private int n_doses;
    private int gap;
    //Constructor
    public Vaccine(String n, int dos, int gp){
        name=n;
        n_doses=dos;
        gap=gp;
    }
    public void display_details() {
        System.out.println("Vaccine name"+name);
        System.out.println("number of doses"+n_doses);
        System.out.println("gap between doses"+gap);
    }
    public String getName(){
        return this.name;
    }
}
class Citizen{
    Scanner scan = new Scanner(System.in);
    //Attributes
    private long id;
    private String name;
    private int age;
    private String status;
    private int prev_date;
    private int due_date;
    private String vax_used;

    public Citizen(String n, long i, int a){
        name=n;
        id=i;
        age=a;
        status="REGISTERED";
        if (age<18){
            status="LOW AGE";
        }
    }
    public long getID() {
        return this.id;
    }
    public String getStatus(){
        return this.status;
    }
    public int getLastDate(){
        return this.prev_date;
    }
    public int getDueDate(){
        return this.due_date;
    }
    public String getVaxUsed(){
        return this.vax_used;
    }
    public void display_details_registration() {
        System.out.println("Name: "+name);
        System.out.println("Age:" +age);
        System.out.println("Unique ID: "+id);
        if (age<18){
            System.out.println("Only above 18 are allowed");
        }
    }
    // public void book_slot() {
    //     System.out.println("Enter ID:");
    //     long entered_id = scan.nextLong();
    //     System.out.println("1. Search by area");
    //     System.out.println("2. Search by vaccine");
    //     System.out.println("3. Exit");
    //     System.out.println("Enter option");
    //     int selection = scan.nextInt();
    //     if (selection==1){
    //         System.out.println("Enter pin code");
    //         int pin=scan.nextInt();
    //     }
    // }
    public void change_status(String new_status){
        status = new_status;
    }
    public void check_status(){
        long id = a1.scan.nextLong();
        Citizen a=null;
        for (int i=0; i<a1.citlist.size();i++){
            if (a1.citlist.get(i).getID()==id){
                a = a1.citlist.get(i);
            }
        }
        System.out.println(a.getStatus());
        if (a.getStatus()=="PARTIALLY VACCINATED" || a.getStatus()=="FULLY VACCINATED"){
            System.out.println(a.getLastDate());
        }
        if (a.getStatus()=="PARTIALLY VACCINATED"){
            System.out.println(a.getDueDate());
        }
    }

    
}
class Hospital{
    private String name;
    private int pincode;
    private int hosp_id;
    private Slot slot;
    private ArrayList<Slot> slots_for_hosp = new ArrayList<Slot>();

    public Hospital(String n, int p, int i) {
        name = n;
        pincode=p;
        hosp_id=i;
    }
    public String getName(){
        return this.name;
    }
    public int getPin(){
        return this.pincode;
    }
    public int getID(){
        return this.hosp_id;
    }
    public void search_pin(){
        int pincode = a1.scan.nextInt();
        for (int i =0; i<a1.hoslist.size();i++){
            if (a1.hoslist.get(i).getPin()==pincode){
                System.out.println(i +a1.hoslist.get(i).getName());
            }
        }
    }
    public ArrayList<Slot> getSlist(){
        return this.slots_for_hosp;
    }
    public void setSlist (ArrayList<Slot> new_list){
        this.slots_for_hosp = new_list;
    }
    public void search_vax(){
        for (int i=0; i<a1.vaxlist.size(); i++){
            System.out.println(i + a1.vaxlist.get(i).getName());
        }
        int vax_sel = a1.scan.nextInt();
        String sel_vax_name = a1.vaxlist.get(vax_sel).getName();

    }
    public void add_slot(Slot a){
        this.slots_for_hosp.add(a);
    }

}

class Slot{
    //Scanner scan = new Scanner(System.in);
    private int hosp_id;
    private int quant;
    private int day_num;
    private String vax_name;
    public Slot (int id, int q, int num, String name){
        hosp_id = id;
        quant = q;
        day_num = num;
        vax_name = name;
    }
    public void create_slot(){
        int hosp_id;
        hosp_id = a1.scan.nextInt();
        int n_slots = a1.scan.nextInt();
        for (int i=0; i<n_slots; i++){
            int day_num = a1.scan.nextInt();
            int quant = a1.scan.nextInt();
            for (int j =0; j<a1.vaxlist.size(); j++){
                System.out.println(j + a1.vaxlist.get(j).getName());
            int vax_sel = a1.scan.nextInt();
            a1.slotlist.add(new Slot(hosp_id, quant, day_num, a1.vaxlist.get(vax_sel).getName()));

            }
        }
    }
    public int getDay(){
        return this.day_num;
    }
    public String getVName(){
        return this.vax_name;
    }
    public int getQty(){
        return this.quant;
    }
    
}
