/**
 * Created by lenovo on 2016/6/2.
 */
$(function(){

    /**verify identity*/
    var identity=localStorage.getItem('identity');
    if(!identity){
        location.replace('/');
    }

    /**show time*/
    var time=$('.time');
    var endTime=localStorage.getItem('endTime');
    endTime+=' Semester: '+localStorage.getItem('semester'); 
    time.text(endTime);

    /**show function*/
    var teacher=$('#teacher');
    var student=$('#student');
    var registrar=$('#registrar');
    var inform=$('.inform');
    var endRegistrar=localStorage.getItem('endRegistrar');
    var iAddCourse=$('#iAddCourse');
    var iDeleteCourse=$('#iDeleteCourse');
    var openClose=$('#openClose');
    if(identity=='teacher'){
        teacher.show();
    }else if(identity=='registrar'){
        registrar.show();
    }else{
        student.show();
        inform.show();
        if(endRegistrar=='over'){
        	iAddCourse.hide();
        	iDeleteCourse.hide();
        	openClose.val('Open Registrar');
        	openClose.removeClass('btn-info');
        	openClose.addClass('btn-success');
        }else{
        	openClose.val('Close Registrar');
        	openClose.removeClass('btn-success');
        	openClose.addClass('btn-info');
        }
    }

    /** show name*/
    var name=$('div.welcome>div');
    name.text('welcome '+localStorage.getItem('name')+'!');

    /** handle logout */
    var logOut=$('div.welcome>a');
    var info=$('.info');
    var message=$('.info>div:nth-child(1)');
    var yes=$('.info>div:nth-child(2)>div:nth-child(1)>a');
    var no=$('.info>div:nth-child(2)>div:nth-child(2)>a');
    logOut.click(function(event){
        event.stopPropagation();
        event.preventDefault();
        message.text(localStorage.getItem('name')+'\r\n'+'want to log out?');
        info.show();
    });
    yes.click(function(){
        info.hide();
        localStorage.removeItem('id');
        localStorage.removeItem('name');
        localStorage.removeItem('identity');
        location.replace('/');
    });
    no.click(function(){
       info.hide();
    });
    /** handle hide inform and info */
    var body=$('body');
    var informContent=$('.informContent');
    body.click(function(){
        if(informContent.css('display')=='block') {
            informContent.hide();
        }
        if(info.css('display')=='block'){
            info.hide();
        }
    });


    var iViewReport=$("#iViewReport");
    var iAddCourse=$('#iAddCourse');
    var iDeleteCourse=$('#iDeleteCourse');

    var iSubmitGrade= $('#iSubmitGrade');
    var iSelectTeach= $('#iSelectTeach');

    var iAddProfessor= $('#iAddProfessor');
    var iUpdateProfessor= $('#iUpdateProfessor');
    var iDeleteProfessor= $('#iDeleteProfessor');
    var iAddStudent= $('#iAddStudent');
    var iUpdateStudent= $('#iUpdateStudent');
    var iDeleteStudent= $('#iDeleteStudent');
    var iEndRegistrar=$('#iEndRegistrar');

    var viewReport=$("#viewReport");
    var addCourse=$('#addCourse');
    var deleteCourse=$('#deleteCourse');

    var submitGrade= $('#submitGrade');
    var selectTeach= $('#selectTeach');

    var addProfessor= $('#addProfessor');
    var updateProfessor= $('#updateProfessor');
    var deleteProfessor= $('#deleteProfessor');
    var addStudent= $('#addStudent');
    var updateStudent= $('#updateStudent');
    var deleteStudent= $('#deleteStudent');
    var endRegistrar=$('#endRegistrar');

    var content=$('.content');

    function handleClick(src,des){
        src.click(function(){
            content.hide();
            des.show();
        });
    }
    handleClick(iViewReport,viewReport);
    handleClick(iAddCourse,addCourse);
    handleClick(iDeleteCourse,deleteCourse);

    handleClick(iSubmitGrade,submitGrade);
    handleClick(iSelectTeach,selectTeach);
    
    handleClick(iAddProfessor,addProfessor);
    handleClick(iUpdateProfessor,updateProfessor);
    handleClick(iDeleteProfessor,deleteProfessor);
    handleClick(iAddStudent,addStudent);
    handleClick(iUpdateStudent,updateStudent);
    handleClick(iDeleteStudent,deleteStudent);
    handleClick(iEndRegistrar,endRegistrar);


});