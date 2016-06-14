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
    setInterval(function(){
        var date=new Date();
        var day=date.toLocaleDateString();
        var hour=parseInt(date.getHours())<10?0+''+date.getHours():date.getHours();
        var minute=parseInt(date.getMinutes())<10?0+''+date.getMinutes():date.getMinutes();
        var second=parseInt(date.getSeconds())<10?0+''+date.getSeconds():date.getSeconds();
        time.text(day+' '+hour+':'+minute+':'+second);
    },1000);

    /**show function*/
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


});