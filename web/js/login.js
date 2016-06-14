$(function(){
    var login=$('input[type="button"]');
    var info=$('div.info');
    info.hide();
    login.click(function(){
		
        var id=$('input[id="id"]');
        for(var i=0;i<id.val().length;i++){
        	if('0123456789'.indexOf(id.val()[i])==-1){
        		 info.text('id must be number!');
                        info.show();
                        setTimeout(function(){
                            info.hide(3000);
                        },2000);
                        return;
        	}
        }
        
        var password=$('input[id="password"]');
        var data={
            id:id.val(),
            password:password.val()
        };
        localStorage.setItem('id',id.val());
        jQuery.post('/login',JSON.stringify(data),function(result,status){
           if(status=='success'){
			   login.val('logining...');
		       login.attr('disabled','true');
			   setTimeout(function(){
				   login.val('login');
				   login.attr('disabled','true');
				   
			   },3000);
               result=JSON.parse(result);
               if(result['info']=='success'){
                   localStorage.setItem('identity',result['identity']);
                   localStorage.setItem('name',result['name']);
                   localStorage.setItem('semester',result['semester']);
                   location.replace('./index.html');
               }else{
                   if(result['error']=='username not exist'){
                        info.text(result['error']);
                        info.show();
                        setTimeout(function(){
                            info.hide(3000);
                        },2000);
                   }else if(result['error']=='password not match'){
                        info.text(result['error']);
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