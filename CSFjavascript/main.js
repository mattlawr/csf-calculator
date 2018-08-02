/// CSF Calculator; July 27, 2018
/// for membership eligibility
///

//Note: consider using jQuery in future

var courses = [null, new Course(), new Course(), new Course(), new Course(), new Course()];

var reqData = {
    listAmt: [0, 0, 0, 0],
    listPnt: [0, 0, 0, 0],
    apAmt: 0
};
// See NewReqDataObject()...

updateReport();

function updateReport() {
    // UPDATE REPORT
    updateReq();
    var table, rows, i;
    table = document.getElementById("cTable");
    rows = table.getElementsByTagName("TR");
    for (i = 1; i < rows.length; i++) {
        var items = rows[i].getElementsByTagName("TD");

        //items[0].innerHTML = i;
        items[1].innerHTML = courses[i].getNameAP();
        items[2].innerHTML = GradeToText(courses[i].grade);
        items[3].innerHTML = DisplayScore(courses[i]);
    }
    document.getElementById("total").innerHTML = "TOTAL = " + GetTotalScore();

    // CHECK REPORT
	var listSum = (reqData.listPnt[1] + reqData.listPnt[2]);

	var listICheck = "<b>LIST I:</b> " + ((reqData.listPnt[1] >= 4) ? "4 POINTS MET" : ("NEED " + (4 - reqData.listPnt[1]) + " MORE POINTS (4 points minimum)"));

	var listDouCheck = "<b>LIST I + II:</b> " + ((listSum >= 7) ? "7 POINTS MET" : ("NEED " + (7 - listSum) + " MORE POINTS (7 point sum minimum)"));
    
	var listIIICheck = "<b>LIST III:</b> " + ((reqData.listPnt[3] <= 3) ? (reqData.listPnt[3] + " POINTS (3 maximum)") : ((10 - reqData.listPnt[3]) + " TOO MANY POINTS (3 maximum)"));

    var totalCheck = "<b>TOTAL:</b> " + ((GetTotalScore() >= 10) ? "10 POINTS MET" : ("NEED " + (10 - GetTotalScore()) + " MORE POINTS (10 minimum)"));

	var finText = (checkReq()) ? "<b>STUDENT ELIGIBLE</b>" : "<b>STUDENT NOT ELIGIBLE</b>";
	var br = "<br>";

    document.getElementById("result").innerHTML = (listICheck + br + listDouCheck + br + listIIICheck + br + totalCheck + br + finText + br);
}
function updateReq() {
    reqData = NewReqDataObject();// Reset all values

    for (i = 1; i < courses.length; i++) {
        reqData.listAmt[courses[i].listNum]++;
        reqData.listPnt[courses[i].listNum] += GetScore(courses[i].grade, courses[i].ap);
        if (courses[i].ap) { reqData.apAmt++; }
    }
}
function checkReq() {
	var listSum = (reqData.listPnt[1] + reqData.listPnt[2]);

	if((reqData.listPnt[1] >= 10) && (listSum >= 7) && (reqData.listPnt[3] <= 3) && (GetTotalScore() >= 10)){
		return true;
	}
	return false;
}

// Deprecated
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

// Calls prompts to user
function editCourse(id) {
    var name = prompt("Please enter a course name", "English I");
    if (name == null || name == "") {
        name = "unset";
    }

	var list = prompt("Please enter course LIST", "[1/2/3]");
    if (list == null || list == "") {
        list = 0;
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
	courses[id].listNum = list;

    updateReport();
}

// Core object of a course's data, set manually by user
function Course() {
    this.name = "unset";
    this.listNum = 0;
    this.ap = false;
    this.grade = -1;

    // GRADE: 0=C, 1=B, 2=A
    /*
     * RULES:
     * 10 pts needed; 7 from I(4) + II(3), and 3 from III
     * CP: A=3, B=1
     * AP: A=4, B=2 (max 2)
     */

    this.getNameAP = function () {
        return (ListTag(this.listNum) + " " + this.name + ((this.ap) ? "*" : ""));
    };
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
function GetTotalScore() {
    var score, i;
    score = 0;
    for (i = 1; i < courses.length; i++) {
        score += GetScore(courses[i].grade, courses[i].ap);
    }

    return score;
}

function ListTag(n) {
    var spc = "";
    if (n <= 1) { spc = " "; }

    return ("[" + spc + "List " + RomanNumeral(n) + "]");
}
function RomanNumeral(n) {
    var text = "- ";
    if (n == 1) { text = "I "; }
    if (n == 2) { text = "II "; }
    if (n == 3) { text = "III"; }
    return text;
}
function DisplayScore(course) {
    var score = GetScore(course.grade, course.ap);
    var text = score;
    if (course.ap && course.grade >= 1) {
        text = score + " (" + (score - 1) + "+" + 1 + ")";
    }

    return text;
}
// Converts int to string grades
function GradeToText(g) {
    var text = "--";
    text = (g == 2) ? "A" : text;
    text = (g == 1) ? "B" : text;
    text = (g == 0) ? "C" : text;
    return text;
}
// Converts string to int grades
function TextToGrade(txt) {
    var g = 0;
    g = (txt.includes("A")) ? 2 : g;
    g = (txt.includes("B")) ? 1 : g;
    return g;
}

function NewReqDataObject() {
    return {
        listAmt: [0, 0, 0, 0],
        listPnt: [0, 0, 0, 0],
        apAmt: 0
    };
}