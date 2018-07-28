/// CSF Calculator; July 27, 2018
/// for membership eligibility
///

//Note: consider using jQuery in future

var courses = [null, new Course(), new Course(), new Course(), new Course(), new Course()];

updateReport();

function updateReport() {
    var table, rows, i;
    table = document.getElementById("cTable");
    rows = table.getElementsByTagName("TR");
    for (i = 1; i < rows.length; i++) {
        var items = rows[i].getElementsByTagName("TD");

        items[0].innerHTML = i;
        items[1].innerHTML = courses[i].getNameAP();
        items[2].innerHTML = GradeToText(courses[i].grade);
        items[3].innerHTML = GetScore(courses[i].grade, courses[i].ap);
    }
}

function editCourses() {
    var id = prompt("Please enter ID to edit [1-5]", "1");
    if (id == null || isNaN(parseInt(id)) || id == "" || id <= 0) {
    } else
    {
        id = parseInt(id);
        console.log(id + "= id is " + (typeof id));
        editCourse(id);
    }

}
function editCourse(id) {
    var name = prompt("Please enter a course name", "English I");
    if (name == null || name == "") {
        name = "unset";
    }

    var ap = prompt("Is this course AP/Honors? [y/n]", "n");
    if (ap.includes("y") || ap.includes("Y")) {
        ap = true;
    } else { ap = false; }

    var grade = prompt("Please enter earned grade [A/B/C]", "C");
    if (grade == null || grade == "") {
        grade = "C";
    }

    console.log(id);
    courses[id].name = name;
    courses[id].ap = ap;
    courses[id].grade = TextToGrade(grade);

    updateReport();
}

function Course() {
    this.name = "unset";
    this.listNum = 1;
    this.ap = false;
    this.grade = 0;

    // GRADE: 0=C, 1=B, 2=A
    /*
     * RULES:
     * 10 pts needed; 7 from I(4) + II(3), and 3 from III
     * CP: A=3, B=1
     * AP: A=4, B=2 (max 2)
     */

    this.getNameAP = function() { return (this.name + ((this.ap) ? "*" : ""));};
}

function GetScore(g, ap)
{
    var score = 0;
    if (g <= 0) { return 0; }// C or below = 0 pts

    score = (g == 2) ? 3 : 1;// If grade is A, score = 3 pts, else B score = 1 pt
    score = (ap) ? (score + 1) : (score);// If AP/Honors, add 1 pt

    //score = (listNum == 0) ? 0 : (score);// If course is N/A, no pts

    return score;
}

// Converts int to string grades
function GradeToText(g) {
    var text = "C";
    text = (g == 2) ? "A" : text;
    text = (g == 1) ? "B" : text;
    return text;
}
// Converts string to int grades
function TextToGrade(txt) {
    var g = 0;
    g = (txt.includes("A")) ? 2 : g;
    g = (txt.includes("B")) ? 1 : g;
    return g;
}