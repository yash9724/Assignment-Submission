console.log("login.js loaded");
var ajaxreq;



function validate(){   
    console.log("inside validate()");
    var username = document.getElementById("username").value;
    var password = document.getElementById("pwd").value;
    var btn = document.getElementById("loginbtn");
    var data_id = btn.getAttribute("data-id");
    console.log(data_id);
    var usertype;
    if(data_id === "userlogin"){
        var usertype = document.getElementById("usertype").value;
    }else if(data_id === "adminlogin"){
        usertype = "ADMIN";
    }
     console.log("in validate(): "+ username +" "+password +" "+usertype);
    if(username === "" || password === ""){
       console.log("if executed");
       document.getElementById("loginresult").innerHTML = "<h5><font color=\"#28a745\">Both fields are mandatory<font></h5>";
       setTimeout(function(){
           document.getElementById("loginresult").innerHTML = "";
       },3000);
       return;
    }
    else{
       document.getElementById("loginresult").innerText = ""; 
       sendrequest(username,password,usertype,data_id);
    }
}

function sendrequest(username,password,usertype,data_id){
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = function(){
        if(ajaxreq.readyState === 4){
            console.log("response: ");
            console.log("processresponse executed");
            var resp = ajaxreq.responseText;
            if(resp.trim() === "invalid"){
                if(data_id === "userlogin"){
                    document.getElementById("loginresult").innerHTML = "<h6>Invalid Credentials. Try again</h6>";
                    return;
                }else if(data_id === "adminlogin"){
                    document.getElementById("loginresult").innerHTML = "<h6><font color=\"white\">Invalid Credentials. Try again</font></h6>";
                    return;
                }
            }else if(resp.trim() === "inactive"){
                window.location.replace("deactivated.html");
                return;
            }else{
                if(data_id === "userlogin"){
                document.getElementById("loginresult").innerHTML = "<h6>Login Successful. Redirecting to profile page.</h6>";
                    console.log("userlogged");
                    if(usertype === "STUDENT"){
                         console.log("student logged");
                    }else if(usertype === "FACULTY"){
                         console.log("faculty logged");
                    }
                }else if(data_id === "adminlogin"){
                    document.getElementById("loginresult").innerHTML = "<h6><font = \"white\">Login Successful. Redirecting to profile page.<font></h6>";
                    window.location.replace("AdminControllerServlet");
                }
            }

        //                  window.location
        //                  similar behavior as an HTTP redirect-
        //                  similar behavior as clicking on a link
        //                  window.location.href = "http://stackoverflow.com";
        //                  window.location.replace(...) is better than using window.location.href, 
        //                  because replace() does not keep the originating page in the session history,
        //                  meaning the user won't get stuck in a never-ending back-button fiasco.

        //                  window.location=responseText;
        //                  window.location is the same as window.location.href,
        //                  in terms of behavior. window.location returns an object.
        //                  If .href is not set, window.location defaults to change the parameter .href.
        //                  Conclude: Use either one is fine.
            }
    };
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

function getTimetable(event){
    var data_id = event.target.parentElement.getAttribute("data-id");
    console.log("inside getTimetable");
    console.log("data-id: "+data_id);
    window.location="FileDownloadServletController?data_id="+data_id+"&filetype=timetable";
 }
 
 function getSyllabus(event){
    var data_id = event.target.parentElement.getAttribute("data-id");
    console.log("inside getSyllabus");
    console.log("data-id: "+data_id);
    window.location="FileDownloadServletController?data_id="+data_id+"&filetype=syllabus";
 }
 
 function getScheme(event){
    var data_id = event.target.parentElement.getAttribute("data-id");
    console.log("inside getScheme");
    console.log("data-id: "+data_id);
    window.location="FileDownloadServletController?data_id="+data_id+"&filetype=scheme";
 }
 
 