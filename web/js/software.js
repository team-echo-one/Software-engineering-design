/**
 * Created by lenovo on 2016/6/4.
 */

    angular.module('software',[])
        .controller('addStudent',function($scope){
            $scope.submit=function(){
                var data={
                    name:$scope.name,
                    birthday:$scope.birthday,
                    SSN:$scope.SSN,
                    status:$scope.status,
                    graduationDate:$scope.graduationDate
                };
                jQuery.post('/addStudent',data,function(result,status){
                    if(status=='success'){
                        result=JSON.parse(result);
                        if(result['info']=='success'){
                            alert('add success!');
                        }else{
                            alert('add failed!please try again!');
                        }
                    } else{
                        alert('network time out!please try again');
                    }

                });
            }
        })
        .controller('updateStudent',function($scope){
            $scope.students=[];
            jQuery.get('/studentInfo',function(result,status){
                if(status=='success'){
                    $scope.students=JSON.parse(result);
                } else{
                    alert('network time out!please try again');
                }
            });
            $scope.studentId=0;
            $scope.selectStudent=function(studentId){
                for(var i=0;i<$scope.students.length;i++){
                    var student=$scope.students[i];
                    if(student['id']=studentId){
                        $scope.name=student['name'];
                        $scope.birthday=student['birthday'];
                        $scope.SSN=student['SSN'];
                        $scope.status=student['status'];
                        $scope.graduationDate=student['graduationDate'];
                    }
                }
            };
            $scope.submit=function(){
                var data={
                    name:$scope.name,
                    birthday:$scope.birthday,
                    SSN:$scope.SSN,
                    status:$scope.status,
                    graduationDate:$scope.graduationDate
                };
                jQuery.post('/updateStudent',data,function(result,status){
                    if(status=='success'){
                        result=JSON.parse(result);
                        if(result['info']=='success'){
                            alert('update success!');
                        }else{
                            alert('update failed!please try again!');
                        }
                    } else{
                        alert('network time out!please try again');
                    }

                });
            }
        });
