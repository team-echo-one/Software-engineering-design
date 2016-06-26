/**
 * Created by lenovo on 2016/6/4.
 */

angular.module('software', [])
    .constant('$id', {
        id: localStorage.getItem('id'),
        semester: localStorage.getItem('semester')
    })
    .factory('$message', function () {
        return {
            show: function (info) {
                var foreground = $('.foreground');
                var message1 = $('.message1');
                var message2 = $('.message2');
                var index = 2;
                foreground.show();
                message1.text(info);
                message2.html('<p>The window will close in<h4 style="color:rgb(60,163,193);">' + (index--) + ' second</h4></p>');
                var interval = setInterval(function () {
                    message2.html('<p>The window will close in<h4 style="color:rgb(60,163,193);">' + (index) + ' second</h4></p>');
                    if (index == 0) {
                        clearInterval(interval);
                        message1.text('');
                        message2.html('');
                        foreground.hide();
                    }
                    index--;
                }, 1000)

            }
        }
    })
    .controller('addStudent', function ($scope, $message, $http) {
        $scope.submit = function ($event) {
            var target = $($event.target);
            target.val('adding');
            target.attr('disabled', 'true');
            var data = {
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                graduationDate: $scope.graduationDate
            };
            $http.post('/addStudent', JSON.stringify(data))
                .success(function (result) {
                    target.val('add');
                    target.removeAttr('disabled');
                    if (result['info'] == 'success') {
                        $message.show('add success!');
                    } else {
                        $message.show('add failed!');
                    }
                })
                .error(function () {
                    target.val('add');
                    target.removeAttr('disabled');
                    $message('network error!');
                })
        }
    })
    .controller('updateStudent', function ($scope, $http, $message) {
        $scope.students = [];
        $scope.getStudents = function () {
            $http.get('/getStudents')
                .success(function (result) {
                    $scope.students = result;
                })
                .error(function () {
                    $message.show('network error!');
                });
        };
        $scope.studentId = 0;
        $scope.selectStudent = function (studentId) {
       
            for (var i = 0; i < $scope.students.length; i++) {
                var student = $scope.students[i];
                if (student['id'] == studentId) {
                    $scope.name = student['name'];
                    $scope.birthday = student['birthday'];
                    $scope.SSN = student['SSN'];
                    $scope.status = student['status'];
                    $scope.graduationDate = student['graduationDate'];
                }
            }
        };
        $scope.submit = function ($event) {
            var target = $($event.target);
            target.val('updating');
            target.attr('disabled', 'true');
            var data = {
                id: $scope.studentId,
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                graduationDate: $scope.graduationDate
            };
            $http.post('/updateStudent', JSON.stringify(data))
                .success(function (result) {
                    target.val('update');
                    target.removeAttr('disabled');
                    if (result['info'] == 'success') {
                        $message.show('update success!');
                    } else {
                        $message.show('update failed!');
                    }
                })
                .error(function () {
                    target.val('update');
                    target.removeAttr('disabled');
                    $message.show('network error!');
                });
        }

    })
    .controller('deleteStudent', function ($scope, $http, $message) {
        $scope.students = [];
        $scope.getStudents = function () {
            $http.get('/getStudents')
                .success(function (result) {
                    $scope.students = result;
                })
                .error(function () {
                    $message.show('network error!');
                })
        };
        $scope.studentId = 0;
        $scope.selectStudent = function (studentId) {
            for (var i = 0; i < $scope.students.length; i++) {
                var student = $scope.students[i];
                if (student['id'] ==studentId) {
                    $scope.name = student['name'];
                    $scope.birthday = student['birthday'];
                    $scope.SSN = student['SSN'];
                    $scope.status = student['status'];
                    $scope.graduationDate = student['graduationDate'];
                }
            }
        };
        $scope.submit = function ($event) {
            var verify = confirm("submit delete?");
            if (verify) {
                var target = $($event.target);
                target.val('deleting');
                target.attr('disabled', 'true');
                var data = {
                    id: $scope.studentId
                };
                $http.post('/deleteStudent', JSON.stringify(data))
                    .success(function (result) {
                        target.val('delete');
                        target.removeAttr('disabled');
                        if (result['info'] == 'success') {
                            $message.show('delete success!');
                        } else {
                            $message.show('delete failed!');
                        }
                    })
                    .error(function () {
                        target.val('delete');
                        target.removeAttr('disabled');
                        $message.show('network error!');
                    })
            }
        }

    })
    .controller('addProfessor', function ($scope, $http, $message) {
        $scope.submit = function ($event) {
            var target = $($event.target);
            target.val('adding');
            target.attr('disabled', 'true');
            var data = {
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                department: $scope.department
            };

            $http.post('/addProfessor', JSON.stringify(data))
                .success(function (result) {
                    target.val('add');
                    target.removeAttr('disabled');
                    if (result['info'] == 'success') {
                        $message.show('add success!');
                    } else {
                        $message.show('add failed!');
                    }
                }).error(function () {
                target.val('add');
                target.removeAttr('disabled');
                $message.show('network error!');
            })
        }
    })
    .controller('updateProfessor', function ($scope, $http, $message) {
        $scope.getProfessors = function () {
            $http.get('/getProfessors')
                .success(function (result) {
                    $scope.professors=result;
                })
                .error(function () {
                    $message.show('network error!');
                });
        };
        $scope.selectProfessor = function (professorId) {
            for (var i = 0; i < $scope.professors.length; i++) {
                var professor = $scope.professors[i];
                if (professor['id'] == professorId) {
                	 $scope.name = professor['name'];
                    $scope.birthday = professor['birthday'];
                    $scope.SSN = professor['SSN'];
                    $scope.status = professor['status'];
                    $scope.department = professor['department'];
                }
            }
        };
        $scope.submit = function ($event) {
            var target = $($event.target);
            target.val('updating');
            target.attr('disabled', 'true');
            var data = {
                id: $scope.professorId,
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                department: $scope.department
            };
            $http.post('/updateProfessor', JSON.stringify(data))
                .success(function (result) {
                    target.val('update');
                    target.removeAttr('disabled');
                    if (result['info'] == 'success') {
                        $message.show('update success!');
                    } else {
                        $message.show('update failed!');
                    }

                })
                .error(function () {
                    target.val('update');
                    target.removeAttr('disabled');
                    $message.show('network error!');
                });
        }

    })
    .controller('deleteProfessor', function ($scope, $http, $message) {
        $scope.professors = [];
        $scope.getProfessors = function () {
            $http.get('/getProfessors')
                .success(function (result) {
                    $scope.professors = result;
                })
                .error(function () {
                    $message.show('network error!');
                })
        };
        $scope.professorId = 0;
        $scope.selectProfessor = function (professorId) {
            for (var i = 0; i < $scope.professors.length; i++) {
                var professor = $scope.professors[i];
                if (professor['id'] == professorId) {
                    $scope.name = professor['name'];
                    $scope.birthday = professor['birthday'];
                    $scope.SSN = professor['SSN'];
                    $scope.status = professor['status'];
                    $scope.department = professor['department'];
                }
            }
        };
        $scope.submit = function ($event) {
            var verify = confirm("submit delete?");
            if (verify) {
                var target = $($event.target);
                target.val('deleting');
                target.attr('disabled', 'true');
                var data = {
                    id: $scope.professorId
                };
                $http.post('/deleteProfessor', JSON.stringify(data))
                    .success(function (result) {
                        target.val('delete');
                        target.removeAttr('disabled');
                        if (result['info'] == 'success') {
                            $message.show('delete success!');
                        } else {
                            $message.show('delete failed!');
                        }

                    })
                    .error(function () {
                        target.val('delete');
                        target.removeAttr('disabled');
                        $message.show('network error!');
                    });
            }
        }
    })
    .controller('endRegistrar',function($scope,$http,$id,$message){
        $scope.submit=function ($event) {
            var target=$($event.target);
            var data={
                id:$scope.id,
                password:$scope.password
            };
            if(!data.id||!data.password){
            	return;
            }
            var id=$('div#endRegistrar input:nth-child(1)');
            var password=$('div#endRegistrar input:nth-child(2)');
           
            $http.post('/endRegistrar',JSON.stringify(data))
                .success(function(result){
                    if(result['info']=='success'){
                     id.val('');
            		password.val('');
                    var endRegistrar=localStorage.getItem('endRegistrar');
                    if(endRegistrar=='over'){
                    	localStorage.removeItem('endRegistrar');
                    	localStorage.setItem('endRegistrar','ok');
                    	target.removeClass('btn-info');
                    	target.addClass('btn-success');
                    	target.val('Close Registrar');
                    }else{
                       localStorage.removeItem('endRegistrar');
                    	localStorage.setItem('endRegistrar','over');
                    	target.removeClass('btn-success');
                    	target.addClass('btn-info');
                    	target.val('Open Registrar');
                    }
                        $message.show('end registrar success!');
                        
                    }else{
                        $message.show('end registrar failed!');
                    }
                })
                .error(function(){
                   id.val('');
            		password.val('');
                    $message.show('network error!');
                })
        }

    })
    .controller('viewReport', function ($scope, $http, $id, $message) {
        $scope.courses = [];
        $scope.semester = 1;
        $scope.viewReport = function ($event) {
            var target = $($event.target);
            target.val('View Reporting');
            target.attr('disabled', 'true');
            var data = {
                id: $id.id,
                semester: $scope.semester
            };

            $http.post('/viewReport', data)
                .success(function (result) {
                target.val('View Report');
                    target.removeAttr('disabled');
                	if(result['info']=="this student doesn't have history course"){
                		$message.show("this student doesn't have history course");
                		return;
                	}
                   result.unshift({"courseId":'ID',"name":"courseName","teacher":"professor",'grade':'grade'});
                    $scope.courses = result;
                })
                .error(function () {
                    target.val('View Report');
                    target.removeAttr('disabled');
                    $message.show('network error!');
                });

        };

    })
    .controller('addCourse', function ($scope, $http, $message, $id) {
        var data = {
            id: $id.id
        };
        $scope.allCourse = [];
        $scope.selectedCourse = [];
        $scope.viewSchedule = function () {
            $http.post('/getCourses', JSON.stringify(data))
                .success(function (result) {
                    $scope.selectedCourse = result;
                })
                .error(function () {
                    $message.show('network error!');
                });
            $http.get('/allCourses')
                .success(function (result) {
                    $scope.allCourse = result;
                })
                .error(function () {
                    $message.show('network error!');
                });
        };
        $scope.addCourse = function (course, $event) {
            var choose = confirm('add this course?');
            if (choose) {
                for (var i = 0; i < $scope.selectedCourse.length; i++) {
                    var course = $scope.selectedCourse[i];
                    if (course['id'] ==course.id) {
                        $message.show('you had selected this course!');
                        return;
                    }
                }
                var target = $($event.target);

                $http.post('/addCourse', JSON.stringify({id: $id.id, courseId: course.id,professorId:course.professorId}))
                    .success(function (result) {
                        if (result['info'] == 'success') {
                            $message.show('add success!');
                            target.hide();
                        } else {
                            $message.show('add failed!');
                        }

                    })
                    .error(function () {
                        $message.show('network error!');
                    })
            }
        }
    })
    .controller('deleteCourse', function ($scope, $http, $message, $id) {
        var data = {
            id: $id.id
        };
        $scope.selectedCourse = [];
        $scope.viewSchedule = function () {
            $http.post('/getCourses', JSON.stringify(data))
                .success(function (result) {
                    $scope.selectedCourse = result;
                })
                .error(function () {
                    $message.show('network error!');
                })
        };
        $scope.deleteCourse = function (id, $event) {
            var choose = confirm('delete this course?');
            if (choose) {
                var parent = $($event.target).parent();
                $http.post('/deleteCourse', JSON.stringify({id: $id.id, courseId: id}))
                    .success(function (result) {
                        if (result['info'] == 'success') {
                            parent.hide();
                            $message.show('delete success!');
                        } else {
                            $message.show('delete failed!');
                        }

                    })
                    .error(function () {
                        $message.show('network error!');
                    });
            }
        }
    })
    .controller('selectTeach', function ($scope, $http, $message, $id) {
        var data = {
            id: $id.id
        };
        var title=$('div#selectTeach>h3');
        title.hide();
        $scope.viewCourse = function () {
            title.show();
            $http.get('/allTaughtCourses')
                .success(function (result) {
                	if(result.length==0){
                		$message.show('All course is empty! ');
                	}
                    $scope.allCourse = result;
                })
                .error(function () {
                    $message.show('network error!');
                });
            $http.post('/myTaughtCourses', JSON.stringify(data))
                .success(function (result) {
                   result.unshift({'id':'id','name':'name','day':'day','begin':'begin','end':'end','capacity':'capacity'});
                    $scope.taughtCourse = result;
                })
                .error(function () {
                    $message.show('network error!');
                })
        };
        $scope.addCourse = function (course,$event ){
            var target=$($event.target);
            target.val('selecting');

            var choice=confirm("you want to choose this course?");
            if(choice) {
                for(var i=0;i<$scope.taughtCourse;i++){
                    if(course.id==$scope.taughtCourse[i].id){}
                    $message.show('you have chose this course!');
                    return;
                }
                if($scope&&$scope.length>=1){
                var days=[],begins=[],ends=[];
                for( i=0;i<$scope.allCourse.length;i++){
                    days.push($scope.taughtCourse[i].day);
                    begins.push($scope.taughtCourse[i].begin);
                    ends.push($scope.taugthCourse[i].end);
                }
                for(i=0;i<days.length;i++){
                    if(course.day==days[i]){
                        if((course.begin>=begins[i]&&course.end<=ends[i])||(course.end>=begins[i]&&course.end<=ends[i])){
                            $message.show("time conflict!");
                            return ;
                        }
                    }
                }
                }
                var day=parseInt(course.day);
                var begin=parseInt(course.begin);
                var end=parseInt(course.end);
                if(day!=1&&day!=2&&day!=3&&day!=4&&day!=5&&day!=6&&day!=7){
                	$message.show('the day you choosed is wrong');
                	return;
                }
                if(begin!=1&&begin!=2&&begin!=3&&begin!=4&&begin!=5&&begin!=6&&begin!=7&&begin!=8&&begin!=9&&begin!=10&&begin!=11){
                	$message.show('the begin you choosed is wrong');
                	return;
                }
                if(begin!=1&&begin!=2&&begin!=3&&begin!=4&&begin!=5&&begin!=6&&begin!=7&&begin!=8&&begin!=9&&begin!=10&&begin!=11){
                	$messgae.show('the end you choosed is wrong');
                	return;
                }
                if(begin>=end){
                	$message.show('the end must larger than begin');
                	return;
                }
                
                var info={
                    professorId:$id.id,
                    courseId:course.id,
                    day:course.day,
                    begin:course.begin,
                    end:course.end,
                    capacity:course.capacity
                };
                $http.post('/addTaughtCourse', JSON.stringify(info))
                    .success(function (result) {
                        if (result['info'] == 'success') {
                            target.attr('disabled', 'true');
                            target.val('selected');
                            $message.show('add success!');
                            console.log(JSON.stringify(info));
                        } else {
                            $message.show('add failed!');
                        }
                    })
                    .error(function () {
                        $message.show('network error!');
                    })
            }
        }
    })
    .controller('submitGrade', function ($scope, $http, $message, $id) {
        $scope.taughtCourses = [];
        $scope.taughtCourse = function () {
            $http.post('/myTaughtCourses', JSON.stringify({id: $id.id}))
                .success(function (data) {
                console.log(data);
                   if(data.length==0){
                   		$message.show('you have not chosen any courses!');
                   		return;
                   }
                    $scope.taughtCourses = data;
                })
                .error(function () {
                    $message.show('network error!');
                });
        };
        $scope.courseId = 0;
        $scope.selectCourse = function () {
            $http.post('/getCourseStudents',
            JSON.stringify({courseId:$scope.courseId,professorId:$id.id}))
                .success(function (data) {
                    $scope.studentGrade = data;
                })
                .error(function () {
                    $http.show('network error!');
                });
        };
        $scope.submitGrade = function () {
            var studentId = [];
            var studentGrade = [];
            var ids=$('div#submitGrade>div>input:nth-child(1)');
            var grades=$('div#submitGrade>div>input:nth-child(3)');
            for(var i=0;i<ids.length;i++){
                studentId.push($(ids[i]).val());
                studentGrade.push($(grades[i]).val());
            }
            var data = {
                professorId:$id.id,
                courseId: $scope.courseId,
                studentIds: studentId,
                studentGrades: studentGrade
            };
            $http.post('/submitGrade', JSON.stringify(data))
                .success(function (data) {
                    if (data['info']== 'success')
                        $message.show('submit success!');
                    else
                        $message.show('submit failed!');
                })
                .error(function () {
                    $message.show('network error!');
                })
        };
    })
    .controller('inform', function ($scope, $http, $message, $id,$sce) {

        $scope.getInform = function ($event) {
            $event.stopPropagation();
            var informContent = $('.informContent');

            $http.post('/getInform', JSON.stringify({'id': $id.id}))
                .success(function (data) {
                    if (data) {
                        informContent.show();
                       for(var i=0;i<data.length;i++){
                       		var info=data[i].content;
                       		info=info.split('\n');
                       		info=info.join('<br>');
                       		console.log(info);
                       		info=info.split('\t');
  
                       		var head=info.shift();
                       		head=head+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                       		info=info.join('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
                       		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
                       		info=head+info;
                    	    data[i].content=$sce.trustAsHtml(info);
                       }
                        $scope.contents = data;
                    }
                })
                .error(function () {
                    $message.show('network error!');
                })
        }

    });




