/**
 * Created by lenovo on 2016/6/2.
 */
$(function(){
   
    var student=$('#student>div[class="topBar"]');
    var registrar=$('#registrar>div[class="topBar"]');
    var teacher=$('#teacher>div[class="topBar"]');
    var studentList=$('#studentList');
    var teacherList=$('#teacherList');
    var registrarList=$('#registrarList');
    var chooseList=$('.chooseList');

    function handleLogic(src,des){
        $(src).mouseover(function(){
            chooseList.hide();
            $(des).show();
        });
    }
    handleLogic(student,studentList);
    handleLogic(teacher,teacherList);
    handleLogic(registrar,registrarList);
    
});