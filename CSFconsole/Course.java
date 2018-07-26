// July 24, 2018

public class Course
{
    String name = "";
    ClassType classType = ClassType.OTHER;
    int listNum = 1;// [1 - 3]; 0 is N/A
    boolean ap = false;
    
    int grade = 0;
    
    // GRADE: 0=C, 1=B, 2=A
    /*
     * RULES:
     * 10 pts needed; 7 from I(4) + II(3), and 3 from III
     * CP: A=3, B=1
     * AP: A=4, B=2 (max 2)
     */
    
    public Course()
    {
        name = "unset";
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
    public String getGrade(){ return GradeToString(grade); }
    
    public void setGrade(int a)
    {
        grade = a;
    }
    public void setGrade(String gr)
    {
        int a = StringToGrade(gr);
        grade = a;
    }
    int StringToGrade(String g){
        int a = 0;
        a = (g.contains("A")) ? 2 : a;
        a = (g.contains("B")) ? 1 : a;
        return a;
    }
    String GradeToString(int g){
        String a = "C";
        a = (g==2) ? "A" : a;
        a = (g==1) ? "B" : a;
        return a;
    }
    public int getScore()
    {
        return getScore(grade);
    }
    public int getScore(int grade)
    {
        int score = 0;
        score = grade;
        if(grade <= 0) { return 0; }// C or below
        
        score = (grade==2) ? 3 : (score);// If grade is A, score = 3 pts
        score = (ap) ? (score+1) : (score);// If AP/Honors, add 1 pt
        
        score = (listNum==0) ? 0 : (score);// If course is N/A, no pts
        
        return score;
    }
}

enum ClassType {
    // LIST I
    ENGLISH, MATH, SOCIAL_SCIENCE, SCIENCE, FOREIGN_LANGUAGE, OTHER;
}