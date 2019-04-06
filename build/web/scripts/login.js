console.log("login.js loaded");
var ajaxreq;
function validate(){     
     var username = document.getElementById("username").value;
     var password = document.getElementById("pwd").value;
     var usertype = document.getElementById("usertype").value;
     console.log("in validate(): "+ username +" "+password +" "+usertype);
     if(username == "" || password == "" ){
        console.log("if executed");
        document.getElementById("loginresult").innerHTML = "<h6>Both fields are mandatory</h6><br>";
        return;
     }
    else{
       document.getElementById("loginresult").innerText = ""; 
       sendrequest(username,password,usertype);
    }
}

function sendrequest(username,password,usertype){
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = processresponse;
    ajaxreq.open("POST","LoginControllerServlet",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    credentials = {
        "username":username,
        "password":password,
        "usertype":usertype
    };
    json_data = JSON.stringify(credentials);
    console.log(json_data);
    ajaxreq.send("json_data="+json_data);
}

function processresponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "invalid"){
            document.getElementById("loginresult").innerHTML = "<h6>Invalid Credentials. Try again</h6>";
            return;
        }
        document.getElementById("loginresult").innerHTML = "<h6>Login Successful. Redirecting to profile page.</h6>";
//                  window.location
//                  similar behavior as an HTTP redirect-
         window.location.replace("AdminControllerServlet");

//                  similar behavior as clicking on a link
//                  window.location.href = "http://stackoverflow.com";
//                    
//                  window.location.replace(...) is better than using window.location.href, 
//                  because replace() does not keep the originating page in the session history,
//                  meaning the user won't get stuck in a never-ending back-button fiasco.

//                  window.location=responseText;
//                  window.location is the same as window.location.href,
//                  in terms of behavior. window.location returns an object.
//                  If .href is not set, window.location defaults to change the parameter .href.
//                  Conclude: Use either one is fine.
    }
}
