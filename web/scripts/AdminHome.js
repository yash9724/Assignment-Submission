console.log("AdminHome.js loaded");
var adminName = document.getElementById("name").value;
var email = document.getElementById("email").value;
var address = document.getElementById("add").value;
var contact = document.getElementById("contact").value;

console.log(adminName+' '+email+' '+address+' '+contact );

var ajaxreq;
function saveAdminDetails(){
    var newAdminName = document.getElementById("name").value;
    var newEmail = document.getElementById("email").value;
    var newAddress = document.getElementById("add").value;
    var newContact = document.getElementById("contact").value;
    
    if(newAdminName === '' || newEmail === '' || newAddress === '' || newContact === ''){
        return;
    }
    
    console.log("New Admin Details: "+newAdminName+' '+newEmail+' '+newAddress+' '+newContact);
//    if(newAdminName === adminName && newEmail === email && newContact === contact && newAddress === address){
//        console.log("No changes detected in admin profile");
//        return;
//    }else{
//        adminName = newAdminName;
//        email = newEmail;
//        address = newAddress;
//        contact = newContact;
        
        ajaxreq = new XMLHttpRequest();
        ajaxreq.onreadystatechange = saveAdminDetailsProcessResponse;
        ajaxreq.open("POST","SaveAdminDetailsController",true);
        ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
        ajaxreq.send("adminName="+newAdminName+"&email="+newEmail+"&address="+newAddress+"&contact="+newContact);
        console.log("After request for saving admin details has been sent");
//    }
}

function saveAdminDetailsProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "notsaved"){
            document.getElementById("saveresult").innerHTML = "<h5><font color=\"red\">Error in saving changes. Try again later.</font></h5>";
        }else{
            document.getElementById("saveresult").innerHTML = "<h5><font color=\"green\">Changes Saved Successfully.</font></h5>";
        }
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("saveresult").innerHTML = "";
        },3000);
    }
}

//---------------------------------------------------Change Admin Password------------------------------------------------------

function changeAdminPassword(){
    var oldPass = document.getElementById("oldPass").value;
    var newPass = document.getElementById("newPass").value;
    var confPass = document.getElementById("confPass").value;
    
    if(oldPass === '' || newPass === '' || confPass === ''){
        return;
    }
    
    if(newPass !== confPass){
        document.getElementById("passcngresult").innerHTML = "<h5><font color=\"red\">Both New Password and Confirm Password fields must be same.</font></h5>";
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("saveresult").innerHTML = "";
        },2000);
        return;
    }
    
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = changeAdminPasswordProcessResponse;
    ajaxreq.open("POST","ChangeAdminPasswordServlet",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    password = {
        "oldPass":oldPass,
        "newPass":newPass
    };
    json_data = JSON.stringify(password);
    console.log(json_data);
    ajaxreq.send("json_data="+json_data);
    console.log("After request for password change has been sent");
}

function changeAdminPasswordProcessResponse(){
    if(ajaxreq.readyState === 4){
        var resp = ajaxreq.responseText;
        if(resp.trim() === "success"){
            document.getElementById("passcngresult").innerHTML = "<h5><font color=\"green\">Password Changed Successfully.</font></h5>";; 
        }else{
            document.getElementById("passcngresult").innerHTML = "<h5><font color=\"red\">Error in changing password. Fill all fields carefully.If problem persists, try after sometime.</font></h5>";
        } 
        setTimeout(function(){
            document.getElementById("saveresult").innerHTML = "";
            document.getElementById("oldPass").value = "";
            document.getElementById("newPass").value = "";
            document.getElementById("confPass").value = ""; 
        },2000);
    }
}

//--------------------------------------------------Send Email--------------------------------------------------------

function sendEmail(){
    console.log("inside sendEmail");
    var to = document.getElementById("to").value;
    var from = document.getElementById("from").value;
    var subject = document.getElementById("subject").value;
    var password = document.getElementById("password").value;
    var message = CKEDITOR.instances.editor1.getData();
    
    if(to === '' || from === '' || subject === '' || password === '' || message === ''){
        return;
    }
    message = stripHtml(message);
    console.log(message);
    
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = sendEmailProcessResponse;
    ajaxreq.open("POST","EmailControllerServlet",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    
    email={
      "to":to,
      "from":from,
      "subject":subject,
      "message":message,
      "password":password
    };
    
    json_data = JSON.stringify(email);
    console.log(json_data);
    ajaxreq.send("json_data="+json_data);
    console.log("After request email has been sent");
}

function sendEmailProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("inside sendEmailProcessResponse");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "success"){
            document.getElementById("emailresult").innerHTML = "<h5><font color=\"green\">Email Sent Successfully.</font></h5>";; 
        }else{
            document.getElementById("emailresult").innerHTML = "<h5><font color=\"red\">Error in Sending Email.Try Again Later.</font></h5>";
        } 
        setTimeout(function(){
            document.getElementById("emailresult").innerHTML = "";
        },10000);
    }
}

//-----------------------------------------Strip Html: Will strip text of all html tags-----------------------------------
function stripHtml(html){
    var temporalDivElement = document.createElement("div");
    temporalDivElement.innerHTML = html;
    return temporalDivElement.textContent || temporalDivElement.innerText || "";
}

//-------------------------------------------Save faculty details---------------------------------------------------------

function saveFacultyDetails(){
    var facultyName = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var address = document.getElementById("add").value;
    var contact = document.getElementById("contact").value;
    //var photoPath = document.getElementById("usrPhoto").value;
    
    if(facultyName === '' || email === '' || address === '' || 
       contact === ''){
            return;
    }
    
    console.log("New faculty Details: "+facultyName+' '+email+' '+address+' '+contact);
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = saveFacultyDetailsProcessResponse;
    ajaxreq.open("POST","SaveFacultyDetailsController",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    ajaxreq.send("facultyName="+facultyName+"&email="+email+"&address="+address+"&contact="+contact);
    console.log("After request for saving faculty details has been sent");

}

function saveFacultyDetailsProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "notsaved"){
            document.getElementById("facultysaveresult").innerHTML = "<h5><font color=\"red\">Error in saving faculty details. Try again later.</font></h5>";
        }else{
            document.getElementById("facultysaveresult").innerHTML = "<h5><font color=\"green\">Details Saved Successfully.</font></h5>";
        }
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("facultysaveresult").innerHTML = "";
        },5000);
    }
}


//---------------------------------------Save Student Details----------------------------------------------------------------
function saveStudentDetails(){
    var studentName = document.getElementById("name").value;
    var rollNo = document.getElementById("rollno").value;
    var email = document.getElementById("email").value;
    var address = document.getElementById("add").value;
    var contact = document.getElementById("contact").value;
    var semester = document.getElementById("sem").value;
    //var photoPath = document.getElementById("usrPhoto").value;
    
    if(studentName === '' || rollNo === '' || email === '' ||
       address === '' || contact === '' || semester === ''){
            return;
    }
    
    console.log("New student Details: "+studentName+' '+email+' '+address+' '+contact+' '+rollNo+' '+semester);
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = saveStudentDetailsProcessResponse;
    ajaxreq.open("POST","SaveStudentDetailsController",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    ajaxreq.send("studentName="+studentName+"&email="+email+"&address="+address+"&contact="+contact+"&semester="+semester+"&rollNo="+rollNo);
    console.log("After request for saving student details has been sent");

}

function saveStudentDetailsProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "notsaved"){
            document.getElementById("studentsaveresult").innerHTML = "<h5><font color=\"red\">Error in saving student details. Try again later.</font></h5>";
        }else{
            document.getElementById("studentsaveresult").innerHTML = "<h5><font color=\"green\">Details Saved Successfully.</font></h5>";
        }
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("studentsaveresult").innerHTML = "";
        },5000);
    }
}    

//-------------------------------------------Save Subject Details---------------------------------------------------------

function saveSubjectDetails(){
    var subName = document.getElementById("subName").value;
    var subCode = document.getElementById("subCode").value;
    var semester = document.getElementById("sem").value;
    var fac_a = document.getElementById("fac-a").value;
    var fac_b = document.getElementById("fac-b").value;
    
    if(subName === '' || subCode === '' || semester === '' || 
       fac_a === '' || fac_b === ''){
            return;
    }
    
    console.log("New subject Details: "+subName+' '+subCode+' '+semester+' '+fac_a+' '+fac_b);
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = saveSubjectDetailsProcessResponse;
    ajaxreq.open("POST","SaveSubjectDetailsController",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    ajaxreq.send("subName="+subName+"&fac_a="+fac_a+"&fac_b="+fac_b+"&semester="+semester+"&subCode="+subCode);
    console.log("After request for saving subject details has been sent");

}

function saveSubjectDetailsProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "notsaved"){
            document.getElementById("subjectsaveresult").innerHTML = "<h5><font color=\"red\">Error in saving subject details. Try again later.</font></h5>";
        }else{
            document.getElementById("subjectsaveresult").innerHTML = "<h5><font color=\"green\">Details Saved Successfully.</font></h5>";
        }
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("subjectsaveresult").innerHTML = "";
        },5000);
    }
}

//--------------------------------------------Save marks-----------------------------------------------------------------

function saveStudentMarks(){
    var rollNo = document.getElementById("rollNo").value;
    var subject = document.getElementById("subject").value;
    var semester = document.getElementById("sem").value;
    var midsem_1 = document.getElementById("mid-1").value;
    var midsem_2 = document.getElementById("mid-2").value;
    var midsem_3 = document.getElementById("mid-3").value;
    
    if(subject === '' || rollNo === '' || semester === '' || 
       midsem_1 === '' || midsem_2 === '' || midsem_3 === ''){
            return;
    }
    
    console.log("New marks Details: "+subject+' '+rollNo+' '+semester+' '+midsem_1+' '+midsem_2+' '+midsem_3);
    ajaxreq = new XMLHttpRequest();
    ajaxreq.onreadystatechange = saveStudentMarksProcessResponse;
    ajaxreq.open("POST","SaveStudentMarksController",true);
    ajaxreq.setRequestHeader("content-type","application/x-www-form-urlencoded");
    ajaxreq.send("subName="+subject+"&rollNo="+rollNo+"&midsem_1="+midsem_1+"&semester="+semester+"&midsem_2="+midsem_2+"&midsem_3="+midsem_3);
    console.log("After request for saving student marks has been sent");

}

function saveStudentMarksProcessResponse(){
    if(ajaxreq.readyState === 4){
        console.log("processresponse executed");
        var resp = ajaxreq.responseText;
        if(resp.trim() === "notsaved"){
            document.getElementById("markssaveresult").innerHTML = "<h5><font color=\"red\">Error in saving marks details. Try again later.</font></h5>";
        }else{
            document.getElementById("markssaveresult").innerHTML = "<h5><font color=\"green\">Details Saved Successfully.</font></h5>";
        }
        setTimeout(function(){
            console.log("Inside settimeout");
            document.getElementById("markssaveresult").innerHTML = "";
        },5000);
    }
}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------