/**
 * Created by lenovo on 2016/6/2.
 */
$(function(){
   
    var identity=localStorage.getItem('identity');
    var teacher=$('#teacher');
    var student=$('#student');
    var registrar=$('#registrar');
    if(identity=='teacher'){
        teacher.show();
        student.hide();
        registrar.hide();
    }else if(identity=='registrar'){
        registrar.show();
        teacher.hide();
        student.hide();
    }else{
        student.show();
        teacher.hide();
        registrar.hide();
    }

    var iViewReport=$("#iViewReport");
    var iCreateSchedule=$('#iCreateSchedule');
    var iUpdateSchedule=$('#iUpdateSchedule');
    var iDeleteSchedule=$('#iDeleteSchedule');
    var iSelectOfferings=$('#iSelectOfferings');
    var iSubmitSchedule=$('#iSubmitSchedule');
    var iUpdateGrades= $('#iUpdateGrades');
    var iSelectCourses= $('#iSelectCourses');
    var iAddProfessor= $('#iAddProfessor');
    var iUpdateProfessor= $('#iUpdateProfessor');
    var iDeleteProfessor= $('#iDeleteProfessor');
    var iAddStudent= $('#iAddStudent');
    var iUpdateStudent= $('#iUpdateStudent');
    var iDeleteStudent= $('#iDeleteStudent');

    var viewReport=$("#viewReport");
    var createSchedule=$('#createSchedule');
    var updateSchedule=$('#updateSchedule');
    var deleteSchedule=$('#deleteSchedule');
    var selectOfferings=$('#selectOfferings');
    var submitSchedule=$('#submitSchedule');
    var updateGrades= $('#updateGrades');
    var selectCourses= $('#selectCourses');
    var addProfessor= $('#addProfessor');
    var updateProfessor= $('#updateProfessor');
    var deleteProfessor= $('#deleteProfessor');
    var addStudent= $('#addStudent');
    var updateStudent= $('#updateStudent');
    var deleteStudent= $('#deleteStudent');

    var content=$('.content');

    function handleClick(src,des){
        src.click(function(){
            alert(src.text());
            content.hide();
            des.show();
        });
    }
    handleClick(iViewReport,viewReport);
    handleClick(iCreateSchedule,createSchedule);
    handleClick(iUpdateSchedule,updateSchedule);
    handleClick(iDeleteSchedule,deleteSchedule);
    handleClick(iSelectOfferings,selectOfferings);
    handleClick(iSubmitSchedule,submitSchedule);
    handleClick(iUpdateGrades,updateGrades);
    handleClick(iSelectCourses,selectCourses);
    handleClick(iAddProfessor,addProfessor);
    handleClick(iUpdateProfessor,updateProfessor);
    handleClick(iDeleteProfessor,deleteProfessor);
    handleClick(iAddStudent,addStudent);
    handleClick(iUpdateStudent,updateStudent);
    handleClick(iDeleteStudent,deleteStudent);

});