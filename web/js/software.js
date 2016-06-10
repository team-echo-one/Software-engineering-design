/**
 * Created by lenovo on 2016/6/4.
 */

angular.module('software', [])
    .constant('$id', {
        id: localStorage.getItem('id'),
        semester: localStorage.getItem('semester')
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
            jQuery.post('/addStudent', JSON.stringify(data), function (result, status) {
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
                    $scope.$digest();
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
            jQuery.post('/updateStudent', JSON.stringify(data), function (result, status) {
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
                    $scope.$digest();
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
                jQuery.post('/deleteStudent', JSON.stringify(data), function (result, status) {
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

            jQuery.post('/addProfessor', JSON.stringify(data), function (result, status) {
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
        $scope.getProfessors = function () {
            jQuery.get('/getProfessors', function (result, status) {
                if (status == 'success') {
                    $scope.professors = JSON.parse(result);
                    $scope.$digest();
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
            var data = {
                id: $scope.professorId,
                name: $scope.name,
                birthday: $scope.birthday,
                SSN: $scope.SSN,
                status: $scope.status,
                department: $scope.department
            };
            jQuery.post('/updateProfessor', JSON.stringify(data), function (result, status) {
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
                    $scope.$digest();
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
                jQuery.post('/deleteProfessor', JSON.stringify(data), function (result, status) {
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
    .controller('viewReport', function ($scope, $id) {
        $scope.courses = [];
        $scope.semester = 1;
        $scope.viewReport = function () {
            var data = {
                id: $id.id,
                semester: $scope.semester
            };
            jQuery.post('/viewReport', JSON.stringify(data), function (result, status) {
                if (status == 'success') {
                    $scope.courses = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network time out!please try again');
                }
            });
        };

    })
    .controller('addCourse', function ($scope, $id) {
        var data = {
            id: $id.id
        };
        $scope.allCourse = [];
        $scope.selectedCourse = [];
        $scope.viewSchedule = function () {
            jQuery.post('/getCourses', JSON.stringify(data), function (result, status) {
                if (status == 'success') {
                console.log(result);
                    $scope.selectedCourse = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network time out!please try again');
                }
            });
            jQuery.get('/allCourses', function (result, status) {
            console.log(result);
                if (status == 'success') {
                    $scope.allCourse = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.addCourse = function (id) {
            for (var i = 0; i < $scope.selectedCourse.length; i++) {
                var course = $scope.selectedCourse[i];
                if (course['id'] == id) {
                    alert('you had selected this course!');
                    return;
                }
            }
            jQuery.post('/addCourse', JSON.stringify({id: $id.id, courseId: id}), function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('add success!');
                    } else {
                        alert('add failed!');
                    }
                } else {
                    alert('network error!');
                }
            });
        }
    })
    .controller('deleteCourse', function ($scope, $id) {
        var data = {
            id: $id.id
        };
        $scope.selectedCourse = [];
        $scope.viewSchedule = function () {
            jQuery.post('/getCourses', JSON.stringify(data), function (result, status) {
                if (status == 'success') {
                    $scope.selectedCourse = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network time out!please try again');
                }
            });
        };
        $scope.deleteCourse = function (id) {
            jQuery.post('/deleteCourse', JSON.stringify({id: $id.id, courseId: id}), function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('delete success!');
                    } else {
                        alert('delete failed!');
                    }
                } else {
                    alert('network error!');
                }
            });
        }
    })
    .controller('selectTeach', function ($scope, $id) {
        var data = {
            id: $id.id
        };
        $scope.viewCourse = function () {
            jQuery.get('/allTaughtCourses', function (result, status) {
                if (status == 'success') {
                    $scope.allCourse = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network error!');
                }
            });
            jQuery.post('/myTaughtCourses', JSON.stringify(data), function (result, status) {
                if (status == 'success') {
                console.log(result);
                    $scope.taughtCourse = JSON.parse(result);
                    $scope.$digest();
                } else {
                    alert('network error');
                }
            })
        };
        $scope.addCourse = function (id) {
        
            var info={};
            var divS=$('div#selectTeach>div:eq(0)>div');
            for(var i=0;i<divS.length;i++){
                var item=$(divS[i]);
                if(item.find('div:eq(0)').text()==id){
                    info['professorId']=$id.id;
                    info['courseId']=id;
                    info['day']=1;
                    info['begin']=2;
                    info['end']=3;
                    info['capacity']=item.find('input:eq(2)').val();
                    break;
                }
            }
            console.log(JSON.stringify(info));
            jQuery.post('/addTaughtCourse', JSON.stringify(info),function (result, status) {
                if (status == 'success') {
                    result = JSON.parse(result);
                    if (result['info'] == 'success') {
                        alert('add course success');
                    } else {
                        alert('add course failed!');
                    }
                } else {
                    alert('network error');
                }
            })
        }
    })
    .controller('submitGrade', function ($scope, $id) {
        $scope.taughtCourses = [];
        $scope.taughtCourse = function () {
            jQuery.post('/taughtCourse', JSON.stringify({id: $id.id}), function (data, status) {
                if (status == 'success') {
                    $scope.taughtCourses = JSON.parse(data);
                } else {
                    alert('network error!please try again');
                }
            });
        };
        $scope.courseId = 0;
        $scope.selectCourse = function (courseId) {
            jQuery.post('/selectCourse', JSON.stringify({id: courseId}), function (data, status) {
                if (status == 'success') {
                    $scope.studentGrade = JSON.parse(data);

                } else {
                    alert('network error!please try again');
                }

            });
        };
        $scope.submitGrade = function () {
            var studentId = [];
            var studentGrade = [];
            for (var id in $('#submitGrade>form>div>input:eq(0)'))
                studentId.push(id.val());
            for (var grade in $('#submitGrade>form>div>input:eq(2)'))
                studentGrade.push(grade.val());
            var data = {
                courseId: $scope.courseId,
                studentIds: studentId,
                studentGrades: studentGrade
            };
            jQuery.post('/submitGrade', JSON.stringify(data), function (data, status) {
                if (status == 'success') {
                    alert('submit grade success');
                } else {
                    alert('submit grade error!please try again');
                }

            });
        }
    });




