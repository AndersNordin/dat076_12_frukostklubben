/* 
    Document   : functions.js
    Created on : Oct 11, 2012, 01:11:41 PM
    Author     : Anders
    Description: Functions using Jquery
 */

// This is a bug-fix.
function greeting()
{
alert("hej");
}

var $ = jQuery;    
$(document).ready(function() {  
 
    // Handles the effects of the main menu.
    $("#toggle_arrow").click(function () {
        var elem = "nav#main_menu";
        $(elem).animate({
            width: 'toggle'
        },'fast');
        
        var currentClass = $(this).attr('class');
        if(currentClass == 'arrow_right'){
            $(this).removeClass('arrow_right');
            $(this).addClass("arrow_left");   
        }else
        {        
            $(this).removeClass('arrow_left');                
            $(this).addClass("arrow_right");                
        }              
    });  // toggle end 
}); // document ready end      