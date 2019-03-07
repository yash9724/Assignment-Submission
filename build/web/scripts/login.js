
var ajaxReq=new XMLHttpRequest();
var username,password,usertype;
var url;
$(document).ready(function()
{
    $("#loginbtn").click(function()
    {
        connect();
    });
});
function validate()
{
    username=$("#username").val();
    password=$("#password").val();
    usertype=$("#usertype").val();
    var status=true;
    if(username==="")
    {
        
        $("#username").after("<span id='error1'>Username Reqd!</span>");
        $("#error1").css("color","red");
        status= false;
    }
    if(password==="")
    {
        
        $("#password").after("<span id='error2'>Password Reqd!</span>");
        $("#error2").css("color","red");
        status= false;
    }
    $("#error1").fadeOut(4000);
    $("#error2").fadeOut(4000);
    return status;
}
function connect()
{
    //alert("connect called");
    if(!validate())
    {
       return ;
    }
   
var mydata={username:username,password:password,usertype:usertype};
var request=$.post("logincontrol",mydata,processresponse);
request.error(handleError);
}
function processresponse(responseText)
{
    
    
        var resp=responseText;
        var pagename;
        resp=resp.trim();
        if(resp.indexOf("jsessionid")!==-1)
        {
            //alert("inside success");
            $("#loginresult").css("color","green");
            if(usertype==="ADMIN")
                pagename="Options";
            else
                pagename="Store";
            $("#loginresult").html("Login Accepted!Redirecting to the "+pagename+" Page!");
            url=resp;
            setTimeout(redirectuser,3000);
        }
        else if(resp==="error")
        {
            //alert("inside error");
            $("#loginresult").css("color","red");
            $("#loginresult").html("Login Rejected");
        }
        else
        {
            //alert("inside else:"+resp);
            $("#loginresult").css("color","red");
            $("#loginresult").html("Some error occurred at the server. Try later");
            
        }
    }
    function handleError(xhr,textStatus)
    {
    
    if(textStatus==='error'){
        $("#loginresult").html("Error is "+xhr.status);
    }
}

function redirectuser()
{
  window.location = url;  
}
