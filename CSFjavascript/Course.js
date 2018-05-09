/* Generated from Java with JSweet 2.0.0 - http://www.jsweet.org */
var Course = (function () {
    function Course(n, type, list, isAP) {
        var _this = this;
        this.name = "";
        this.classType = ClassType.OTHER;
        this.listNum = 1;
        this.ap = false;
        this.grade = 0;
        if (((typeof n === 'string') || n === null) && ((typeof type === 'number') || type === null) && ((typeof list === 'number') || list === null) && ((typeof isAP === 'boolean') || isAP === null)) {
            var __args = Array.prototype.slice.call(arguments);
            this.name = "";
            this.classType = ClassType.OTHER;
            this.listNum = 1;
            this.ap = false;
            this.grade = 0;
            (function () {
                _this.name = n;
                _this.classType = type;
                _this.listNum = list;
                _this.ap = isAP;
            })();
        }
        else if (((typeof n === 'string') || n === null) && ((typeof type === 'number') || type === null) && ((typeof list === 'boolean') || list === null) && isAP === undefined) {
            var __args = Array.prototype.slice.call(arguments);
            var list_1 = __args[1];
            var isAP_1 = __args[2];
            this.name = "";
            this.classType = ClassType.OTHER;
            this.listNum = 1;
            this.ap = false;
            this.grade = 0;
            (function () {
                _this.name = n;
                _this.listNum = list_1;
                _this.ap = isAP_1;
            })();
        }
        else if (n === undefined && type === undefined && list === undefined && isAP === undefined) {
            var __args = Array.prototype.slice.call(arguments);
            this.name = "";
            this.classType = ClassType.OTHER;
            this.listNum = 1;
            this.ap = false;
            this.grade = 0;
            (function () {
                _this.name = "none";
                _this.ap = false;
                _this.listNum = 0;
            })();
        }
        else
            throw new Error('invalid overload');
    }
    Course.prototype.getName = function () {
        return this.name;
    };
    Course.prototype.setGrade$int = function (a) {
        this.grade = a;
    };
    Course.prototype.setGrade$java_lang_String = function (gr) {
        var a = this.StringToGrade(gr);
        this.grade = a;
    };
    Course.prototype.setGrade = function (gr) {
        if (((typeof gr === 'string') || gr === null)) {
            return this.setGrade$java_lang_String(gr);
        }
        else if (((typeof gr === 'number') || gr === null)) {
            return this.setGrade$int(gr);
        }
        else
            throw new Error('invalid overload');
    };
    Course.prototype.StringToGrade = function (g) {
        var a = 0;
        a = (g === "A") ? 2 : a;
        a = (g === "B") ? 1 : a;
        return a;
    };
    Course.prototype.getScore$ = function () {
        return this.getScore$int(this.grade);
    };
    Course.prototype.getScore$int = function (grade) {
        var score = 0;
        score = grade;
        if (grade <= 0) {
            return 0;
        }
        score = (grade === 2) ? 3 : (score);
        score = (this.ap) ? (score + 1) : (score);
        score = (this.listNum === 0) ? 0 : (score);
        return score;
    };
    Course.prototype.getScore = function (grade) {
        if (((typeof grade === 'number') || grade === null)) {
            return this.getScore$int(grade);
        }
        else if (grade === undefined) {
            return this.getScore$();
        }
        else
            throw new Error('invalid overload');
    };
    return Course;
}());
Course["__class"] = "Course";
var ClassType;
(function (ClassType) {
    ClassType[ClassType["ENGLISH"] = 0] = "ENGLISH";
    ClassType[ClassType["MATH"] = 1] = "MATH";
    ClassType[ClassType["SOCIAL_SCIENCE"] = 2] = "SOCIAL_SCIENCE";
    ClassType[ClassType["SCIENCE"] = 3] = "SCIENCE";
    ClassType[ClassType["FOREIGN_LANGUAGE"] = 4] = "FOREIGN_LANGUAGE";
    ClassType[ClassType["OTHER"] = 5] = "OTHER";
})(ClassType || (ClassType = {}));
