// July 24, 2018

// CSF Calculator: console edition

import java.util.*;

public class Main
{
    static Course[] courses = {
        new Course(),
        new Course("English AP", 1, true),
        new Course("History CP", 1, false),
        new Course("Math AP", 1, true),
        new Course("Math CP", 1, false),
        new Course("Science CP", 1, false),
        new Course("Physics AP", 1, true),
        new Course("Perf. Arts", 3, false)
    };
    //static String[] grades = { "A", "B", "C" };
    
    // main method
    public static void main(String[] args)
    {
        csfReport report = new csfReport();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("CSF CALCULATOR - CONSOLE EDITION\nby Matthew Lawrence\nwritten in Java (July 2018)");
        
        pause(500);
        
        int choice = 0;
        // using a loop as way of repeating the display and choice phase of the program
        while (choice != 4) {
            // displaying the options and taking in the user input (added an exit option 4)
            System.out.println(
                               "\nPlease enter an option: \n[1] - Edit report\n[2] - Print report\n\n[3] - Reset\n[4] - Exit");
            choice = input.nextInt();
            System.out.println("");
            // using a switch statement to execute the different calculator methods
            switch (choice) {
                case 1:
                    report.PrintReport();
                    System.out.println("Enter course ID to edit:");
                    int id = input.nextInt(); pause(100);
                    System.out.println("Enter course name:");
                    String cName = input.next(); pause(100);
                    System.out.println("Is AP course? [y/n]:");
                    boolean cAP = (input.next() == "y") ? true:false; pause(100);
                    System.out.println("Enter course grade [A/B/C]:");
                    String cGrade = input.next(); pause(100);
                    
                    report.choiceCourses[id] = new Course(cName, 1, cAP);
                    report.choiceCourses[id].setGrade(cGrade);
                    //System.out.println(cGrade + "-" + report.choiceCourses[id].getGrade());
                    
                    System.out.println("Course overwritten.");
                    
                    pause(1000);
                    break;
                case 2:
                    // Using csfReport class
                    report.PrintReport();
                    break;
                case 3:
                    // Using csfReport class
                    report = new csfReport();
                    System.out.println("CSF report reset.");
                    
                    pause(1000);
                    break;
                case 2001:
                    for(int i = 0; i < 2001; i++) { System.out.println("you fool #" + (i+1)); }
                    
                    pause(1000);
                    break;
            }
        }
        if(choice == 4) {
            input.close();
            System.out.println("Exiting...");
            pause(800);
            System.out.println("Goodbye world");
        }
    }
    //
    
    static String[] CourseNames(Course[] courses) {
        ArrayList<String> list = new ArrayList<String>();
        for(Course c : courses){
            list.add(c.getName());
        }
        String[] sArr = list.toArray(new String[0]);
        return sArr;
    }
    
    static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}

// Holds methods and data for the user's selected grades
class csfReport
{
    public Course[] choiceCourses = new Course[5];
    
    csfReport() {
        for (int i = 0; i < choiceCourses.length; i++)
        {
            choiceCourses[i] = new Course();
        }
        
        //PrintReport();
    }
    
    public void PrintReport()
    {
        String leftAlignFormat = "| %-2d | %-15s | %5s | %5d |%n";
        
        System.out.format("+----+-----------------+-------+-------+%n");
        System.out.format("| ID | Course          | Grade | Value |%n");
        System.out.format("+----+-----------------+-------+-------+%n");
        for (int i = 0; i < choiceCourses.length; i++)
        {
            System.out.format(leftAlignFormat,
                              i,
                              choiceCourses[i].getName(),
                              choiceCourses[i].getGrade(),
                              choiceCourses[i].getScore());
        }
        System.out.format("+----+-----------------+-------+-------+%n");
        System.out.format(leftAlignFormat, 0, "", "TOTAL", getTotalScore());
    }
    
    public int getTotalScore()
    {
        int s = 0;
        for(Course c : choiceCourses)
        {
            s += c.getScore();// Grade
        }
        return s;
    }
}


/*class Window extends Frame
{
    Choice[] choiceCourses = new Choice[5];
    Choice[] choiceGrades = new Choice[5];
    Label[] courseScores = new Label[5];
    
    Course[] chosenCourses = new Course[5];
    Course[] coursesList;
    int score = 0;
    
    /*public void actionPerformed(ActionEvent event)
    {
        
    }
    
    Window()
    {
        addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }
        });
        
        // Create components
        final Label label1 = new Label();
        label1.setBounds(20,30,200,20); add(label1);
        Button b = new Button("CALCULATE");
        b.setBounds(20,50,80,30); add(b);
        
        // Events
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenCourses = findCourses(choiceCourses, choiceGrades, coursesList);
                int sc = getTotalScore();
                label1.setText("SCORE: " + sc);
                
                findScores(courseScores, chosenCourses);
            }
        });
        
        // Set frame
        setSize(650,300);
        setTitle("CSF Calculator 2018");
        setBackground(Color.white);
        setLayout(null);
        setVisible(true);
    }
    
    public void setCoursesList(Course[] c) {
        coursesList = c;
    }
    // Return selected courses (Course[] cl) based on (Choice[] cs) and (Choice[] gr)
    public static Course[] findCourses(Choice[] cs, Choice[] gr, Course[] cl)
    {
        ArrayList<Course> list = new ArrayList<Course>();
        int index = 0;
        for(Choice c : cs)
        {
            int i = c.getSelectedIndex();
            Course coursePair = cl[i];// To set grade int
            coursePair.setGrade(gr[index].getSelectedItem());// Based on gradeChoice[index]
            
            list.add(coursePair);
            
            index++;
        }
        
        Course[] cArr = list.toArray(new Course[0]);
        return cArr;
    }
    static Label[] findScores(Label[] labels, Course[] courses)// Returns label array with printed scores
    {
        int count = courses.length;
        for(int i = 0; i < count; i++) {
            labels[i].setText(courses[i].getScore() + "");
        }
        return labels;
    }
    public void setUI()
    {
        int w = 120;
        Label[] scoreLabels = new Label[5];
        for(int i = 0; i < scoreLabels.length; i++) { scoreLabels[i] = new Label(); scoreLabels[i].setText("--"); }
        
        courseScores = CompToLabel(setUIRow(scoreLabels, new int[]{ 20,180,w,20 }));
    }
    public void setChoices(String[] courses, String[] grades)
    {
        int w = 120;
        choiceCourses = setUIChoices(courses, 5, new int[]{ 20,120,w,20 });
        choiceGrades = setUIChoices(grades, 5, new int[]{ 20,150,w,20 });
    }
    
    public Component[] setUIRow(Component[] baseComps, int[] rect)// Returns components
    {
        int w = rect[2];// width
        int count = baseComps.length;
        Component[] comp = new Component[count];
        for(int i = 0; i < count; i++)
        {
            comp[i] = baseComps[i];
            comp[i].setBounds(rect[0]+(w*i),rect[1],w,rect[3]);
            
            add(comp[i]);// For AWT ***
        }
        return comp;
    }
    
    public Choice[] setUIChoices(String[] choices, int count, int[] rect)// Returns choices
    {
        int w = rect[2];
        Choice[] ch = new Choice[count];
        for(int i = 0; i < count; i++)
        {
            ch[i] = setChoiceOptions(choices);
            ch[i].setBounds(rect[0]+(w*i),rect[1],w,rect[3]);
            add(ch[i]);// For awt
        }
        return ch;
    }
    public static Choice setChoiceOptions(String[] choices)// Returns Choice with arg
    {
        Choice choice = new Choice();
        for(String c : choices)
        {
            choice.add(c);
        }
        return choice;
    }
    public int getTotalScore()
    {
        int s = 0;
        for(Course c : chosenCourses)
        {
            s += c.getScore();// Grade
        }
        return s;
    }
    
    Label[] CompToLabel(Component[] cArr) {
        Label[] arr = new Label[cArr.length];
        for(int i = 0; i < cArr.length; i++) {
            arr[i] = (Label)cArr[i];
        }
        return arr;
    }
}*/