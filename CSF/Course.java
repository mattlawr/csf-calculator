// May 1, 2018

public class Course
{
    String name = "";
    ClassType classType = ClassType.OTHER;
    int listNum = 1;// [1 - 3]; 0 is N/A
    boolean ap = false;
    
    // GRADE: 0=C, 1=B, 2=A
    /*
     * RULES:
     * 10 pts needed; 7 from I(4) + II(3), and 3 from III
     * CP: A=3, B=1
     * AP: A=4, B=2 (max 2)
     */
    
    public Course()
    {
        name = "none";
        ap = false;
        listNum = 0;
    }
    public Course(String n, ClassType type, int list, boolean isAP)
    {
        name = n;
        classType = type;
        listNum = list;
        ap = isAP;
    }
    public Course(String n, int list, boolean isAP)
    {
        name = n;
        listNum = list;
        ap = isAP;
    }
    
    public String getName(){ return name; }
    
    public int getScore(int grade)
    {
        int score = 0;
        score = grade;
        score = (ap) ? (score+1) : (score);
        
        score = (listNum==0) ? 0 : (score);
        
        return score;
    }
}

enum ClassType {
    // LIST I
    ENGLISH, MATH, SOCIAL_SCIENCE, SCIENCE, FOREIGN_LANGUAGE, OTHER;
}