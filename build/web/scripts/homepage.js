$(document).ready(function()
{
    $("#nextbtn").click(function()
    {
        doTask();
    });
});
function dovalidate()
{
    isRegChecked = $('#rbtnReg').is(':checked');
    isLogChecked = $('#rbtnLog').is(':checked');
    var status=true;
    
    if(isRegChecked===false && isLogChecked===false)
    {
        $("#result").css("display","inline");
        $("#result").text("Please select an option first");
        $("#result").css("font-weight","bold");
        $("#result").css("color","red");
        $("#result").fadeOut(4000);
        status= false;
    }
    //alert("var status is "+status);
    return status;
}
function doTask()
{
    var ans=dovalidate();
    
    if(ans==false)
    {
        //alert("in");
        return;
    }
    if(isRegChecked===true)
    {
       window.location="registration.html";
        //alert("in if");
   }
    else if(isLogChecked===true)
    {
        //alert("in else ");
        window.location="login.html";
        
    }
}