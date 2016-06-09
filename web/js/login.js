$(function(){
    var login=$('input[type="button"]');
    var info=$('div.info');
    info.hide();
    login.click(function(){

        var id=$('input[id="id"]');
        var password=$('input[id="password"]');
        var data={
            id:id.val(),
            password:password.val()
        };
        localStorage.setItem('id',id.val());
        jQuery.post('/login',JSON.stringify(data),function(result,status){
           if(status=='success'){
               result=JSON.parse(result);
               if(result['info']=='success'){
                   localStorage.setItem('identity',result['identity']);
                   localStorage.setItem('name',result['name']);
                   location.replace('./index.html');
               }else{
                   if(result['error']=='username not exist'){
                        info.text('username not exist!');
                        info.show();
                        setTimeout(function(){
                            info.hide(3000);
                        },2000);
                   }else if(result['error']=='password not match'){
                        info.text('password not exist!');
                        info.show();
                        setTimeout(function(){
                            info.hide(3000);
                        },2000);
                   }
               }
           } else{
               alert('network time out!please try again');
           }
        });
    });
});