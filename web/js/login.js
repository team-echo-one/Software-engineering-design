$(function(){
    var login=$('input[type="button"]');
    localStorage.setItem('identity','student');
    login.click(function(){
        var username=$('input[id="username"]');
        var password=$('input[id="password"]');
        var data={
           'username':username.val(),
            'password':password.val()
        };
        jQuery.post('/login',JSON.stringify(data),function(result,status){
           if(status=='success'){
               result=JSON.parse(result);
               if(result['info']=='success'){
                   /* identity saved here*/
                   localStorage.setItem('id',result['id']);
                   location.replace('./index.html');
               }else{
                   if(result['error']=='username not exist'){
                       alert('user not exist!please try again');
                   }else if(result['error']=='password not match'){
                       alert('password not match!please try again');
                   }
               }
           } else{
               alert('network time out!please try again');
           }
        });
    });
});