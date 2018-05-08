// May 1, 2018

import java.awt.*;
import java.awt.event.*;
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
    static String[] grades = { "A", "B", "C" };
    
    public static void main(String[] args)
    {
        Window w = new Window();
        w.setCoursesList(courses);
        //w.setCourseChoices(CourseNames(courses));
        //w.setGradeChoices(grades);
        w.setChoices(CourseNames(courses), grades);
        w.setUI();
    }
    
    static String[] CourseNames(Course[] courses) {
        ArrayList<String> list = new ArrayList<String>();
        for(Course c : courses){
            list.add(c.getName());
        }
        String[] sArr = list.toArray(new String[0]);
        return sArr;
    }
}

class Window extends Frame
{
    Choice[] choiceCourses = new Choice[5];
    Choice[] choiceGrades = new Choice[5];
    Label[] courseScores = new Label[5];
    
    Course[] chosenCourses = new Course[5];
    Course[] coursesList;
    int score = 0;
    
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
}