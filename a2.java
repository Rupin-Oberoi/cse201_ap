import java.time.format.*;
import java.time.*;
import java.util.*;
public class a2 {
    public static ArrayList<Video> vidlist = new ArrayList<Video>();
    public static ArrayList<Instructor> instlist = new ArrayList<Instructor>();
    public static ArrayList<Student> stulist = new ArrayList<Student>();
    public static ArrayList<Slide> sldlist = new ArrayList<Slide>();
    public static ArrayList<Assignment> asslist = new ArrayList<Assignment>();
    public static ArrayList<Assesment> assmlist = new ArrayList<Assesment>();
    public static ArrayList<Quiz> quizlist = new ArrayList<Quiz>();
    public static ArrayList<Comment> commlist = new ArrayList<Comment>();
    public static ArrayList<Submission> sublist = new ArrayList<Submission>();
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        instlist.add(new Instructor("I0", 1));
        instlist.add(new Instructor("I1",1));
        stulist.add(new Student("S0"));
        stulist.add(new Student("S1"));
        stulist.add(new Student("S2"));
        int sel1=-1;
        while (sel1!=3){
        System.out.println("Welcome to Backpack");
        System.out.println("1. Enter as instructor");
        System.out.println("2. Enter as student");
        System.out.println("3. Exit");
        sel1 = scan.nextInt();
        @SuppressWarnings("unused")
        String misc = scan.nextLine();
        if (sel1==3){
            break;
        }
        else if (sel1==1){
            int len = instlist.size();
            for (int i = 0; i<len;i++){
                System.out.println(i+" - "+instlist.get(i).getName());
            }
        }
        else if (sel1==2){
            int len = stulist.size();
            for (int i = 0; i<len;i++){
                System.out.println(i+" - "+stulist.get(i).getName());
            }
        }
        Instructor i_user=null;
        Student s_user=null;
        int sel2 = scan.nextInt();
        misc = scan.nextLine();
        if (sel1==1){
            i_user = instlist.get(sel2);
        }
        else if (sel1==2){
            s_user = stulist.get(sel2);
        }
        int sel3 = -1;
        int sel4 = -1;
        int sel5 = -1;
        if (sel1==1){
            while(sel3!=9){
                System.out.println("Welcome "+ i_user.getName());
                System.out.println("Instructor menu");
                sel3 = scan.nextInt();
                if (sel3==1){
                    System.out.println("1. Add lecture slide");
                    System.out.println("2. Add lecture video");
                    sel4 = scan.nextInt();
                    misc = scan.nextLine();
                    if (sel4 ==1){
                        i_user.putSlide();
                    }
                    else if (sel4==2){
                        i_user.putVideo();
                    }
                }
                else if (sel3==2){
                    System.out.println("1. Add Assignment");
                    System.out.println("2. Add quiz");
                    sel4 = scan.nextInt();
                    misc = scan.nextLine();
                    if (sel4==1){
                        i_user.putAssignment();
                    }
                    else if (sel4==2){
                        i_user.putQuiz();
                    }
                }
                else if (sel3==3){
                    for (int i=0;i<vidlist.size();i++){
                        System.out.println("Title: "+vidlist.get(i).getTitle());
                        System.out.println("Video file: "+vidlist.get(i).getFile());
                        System.out.println("Time of upload: "+ dtf.format(vidlist.get(i).getTime()));
                        System.out.println("Uploaded by: "+vidlist.get(i).getUpld().getName());
                        System.out.println(" ");
                    }
                    for (int i =0; i<sldlist.size();i++){
                        System.out.println("Title: "+sldlist.get(i).getTopic());
                        for (int j=0; j<sldlist.get(i).getNsld();j++){
                            System.out.println("Slide "+j+": "+sldlist.get(i).getSlides()[j]);
                        }
                        System.out.println("Time of upload: "+dtf.format(sldlist.get(i).getTime()));
                        System.out.println("Uploaded by: "+sldlist.get(i).getUpldr().getName());
                        System.out.println(" ");
                    }
                }
                else if (sel3==6){
                    for (int i=0; i<assmlist.size();i++){
                        if (assmlist.get(i).getStatus()=="OPEN"){
                            System.out.println(i+" "+assmlist.get(i).getQ());
                        }
                        else
                            continue;
                    }
                    System.out.println("Choose which assesment to close");
                    sel4 = scan.nextInt();
                    misc = scan.nextLine();
                    assmlist.get(sel4).setStatus(1);

                }
                else if (sel3==4){
                    for (int i=0; i<assmlist.size();i++){
                        System.out.println("ID: "+i+" "+assmlist.get(i).getQ()+" Max Marks: "+ assmlist.get(i).getMax()+"\n");
                    }
                }
                else if (sel3==5){
                    System.out.println("List of assesments: ");
                    for (int i =0;i<assmlist.size();i++){
                        if(assmlist.get(i).getType()=="Assignment"){
                            System.out.println("ID: "+i+" Assignemnt: "+ assmlist.get(i).getQ()+" "+"Max Marks "+ assmlist.get(i).getMax());
                        }
                        else if (assmlist.get(i).getType()=="Quiz"){
                            System.out.println("ID: "+i+" Question: "+assmlist.get(i).getQ());
                        }
                    }
                    System.out.print("Enter ID of assesment to view submissions: ");
                    sel4 = scan.nextInt();
                    misc = scan.nextLine();
                    System.out.println("Choose ID from these ungraded submissions");
                    for (int i =0; i<sublist.size();i++){
                        if (sublist.get(i).getAssesment()==assmlist.get(sel4) && sublist.get(i).getStatus()==0){
                            System.out.println("ID "+i+" "+sublist.get(i).getStudent().getName());
                        }
                    }
                    sel5 = scan.nextInt();
                    misc = scan.nextLine();
                    System.out.println("Submission: "+sublist.get(sel5).getAns());
                    System.out.println("Max Marks = "+ assmlist.get(sel4).getMax());
                    System.out.print("Enter marks scored: ");
                    int marks_scored = scan.nextInt();
                    misc = scan.nextLine();
                    sublist.get(sel5).setMarks(marks_scored);
                    sublist.get(sel5).setStatus(1);
                    sublist.get(sel5).setChecker(i_user);
                }
                else if (sel3==7){
                    for (int i=0;i<commlist.size();i++){
                        System.out.println(commlist.get(i).getContent()+" - "+commlist.get(i).getUser().getName());
                        System.out.println(dtf.format(commlist.get(i).getTime()));
                    }
                }
                else if (sel3==8){
                    i_user.putComment();
                }
            }
        }
        else if (sel1==2){
            while(sel3!=7){
                System.out.println("Welcome "+ s_user.getName());
                System.out.println("Student menu");
                sel3 = scan.nextInt();
                misc = scan.nextLine();
                if (sel3==1){
                    for (int i=0;i<vidlist.size();i++){
                        System.out.println("Title: "+vidlist.get(i).getTitle());
                        System.out.println("Video file: "+vidlist.get(i).getFile());
                        System.out.println("Time of upload: "+ dtf.format(vidlist.get(i).getTime()));
                        System.out.println("Uploaded by: "+vidlist.get(i).getUpld().getName());
                        System.out.println(" ");
                    }
                    for (int i =0; i<sldlist.size();i++){
                        System.out.println("Title: "+sldlist.get(i).getTopic());
                        for (int j=0; j<sldlist.get(i).getNsld();j++){
                            System.out.println("Slide "+j+": "+sldlist.get(i).getSlides()[j]);
                        }
                        System.out.println("Time of upload: "+dtf.format(sldlist.get(i).getTime()));
                        System.out.println("Uploaded by: "+sldlist.get(i).getUpldr().getName());
                        System.out.println(" ");
                    }
                }
                else if (sel3==2){
                    for (int i=0; i<assmlist.size();i++){
                        System.out.println("ID: "+i+" "+assmlist.get(i).getQ()+" Max Marks: "+ assmlist.get(i).getMax()+"\n");
                    }
                }
                else if (sel3==3){
                    s_user.seePending();
                }
                else if (sel3==4){
                    System.out.println("Graded submissions");
                    for (int i=0; i<sublist.size();i++){
                        if (sublist.get(i).getStudent()==s_user && sublist.get(i).getStatus()==1){
                            System.out.println("Submission: "+ sublist.get(i).getAns());
                            System.out.println("Marks scored :"+sublist.get(i).getScore());
                            System.out.println("Graded by: "+sublist.get(i).getChecker().getName());
                        }
                    }
                    System.out.println("Ungraded submissions");
                    for (int i=0; i<sublist.size();i++){
                        if (sublist.get(i).getStudent()==s_user && sublist.get(i).getStatus()==0){
                            System.out.println("Submission: "+ sublist.get(i).getAns());
                            
                        }
                    }
                }
                else if (sel3==5){
                    for (int i=0;i<commlist.size();i++){
                        System.out.println(commlist.get(i).getContent()+" - "+commlist.get(i).getUser().getName());
                        System.out.println(dtf.format(commlist.get(i).getTime()));
                    }
                }
                else if (sel3==6){
                    s_user.putComment();
                }
            }
        }
        
    }
}   
}
class Submission {
    private Student doer;
    private Instructor checker=null;
    private String answer;
    private int marks=0;
    private Assesment problem;
    private int grade_status= 0;    // 0 for ungraded and 1 for graded
    public Submission(Student s, String a, Assesment as){
        this.doer  =s;
        this.answer = a;
        this.problem = as;
    }
    public Student getStudent(){
        return this.doer;
    }
    public int getStatus(){
        return this.grade_status;
    }
    public String getAns(){
        return this.answer;
    }
    public int getScore(){
        return this.marks;
    }
    public Instructor getChecker(){
        return this.checker;
    }
    public Assesment getAssesment(){
        return this.problem;
    }
    public void setMarks(int m){
        this.marks = m;
    }
    public void setStatus(int a){
        this.grade_status=a;
    }
    public void setChecker(Instructor in){
        this.checker = in;
    }
}
class Instructor implements User{
    private String name;
    private int id;
    public Instructor(String n, int i){
        this.name = n;
        this.id = i;
    }
    public String getName(){
        return this.name;
    }
    public int getID(){
        return this.id;
    }
    public void putSlide(){
        System.out.print("Enter topic of slides: ");
        String sld_tpc = a2.scan.nextLine();
        System.out.print("Enter number of slides: ");
        int n_sld = a2.scan.nextInt();
        @SuppressWarnings("unused")
        String misc = a2.scan.nextLine();
        System.out.println("Enter content of slides");
        String sld_array[]  = new String[n_sld];
        for (int i= 0;i<n_sld;i++){
            System.out.print("Content of slide "+ (i+1)+": ");
            sld_array[i] = a2.scan.nextLine();
        }
        a2.sldlist.add(new Slide(sld_tpc, n_sld, sld_array, this));
    }
    public void putVideo(){
        System.out.print("Enter topic of video:- ");
        String vid_title = a2.scan.nextLine();
        System.out.print("Enter filename of video:- ");
        String vid_name = a2.scan.nextLine();
        int len1 = vid_name.length();
        String vid_ext = vid_name.substring(len1-4);
        while (!vid_ext.equals(".mp4")) {
            System.out.println("Invalid file extension. Try again:");
            vid_name = a2.scan.nextLine();
            len1 = vid_name.length();
            System.out.println(vid_ext);
            vid_ext = vid_name.substring(len1-4);
        }
        a2.vidlist.add(new Video(vid_title, vid_name, this));
    }
    public void putAssignment(){
        System.out.print("Enter problem statement: ");
        String pst = a2.scan.nextLine();
        System.out.print("Enter max marks: ");
        int maxm = a2.scan.nextInt();
        @SuppressWarnings("unused")
        String misc = a2.scan.nextLine();
        a2.asslist.add(new Assignment(this, pst, maxm));
        a2.assmlist.add(a2.asslist.get(a2.asslist.size()-1));
    }
    public void putQuiz(){
        System.out.print("Enter quiz question");
        String ques = a2.scan.nextLine();
        a2.quizlist.add(new Quiz(this, ques));
        a2.assmlist.add(a2.quizlist.get(a2.quizlist.size()-1));
    }
    public void putComment(){
        System.out.println("Enter comment: ");
        @SuppressWarnings("unused")
        String misc= a2.scan.nextLine();
        String comm = a2.scan.nextLine();
        a2.commlist.add(new Comment(this,comm));
    }
}
class Student implements User{
    private String name;
    private boolean[] sub_status;
    public Student(String n){
        this.name = n;
        this.sub_status = new boolean[a2.assmlist.size()];
    }
    public String getName(){
        return this.name;
    }
    public void putComment(){
        System.out.print("Enter comment: ");
        String comm = a2.scan.nextLine();
        a2.commlist.add(new Comment(this,comm));
    }
    public void seePending(){
        int count=0;
        int sel4=-1;
        String misc;
        for (int i=0; i<a2.assmlist.size();i++){
            if (!a2.assmlist.get(i).getSubmitters().contains(this) && a2.assmlist.get(i).getStatus()=="OPEN"){
                count+=1;
            }
        }
        if (count==0){
            System.out.println("No pending assesments");
        }
        else{
            System.out.println("Pending Assesments");
            for (int i=0; i<a2.assmlist.size();i++){
                if (!a2.assmlist.get(i).getSubmitters().contains(this) && a2.assmlist.get(i).getStatus()=="OPEN"){
                    System.out.println("ID: "+i+" "+a2.assmlist.get(i).getQ()+" Max Marks: "+ a2.assmlist.get(i).getMax());
            }
        }
            System.out.print("Enter ID of assignment: ");
            sel4 = a2.scan.nextInt();
            misc = a2.scan.nextLine();
            String file_name=null;
            String extn;
            if (a2.assmlist.get(sel4).getType()=="Assignment"){
            System.out.print("Enter file name of submission: ");
            file_name = a2.scan.nextLine();
            extn = file_name.substring(file_name.length()-4);
            while (!extn.equals(".zip")){
                System.out.print("Invalid extension. Try again: ");
                file_name = a2.scan.nextLine();
                extn = file_name.substring(file_name.length()-4);
            }
        }
        else if (a2.assmlist.get(sel4).getType()=="Quiz"){
        System.out.print(a2.assmlist.get(sel4).getQ()+" ");
        file_name = a2.scan.nextLine();
        }
        a2.sublist.add(new Submission(this, file_name, a2.assmlist.get(sel4)));
        a2.assmlist.get(sel4).getSubmitters().add(this);
    }
    }
}
class Video{
    private String name;
    private String fname;
    private Instructor uploader;
    private LocalDateTime time;
    public Video(String n, String f, Instructor u){
        this.name = n;
        this.fname = f;
        this.uploader = u;
        this.time= LocalDateTime.now();
    }
    public String getTitle(){
        return this.name;
    }
    public Instructor getUpld(){
        return this.uploader;
    }
    public String getFile(){
        return this.fname;
    }
    public LocalDateTime getTime(){
        return this.time;
    }
}

interface Classes{
    public void view();
}
class Comment{
    private User user;
    private LocalDateTime c_time;
    private String content;
    public Comment(User u, String c){
        this.user = u;
        this.c_time = LocalDateTime.now();
        this.content = c;
    }
    public LocalDateTime getTime(){
        return this.c_time;
    }
    public User getUser(){
        return this.user;
    }
    public String getContent(){
        return this.content;
    }
}
interface User{
    public String getName();
    public void putComment();
}
class Slide{
    private String topic;
    private int n_sld;
    private String[] arr_sld;
    private LocalDateTime time;
    private Instructor uploader;
    public Slide(String t, int n, String[] a, Instructor u){
        this.topic = t;
        this.n_sld = n;
        this.arr_sld = a;
        this.time = LocalDateTime.now();
        this.uploader = u;
    }
    public int getNsld(){
        return this.n_sld;
    }
    public String[] getSlides(){
        return this.arr_sld;
    }
    public String getTopic(){
        return this.topic;
    }
    public LocalDateTime getTime(){
        return this.time;
    }
    public Instructor getUpldr(){
        return this.uploader;
    }
}

class Assignment implements Assesment{
    private Instructor uploader;
    private String p_st;
    private int max_m;
    private String status="OPEN";
    private ArrayList<Student> sub_list= new ArrayList<Student>();
    public Assignment(Instructor u, String st, int m){
        this.uploader = u;
        this.p_st = st;
        this.max_m = m;
    }
    public void setStatus(int a){
        if (a==0){
            this.status = "OPEN";
        }
        else if (a==1){
            this.status = "CLOSED";
        }
    }
    public String getStatus(){
        return this.status;
    }
    public String getQ(){
        return this.p_st;
    }
    public int getMax(){
        return this.max_m;
    }
    public Instructor getUploader(){
        return this.uploader;
    }
    public ArrayList<Student> getSubmitters(){
        return this.sub_list;
    }
    public String getType(){
        return "Assignment";
    }
}
interface Assesment{
    public void setStatus(int a);
    public String getStatus();
    public String getQ();
    public int getMax();
    public ArrayList<Student> getSubmitters();
    public String getType();
}
class Quiz implements Assesment{
    private Instructor uploader;
    private String p_st;
    private ArrayList<Student> sub_list = new ArrayList<Student>(); 
    @SuppressWarnings("unused")
    private int max_m=1;
    private String status="OPEN";
    public Quiz(Instructor u, String st){
        this.uploader = u;
        this.p_st = st;
    }
    public void setStatus(int a){
        if (a==0){
            this.status = "OPEN";
        }
        else if (a==1){
            this.status = "CLOSED";
        }
    }
    public String getStatus(){
        return this.status;
    }
    public String getQ(){
        return this.p_st;
    }
    public int getMax(){
        return 1;
    }
    public Instructor getUploader(){
        return this.uploader;
    }
    public ArrayList<Student> getSubmitters(){
        return this.sub_list;
    }
    public String getType(){
        return "Quiz";
    }
}