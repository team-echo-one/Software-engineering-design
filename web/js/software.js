/**
 * Created by lenovo on 2016/6/4.
 */

angular.module('software', [])
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
            jQuery.get('/students', function (result, status) {
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
            jQuery.get('/students', function (result, status) {
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
                    studentId: $scope.studentId
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
    .controller('addProfessor',function($scope){
       $scope.submit=function(){
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
            jQuery.get('/professors', function (result, status) {
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
            jQuery.get('/professors', function (result, status) {
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
                if (professor['id'] = studentId) {
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
                    professorId: $scope.professorId
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



