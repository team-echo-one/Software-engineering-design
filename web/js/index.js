/**
 * Created by lenovo on 2016/6/2.
 */
$(function(){


    var identity=localStorage.getItem('identity');
    if(!identity){
        location.replace('/');
    }
    var teacher=$('#teacher');
    var student=$('#student');
    var registrar=$('#registrar');
    if(identity=='teacher'){
        student.hide();
        registrar.hide();
        teacher.show();
    }else if(identity=='registrar'){
        teacher.hide();
        student.hide();
        registrar.show();
    }else{
        teacher.hide();
        registrar.hide();
        student.show();
    }
    var logOut=$('div.welcome>a').eq(0);
    logOut.click(function(){
        localStorage.removeItem('identity');
        localStorage.removeItem('id');
        alert('logout');
        location.replace("/");

    });

    var iViewReport=$("#iViewReport");
    var iUpdateSchedule=$('#iUpdateSchedule');
    var iDeleteSchedule=$('#iDeleteSchedule');
    var iSelectOfferings=$('#iSelectOfferings');
    var iUpdateGrades= $('#iUpdateGrades');
    var iSelectCourses= $('#iSelectCourses');
    var iAddProfessor= $('#iAddProfessor');
    var iUpdateProfessor= $('#iUpdateProfessor');
    var iDeleteProfessor= $('#iDeleteProfessor');
    var iAddStudent= $('#iAddStudent');
    var iUpdateStudent= $('#iUpdateStudent');
    var iDeleteStudent= $('#iDeleteStudent');

    var viewReport=$("#viewReport");
    var updateSchedule=$('#updateSchedule');
    var deleteSchedule=$('#deleteSchedule');
    var selectOfferings=$('#selectOfferings');
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
            content.hide();
            des.show();
        });
    }
    handleClick(iViewReport,viewReport);
    handleClick(iUpdateSchedule,updateSchedule);
    handleClick(iDeleteSchedule,deleteSchedule);
    handleClick(iSelectOfferings,selectOfferings);
    handleClick(iUpdateGrades,updateGrades);
    handleClick(iSelectCourses,selectCourses);
    handleClick(iAddProfessor,addProfessor);
    handleClick(iUpdateProfessor,updateProfessor);
    handleClick(iDeleteProfessor,deleteProfessor);
    handleClick(iAddStudent,addStudent);
    handleClick(iUpdateStudent,updateStudent);
    handleClick(iDeleteStudent,deleteStudent);

});