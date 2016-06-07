/**
 * Created by lenovo on 2016/6/4.
 */

angular.module('software', [])
    .constant('$id',{
        id:localStorage.getItem('id')
    })
    .controller('addStudent', function ($scope) {
        $scope.submit = function () {
            var data = {
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                graduationDate: $scope.graduationDate
            };
            jQuery.post('/addStudent', data, function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('add success!');
                    } else {
                        alert('add failed!please try again!');
                    }
                } else {
                    alert('network time out!please try again');
                }

            });
        }
    })
    .controller('updateStudent', function ($scope) {
        $scope.students = [];
        $scope.getStudents = function () {
            jQuery.get('/getStudents', function (result, status) {
                if (status == 'success') {
                    $scope.students = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.studentId = 0;
        $scope.selectStudent = function (studentId) {
            for (var i = 0; i < $scope.students.length; i++) {
                var student = $scope.students[i];
                if (student['id'] = studentId) {
                    $scope.name = student['name'];
                    $scope.birthday = student['birthday'];
                    $scope.SSN = student['SSN'];
                    $scope.status = student['status'];
                    $scope.graduationDate = student['graduationDate'];
                }
            }
        };
        $scope.submit = function () {
            var data = {
                id: $scope.studentId,
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                graduationDate: $scope.graduationDate
            };
            jQuery.post('/updateStudent', data, function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('update success!');
                    } else {
                        alert('update failed!please try again!');
                    }
                } else {
                    alert('network time out!please try again');
                }

            });
        }

    })
    .controller('deleteStudent', function ($scope) {
        $scope.students = [];
        $scope.getStudents = function () {
            jQuery.get('/getStudents', function (result, status) {
                if (status == 'success') {
                    $scope.students = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.studentId = 0;
        $scope.selectStudent = function (studentId) {
            for (var i = 0; i < $scope.students.length; i++) {
                var student = $scope.students[i];
                if (student['id'] = studentId) {
                    $scope.name = student['name'];
                    $scope.birthday = student['birthday'];
                    $scope.SSN = student['SSN'];
                    $scope.status = student['status'];
                    $scope.graduationDate = student['graduationDate'];
                }
            }
        };
        $scope.submit = function () {
            var verify = confirm("submit delete?");
            if (verify) {
                var data = {
                    id: $scope.studentId
                };
                jQuery.post('/deleteStudent', data, function (result, status) {
                    if (status == 'success') {
                        result = JSON.parse(result);
                        if (result['info'] == 'success') {
                            alert('delete success!');
                        } else {
                            alert('delete failed!please try again!');
                        }
                    } else {
                        alert('network time out!please try again');
                    }

                });
            }
        }
    })
    .controller('addProfessor', function ($scope) {
        $scope.submit = function () {
            var data = {
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                department: $scope.department
            };
            jQuery.post('/addProfessor', data, function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('add success!');
                    } else {
                        alert('add failed!please try again!');
                    }
                } else {
                    alert('network time out!please try again');
                }

            });
        }
    })
    .controller('updateProfessor', function ($scope) {
        $scope.professors = [];
        $scope.getProfessors = function () {
            jQuery.get('/getProfessors', function (result, status) {
                if (status == 'success') {
                    $scope.professors = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.professorId = 0;
        $scope.selectProfessor = function (professorId) {
            for (var i = 0; i < $scope.professors.length; i++) {
                var professor = $scope.professors[i];
                if (professor['id'] = professorId) {
                    $scope.name = professor['name'];
                    $scope.birthday = professor['birthday'];
                    $scope.SSN = professor['SSN'];
                    $scope.status = professor['status'];
                    $scope.deparment = professor['department'];
                }
            }
        };
        $scope.submit = function () {
            var data = {
                id: $scope.professorId,
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                department: $scope.department
            };
            jQuery.post('/updateProfessor', data, function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('update success!');
                    } else {
                        alert('update failed!please try again!');
                    }
                } else {
                    alert('network time out!please try again');
                }

            });
        }

    })
    .controller('deleteProfessor', function ($scope) {
        $scope.professors = [];
        $scope.getProfessors = function () {
            jQuery.get('/getProfessors', function (result, status) {
                if (status == 'success') {
                    $scope.professors = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.professorId = 0;
        $scope.selectProfessor = function (professorId) {
            for (var i = 0; i < $scope.professors.length; i++) {
                var professor = $scope.professors[i];
                if (professor['id'] = professorId) {
                    $scope.name = professor['name'];
                    $scope.birthday = professor['birthday'];
                    $scope.SSN = professor['SSN'];
                    $scope.status = professor['status'];
                    $scope.department = professor['department'];
                }
            }
        };
        $scope.submit = function () {
            var verify = confirm("submit delete?");
            if (verify) {
                var data = {
                    id: $scope.professorId
                };
                jQuery.post('/deleteProfessor', data, function (result, status) {
                    if (status == 'success') {
                        result = JSON.parse(result);
                        if (result['info'] == 'success') {
                            alert('delete success!');
                        } else {
                            alert('delete failed!please try again!');
                        }
                    } else {
                        alert('network time out!please try again');
                    }

                });
            }
        }
    })
    .controller('viewReport', function ($scope,$id) {
        $scope.courses = [];
        var data = {
            id: $id.id
        };
        $scope.viewReport=function(){
            jQuery.post('/viewReport', data, function (result, status) {
                if (status == 'success') {
                    $scope.courses = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };

    })
    .controller('deleteSchedule', function ($scope,$id) {
        $scope.schedules = [];
        var data = {
            id: $id.id
        };

        $scope.getSchedule = function () {
            jQuery.post('/getSchedule', data, function (result, status) {
                if (status == 'success') {
                    $scope.schedules = JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.deleteSchedule = function () {
            jQuery.post('/deleteSchedule', data, function (result, status) {
                if (status == 'success') {
                    result=JSON.parse(result);
                    if(result['info']='success')
                        alert('delete success!');
                    else
                        alert('delete failed!')
                } else {
                    alert('network time out!please try again');
                }
            });
        }
    })
    .controller('updateSchedule',function($scope,$id){
       var data={
           id:$id.id
       };
        $scope.mySchedule=[];
        $scope.unselectedSchedule=[];
        $scope.viewSchedule=function(){
            jQuery.post('/getSchedule', data, function (result, status) {
                if (status == 'success') {
                    $scope.mySchedule=JSON.parse(result);
                } else {
                    alert('network time out!please try again');
                }
            });
            jQuery.post('/unselectedSchedule',data,function(result,status){
               if(status=='success'){
                   $scope.unselectedSchedule=JSON.parse(result);
               } else{
                   alert('network time out!please try again');
               }
            });
        };
        var button=$('div#updateSchedule input[type="button"]');
        $(button.context).delegate(button.selector,'click',function(){
            if(this.css('backgroundColor')=='rgb(91, 183, 91)'){
                this.css('backgroundColor','white');
                this.data('select','true');
            }else{
                this.css('backgroundColor','rgb(91, 183, 91)');
                this.data('select','false');
            }
        });
        $scope.updateSchedule=function(){
            var deleteButton=$('div#updateSchedule>table:eq(0)>tr>input');
            var deleteSchedule=[];
            var addSchedule=[];
            for(var i=0;i<deleteButton.length;i++){
                if(deleteButton[i].data('select')=='true')
                    deleteSchedule.push(deleteButton[i].data('id'));
            }
            var addButton=$('div#updateSchedule>table:eq(1)>tr>table');
            for(i=0;i<addButton.length;i++){
                if(addButton[i].data('select')=='true')
                    deleteSchedule.push(deleteButton[i].data('id'));
            }
            var data={
                id:$id.id,
                deleteSchedule:deleteSchedule,
                addSchedule:addSchedule
            };
            jQuery.post('/updateSchedule',data,function(data,status){
               if(status=='success'){
                   alert('update success!');

               }else{
                   alert('network error!please try again');
               }

            });
        }
        
    })
    .controller('selectOfferings',function($scope,$id){
        $scope.allOfferings=[];
        $scope.getOfferings=function(){
           jQuery.post('/allCourses',{id:$id.id},function(result,status){
              if(status=='success'){
                  $scope.allOfferings=JSON.parse(result)
              }else{
                  alert('network error!please try again');
              }

           });
       };
        $scope.chooseCourse=function(){
            var target=$(this);
            if(target.data('select')=='false'){
                target.data('select','true');
                target.css('backgroundColor','white')
            }else{
                target.data('select','false');
                target.css('backgroundColor','rgb(91, 183, 91)');
            }
        };
        $scope.selectOfferings=function(){
            var selectButton=$('div#selectOfferings>table>tr>input');
            var selectCourse=[];
            for(var i=0;i<selectButton.length;i++){
                if(selectButton[i].data('select')=='true')
                    selectCourse.push(selectButton[i].data('id'));
            }
            var data={
                id:$id.id,
                selectCourse:selectCourse
            };
            jQuery.post('/selectOfferings',data,function(result,status){
                if(status=='success'){
                    result=JSON.parse(result);
                    if(result['info']=='success')
                        alert('select success');
                    else
                        alert('select failed');

                }else{
                    alert('network error!please try again');
                }

            });
        }
    })
    .controller('selectTeach',function($scope,$id){
        $scope.allOfferings=[];
        $scope.allCourses=function(){
            jQuery.get('/untaughtCourses',function(result,status){
                if(status=='success'){
                    $scope.allOfferings=JSON.parse(result)
                }else{
                   alert('network error!please try again');
                }
            });
        };
        $scope.chooseCourse=function(){
            var target=$(this);
            if(target.data('select')=='false'){
                target.data('select','true');
                target.css('backgroundColor','white')
            }else{
                target.data('select','false');
                target.css('backgroundColor','rgb(91, 183, 91)');
            }
        };
        $scope.selectTeach=function(){
            var selectButton=$('div#selectTeach>table>tr>input');
            var selectCourse=[];
            for(var i=0;i<selectButton.length;i++){
                if(selectButton[i].data('select')=='true')
                    selectCourse.push(selectButton[i].data('id'));
            }
            var data={
                id:$id.id,
                selectCourse:selectCourse
            };
            jQuery.post('/selectTeach',data,function(result,status){
                if(status=='success'){
                    result=JSON.parse(result);
                    if(result['info']=='success')
                        alert('select success');
                    else
                        alert('select failed');

                }else{
                    alert('network error!please try again');
                }

            });
        }
        
    })
    .controller('submitGrade',function($scope,$id){
        $scope.taughtCourses=[];
        $scope.taughtCourse=function(){
            jQuery.post('/taughtCourse',{id:$id.id},function(data,status){
                if(status=='success'){
                    $scope.taughtCourses=JSON.parse(data);
                }else{
                    alert('network error!please try again');
                }
            });
        };
        $scope.courseId = 0;
        $scope.selectCourse = function (courseId) {
          jQuery.post('/selectCourse',{id:courseId},function(data,status){
            if(status=='success'){
                $scope.studentGrade=JSON.parse(data);

            }else{
                alert('network error!please try again');
            }

          });
        };
        $scope.submitGrade=function(){
            var studentId=[];
            var studentGrade=[];
            for(var id in $('#submitGrade>form>div>input:eq(0)'))
                studentId.push(id.val());
            for(var grade in $('#submitGrade>form>div>input:eq(2)'))
                studentGrade.push(grade.val());
            var data={
                courseId:$scope.courseId,
                studentIds:studentId,
                studentGrades:studentGrade
            };
            jQuery.post('/submitGrade',data,function(data,status){
               if(status=='success'){
                   alert('submit grade success');
               }else{
                   alert('submit grade error!please try again');
               }

            });
        }
    });




