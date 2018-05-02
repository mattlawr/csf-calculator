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
        new Course("Math CP", 1, true),
        new Course("Science CP", 1, false),
        new Course("Physics AP", 1, true),
        new Course("Perf. Arts", 3, false)
    };
    
    public static void main(String[] args)
    {
        Window w = new Window();
        w.setCoursesList(courses);
        w.setCourseChoices(CourseNames(courses));
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
                chosenCourses = findCourses(choiceCourses, coursesList);
                int sc = getTotalScore();
                label1.setText("SCORE: " + sc);
            }
        });
        
        // Set frame
        setSize(650,300);
        setLayout(null);
        setVisible(true);
    }
    
    public void setCoursesList(Course[] c) {
        coursesList = c;
    }
    public static Course[] findCourses(Choice[] cs, Course[] cl){
        ArrayList<Course> list = new ArrayList<Course>();
        for(Choice c : cs){
            int i = c.getSelectedIndex();
            list.add(cl[i]);
        }
        
        Course[] cArr = list.toArray(new Course[0]);
        return cArr;
    }
    public void setCourseChoices(String[] choices)// Sets courseChoices
    {
        final int width = 120;
        for(int i = 0; i < choiceCourses.length; i++)
        {
            choiceCourses[i] = setChoiceOptions(choices);
            choiceCourses[i].setBounds(20+(width*i),120,width,20);
            add(choiceCourses[i]);
        }
    }
    public static Choice setChoiceOptions(String[] choices)
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
            s += c.getScore(2);// A grade
        }
        return s;
    }
}